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

package eu.stratosphere.nephele.profiling.impl;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import eu.stratosphere.nephele.configuration.Configuration;
import eu.stratosphere.nephele.configuration.GlobalConfiguration;
import eu.stratosphere.nephele.execution.Environment;
import eu.stratosphere.nephele.executiongraph.ExecutionVertexID;
import eu.stratosphere.nephele.instance.InstanceConnectionInfo;
import eu.stratosphere.nephele.profiling.ProfilingException;
import eu.stratosphere.nephele.profiling.ProfilingUtils;
import eu.stratosphere.nephele.profiling.TaskManagerProfiler;
import eu.stratosphere.nephele.profiling.impl.types.InternalExecutionVertexThreadProfilingData;
import eu.stratosphere.nephele.profiling.impl.types.InternalInstanceProfilingData;
import eu.stratosphere.nephele.profiling.impl.types.InternalProfilingData;
import eu.stratosphere.nephele.rpc.ProfilingTypeUtils;
import eu.stratosphere.nephele.rpc.RPCService;
import eu.stratosphere.nephele.taskmanager.runtime.RuntimeTask;
import eu.stratosphere.nephele.util.StringUtils;

public class TaskManagerProfilerImpl extends TimerTask implements TaskManagerProfiler {

	private static final Log LOG = LogFactory.getLog(TaskManagerProfilerImpl.class);

	private final RPCService rpcService;

	private final ProfilerImplProtocol jobManagerProfiler;

	private final Timer timer;

	private final ThreadMXBean tmx;

	private final long timerInterval;

	private final InstanceProfiler instanceProfiler;

	private final ConcurrentMap<Environment, EnvironmentThreadSet> monitoredThreads = new ConcurrentHashMap<Environment, EnvironmentThreadSet>();

