/***********************************************************************************************************************
 *
 * Copyright (C) 2010-2012 by the Stratosphere project (http://stratosphere.eu)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 **********************************************************************************************************************/

package eu.stratosphere.pact.client.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.stratosphere.nephele.configuration.ConfigConstants;
import eu.stratosphere.nephele.configuration.Configuration;
import eu.stratosphere.nephele.event.job.RecentJobEvent;
import eu.stratosphere.nephele.protocols.ExtendedManagementProtocol;
import eu.stratosphere.nephele.rpc.ManagementTypeUtils;
import eu.stratosphere.nephele.rpc.RPCService;

/**
 * @author Stephan Ewen (stephan.ewen@tu-berlin.com)
 */
public class JobsInfoServlet extends HttpServlet {
	/**
	 * Serial UID for serialization interoperability.
	 */
	private static final long serialVersionUID = 558077298726449201L;

	// ------------------------------------------------------------------------

	private final Configuration config;

	public JobsInfoServlet(Configuration nepheleConfig) {
		this.config = nepheleConfig;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// resp.setContentType("application/json");

		RPCService rpcService = null;
		try {

			rpcService = new RPCService(ManagementTypeUtils.getRPCTypesToRegister());
			ExtendedManagementProtocol jmConn = getJMConnection(rpcService);
			List<RecentJobEvent> recentJobs = jmConn.getRecentJobs();

			ArrayList<RecentJobEvent> jobs = new ArrayList<RecentJobEvent>(recentJobs);

			resp.setStatus(HttpServletResponse.SC_OK);
			PrintWriter wrt = resp.getWriter();
			wrt.write("[");
			for (int i = 0; i < jobs.size(); i++) {
				RecentJobEvent jobEvent = jobs.get(i);

				// Serialize job to json
				wrt.write("{");
				wrt.write("\"jobid\": \"" + jobEvent.getJobID() + "\",");
				if (jobEvent.getJobName() != null) {
					wrt.write("\"jobname\": \"" + jobEvent.getJobName() + "\",");
				}
				wrt.write("\"status\": \"" + jobEvent.getJobStatus() + "\",");
				wrt.write("\"time\": " + jobEvent.getTimestamp());
				wrt.write("}");
				// Write seperator between json objects
				if (i != jobs.size() - 1) {
					wrt.write(",");
				}
			}
			wrt.write("]");

		} catch (Throwable t) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.getWriter().print(t.getMessage());
		} finally {
			if (rpcService != null) {
				rpcService.shutDown();
			}
			rpcService = null;
		}
	}

	/**
	 * Sets up a connection to the JobManager.
	 * 
	 * @param rpcService
	 *        the RPC service to use to create the proxy object
	 * @return Connection to the JobManager.
	 * @throws IOException
	 */
	private ExtendedManagementProtocol getJMConnection(RPCService rpcService) throws IOException {
		String jmHost = config.getString(ConfigConstants.JOB_MANAGER_IPC_ADDRESS_KEY, null);
		int jmPort = config.getInteger(ConfigConstants.JOB_MANAGER_IPC_PORT_KEY, -1);

		return rpcService.getProxy(new InetSocketAddress(jmHost, jmPort), 
			ExtendedManagementProtocol.class);
	}

	protected String escapeString(String str) {
		int len = str.length();
		char[] s = str.toCharArray();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < len; i += 1) {
			char c = s[i];
			if ((c == '\\') || (c == '"') || (c == '/')) {
				sb.append('\\');
				sb.append(c);
			} else if (c == '\b')
				sb.append("\\b");
			else if (c == '\t')
				sb.append("\\t");
			else if (c == '\n')
				sb.append("<br>");
			else if (c == '\f')
				sb.append("\\f");
			else if (c == '\r')
				sb.append("\\r");
			else {
				if (c < ' ') {
					// Unreadable throw away
				} else {
					sb.append(c);
				}
			}
		}

		return sb.toString();
	}
}
