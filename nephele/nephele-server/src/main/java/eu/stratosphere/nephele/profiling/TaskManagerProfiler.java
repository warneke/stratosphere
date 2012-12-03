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

package eu.stratosphere.nephele.profiling;

import eu.stratosphere.nephele.configuration.Configuration;
import eu.stratosphere.nephele.executiongraph.ExecutionVertexID;
import eu.stratosphere.nephele.taskmanager.runtime.RuntimeTask;

/**
 * This interface must be implemented by profiling components
 * for the task manager manager.
 * 
 * @author warneke
 */
public interface TaskManagerProfiler {

	/**
	 * Registers an {@link ExecutionObserver} object for profiling.
	 * 
	 * @param task
	 *        task to be register a profiling observer for
	 * @param jobConfiguration
	 *        the job configuration sent with the task
	 */
	void registerExecutionObserver(RuntimeTask task, Configuration jobConfiguration);

	/**
	 * Unregisters all previously register {@link ExecutionObserver} objects for
	 * the vertex identified by the given ID.
	 * 
	 * @param id
	 *        the ID of the vertex to unregister the {@link ExecutionObserver} objects for
	 */
	void unregisterExecutionObserver(ExecutionVertexID id);

	/**
	 * Shuts done the task manager's profiling component
	 * and stops all its internal processes.
	 */
	void shutdown();
}