	public TaskManagerProfilerImpl(final InetAddress jobManagerAddress,
			final InstanceConnectionInfo instanceConnectionInfo) throws ProfilingException {

		// Start RPC service
		try {
			this.rpcService = new RPCService(ProfilingTypeUtils.getRPCTypesToRegister());
		} catch (IOException e) {
			throw new ProfilingException(StringUtils.stringifyException(e));
		}

		// Create RPC stub for communication with job manager's profiling component.
		final InetSocketAddress profilingAddress = new InetSocketAddress(jobManagerAddress, GlobalConfiguration
			.getInteger(ProfilingUtils.JOBMANAGER_RPC_PORT_KEY, ProfilingUtils.JOBMANAGER_DEFAULT_RPC_PORT));
		try {
			this.jobManagerProfiler = this.rpcService.getProxy(profilingAddress, ProfilerImplProtocol.class);
		} catch (IOException e) {
			throw new ProfilingException(StringUtils.stringifyException(e));
		}

		// Initialize MX interface and check if thread contention monitoring is supported
		this.tmx = ManagementFactory.getThreadMXBean();
		if (this.tmx.isThreadContentionMonitoringSupported()) {
			this.tmx.setThreadContentionMonitoringEnabled(true);
		} else {
			throw new ProfilingException("The thread contention monitoring is not supported.");
		}

		// Create instance profiler
		this.instanceProfiler = new InstanceProfiler(instanceConnectionInfo);

		// Set and trigger timer
		this.timerInterval = (long) (GlobalConfiguration.getInteger(ProfilingUtils.TASKMANAGER_REPORTINTERVAL_KEY,
			ProfilingUtils.DEFAULT_TASKMANAGER_REPORTINTERVAL) * 1000);
		// The initial delay is based on a random value, so the task managers will not send data to the job manager all
		// at once.
		final long initialDelay = (long) (Math.random() * this.timerInterval);
		this.timer = new Timer(true);
		this.timer.schedule(this, initialDelay, this.timerInterval);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerExecutionObserver(final RuntimeTask task, final Configuration jobConfiguration) {

		// Register profiling hook for the environment
		task.registerExecutionObserver(new EnvironmentObserverImpl(this, task.getVertexID(), task
			.getRuntimeEnvironment()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void unregisterExecutionObserver(final ExecutionVertexID id) {
		/*
		 * Nothing to do here, the task will unregister itself when its
		 * execution state has either switched to FINISHED, CANCELLED,
		 * or FAILED.
		 */
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void shutdown() {

		// Stop the RPC service
		this.rpcService.shutDown();

		// Stop the timer task
		this.timer.cancel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {

		final List<InternalProfilingData> profilingDataList = new ArrayList<InternalProfilingData>(16);
		final long timestamp = System.currentTimeMillis();

		// Collect profiling information of the threads
		final Iterator<Environment> iterator = this.monitoredThreads.keySet().iterator();
		while (iterator.hasNext()) {
			final Environment environment = iterator.next();
			final EnvironmentThreadSet environmentThreadSet = this.monitoredThreads.get(environment);
			final InternalExecutionVertexThreadProfilingData threadProfilingData = environmentThreadSet
				.captureCPUUtilization(environment.getJobID(), this.tmx, timestamp);
			if (threadProfilingData != null) {
				profilingDataList.add(threadProfilingData);
			}
		}

		InternalInstanceProfilingData instanceProfilingData = null;
		try {
			instanceProfilingData = this.instanceProfiler.generateProfilingData(timestamp);
		} catch (ProfilingException e) {
			LOG.error(StringUtils.stringifyException(e));
		}

		if (instanceProfilingData != null) {
			profilingDataList.add(instanceProfilingData);
		}

		if (!profilingDataList.isEmpty()) {
			try {
				this.jobManagerProfiler.reportProfilingData(profilingDataList);
			} catch (IOException e) {
				LOG.error(StringUtils.stringifyException(e));
			} catch (InterruptedException ie) {
				if (LOG.isDebugEnabled()) {
					LOG.debug(StringUtils.stringifyException(ie));
				}
			}
		}
	}

	void registerMainThreadForCPUProfiling(final Environment environment, final Thread thread,
			final ExecutionVertexID executionVertexID) {

		LOG.debug("Registering thread " + thread.getName() + " for CPU monitoring");
		if (this.monitoredThreads.putIfAbsent(environment,
			new EnvironmentThreadSet(this.tmx, thread, executionVertexID)) != null) {
			LOG.error("There is already a main thread registered for environment object " + environment.getTaskName());
		}
	}

	void registerUserThreadForCPUProfiling(final Environment environment, final Thread userThread) {

		final EnvironmentThreadSet environmentThreadList = this.monitoredThreads.get(environment);
		if (environmentThreadList == null) {
			LOG.error("Trying to register " + userThread.getName() + " but no main thread found!");
			return;
		}

		environmentThreadList.addUserThread(this.tmx, userThread);
	}

	void unregisterMainThreadFromCPUProfiling(final Environment environment, final Thread thread) {

		LOG.debug("Unregistering thread " + thread.getName() + " from CPU monitoring");
		final EnvironmentThreadSet environmentThreadSet = this.monitoredThreads.remove(environment);
		if (environmentThreadSet != null) {

			if (environmentThreadSet.getMainThread() != thread) {
				LOG.error("The thread " + thread.getName() + " is not the main thread of this environment");
			}

			if (environmentThreadSet.getNumberOfUserThreads() > 0) {
				LOG.error("Thread " + environmentThreadSet.getMainThread().getName()
					+ " has still unfinished user threads!");
			}
		}
	}

	void unregisterUserThreadFromCPUProfiling(final Environment environment, final Thread userThread) {

		final EnvironmentThreadSet environmentThreadSet = this.monitoredThreads.get(environment);
		if (environmentThreadSet == null) {
			LOG.error("Trying to unregister " + userThread.getName() + " but no main thread found!");
			return;
		}

		environmentThreadSet.removeUserThread(userThread);
	}
}
