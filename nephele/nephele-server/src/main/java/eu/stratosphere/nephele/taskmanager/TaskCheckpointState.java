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

package eu.stratosphere.nephele.taskmanager;

import eu.stratosphere.nephele.executiongraph.CheckpointState;
import eu.stratosphere.nephele.executiongraph.ExecutionVertexID;
import eu.stratosphere.nephele.jobgraph.JobID;

/**
 * This class can be used to propagate updates about a task's checkpoint state from the
 * task manager to the job manager.
 * <p>
 * This class is thread-safe.
 * 
 * @author warneke
 */
public final class TaskCheckpointState {

	private final JobID jobID;

	private final ExecutionVertexID executionVertexID;

	private final CheckpointState checkpointState;

	/**
	 * Creates a new task checkpoint state.
	 * 
	 * @param jobID
	 *        the ID of the job the task belongs to
	 * @param id
	 *        the ID of the task whose checkpoint state has changed
	 * @param checkpointState
	 *        the new checkpoint to be reported
	 */
	public TaskCheckpointState(final JobID jobID, final ExecutionVertexID id, final CheckpointState checkpointState) {

		if (jobID == null) {
			throw new IllegalArgumentException("Argument jobID must not be null");
		}

		if (id == null) {
			throw new IllegalArgumentException("Argument id must not be null");
		}

		if (checkpointState == null) {
			throw new IllegalArgumentException("Argument checkpointState must not be null");
		}

		this.jobID = jobID;
		this.executionVertexID = id;
		this.checkpointState = checkpointState;
	}

	/**
	 * Creates an empty task checkpoint state.
	 */
	public TaskCheckpointState() {
		this.jobID = null;
		this.executionVertexID = null;
		this.checkpointState = null;
	}

	/**
	 * Returns the ID of the job this update belongs to.
	 * 
	 * @return the ID of the job this update belongs to
	 */
	public JobID getJobID() {
		return this.jobID;
	}

	/**
	 * Returns the ID of the vertex this update refers to
	 * 
	 * @return the ID of the vertex this update refers to
	 */
	public ExecutionVertexID getVertexID() {
		return this.executionVertexID;
	}

	/**
	 * Returns the new checkpoint state.
	 * 
	 * @return the new checkpoint state
	 */
	public CheckpointState getCheckpointState() {
		return this.checkpointState;
	}
}
