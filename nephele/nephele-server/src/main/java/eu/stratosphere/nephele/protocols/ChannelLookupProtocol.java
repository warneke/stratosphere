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

package eu.stratosphere.nephele.protocols;

import java.io.IOException;

import edu.berkeley.icsi.memngt.rpc.RPCProtocol;
import eu.stratosphere.nephele.instance.InstanceConnectionInfo;
import eu.stratosphere.nephele.io.channels.ChannelID;
import eu.stratosphere.nephele.jobgraph.JobID;
import eu.stratosphere.nephele.taskmanager.routing.ConnectionInfoLookupResponse;

/**
 * The channel lookup protocol can be used to resolve the ID of an output channel to all recipients which shall receive
 * the data of this channel.
 * 
 * @author warneke
 */
public interface ChannelLookupProtocol extends RPCProtocol {

	/**
	 * Retrieves all recipients of a data for the given <code>sourceChannelID</code>.
	 * 
	 * @param caller
	 *        the {@link InstanceConnectionInfo} object of the task manager which calls this method
	 * @param jobID
	 *        the ID of the job the channel ID belongs to
	 * @param sourceChannelID
	 *        the ID of the channel to resolve
	 * @return the lookup response containing the connection info and a return code
	 * @throws IOException
	 *         thrown if an error occurs during the remote procedure call
	 * @throws InterruptedException
	 *         thrown if the caller is interrupted while waiting for the result of the remote procedure call
	 */
	ConnectionInfoLookupResponse lookupConnectionInfo(InstanceConnectionInfo caller, JobID jobID,
			ChannelID sourceChannelID) throws IOException, InterruptedException;
}
