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

package eu.stratosphere.nephele.profiling.types;

import eu.stratosphere.nephele.jobgraph.JobID;

/**
 * A single instance profiling event encapsulates profiling information for one particular instance.
 * 
 * @author warneke
 */
public final class SingleInstanceProfilingEvent extends InstanceProfilingEvent {

	private final String instanceName;

	public SingleInstanceProfilingEvent(final int profilingInterval, final int ioWaitCPU, final int idleCPU,
			final int userCPU, final int systemCPU, final int hardIrqCPU, final int softIrqCPU, final long totalMemory,
			final long stratosphereMemory, final long hdfsMemory, final long otherMemory,
			final long receivedBytes, final long transmittedBytes, final JobID jobID, final long timestamp,
			final long profilingTimestamp, final String instanceName) {
		super(profilingInterval, ioWaitCPU, idleCPU, userCPU, systemCPU, hardIrqCPU, softIrqCPU, totalMemory,
			stratosphereMemory, hdfsMemory, otherMemory, receivedBytes, transmittedBytes, jobID,
			timestamp, profilingTimestamp);

		this.instanceName = instanceName;
	}

	/**
	 * Default constructor for serialization/deserialization.
	 */
	public SingleInstanceProfilingEvent() {
		this.instanceName = null;
	}

	/**
	 * Returns the name of the instance.
	 * 
	 * @return the name of the instance
	 */
	public String getInstanceName() {
		return this.instanceName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {

		if (!super.equals(obj)) {
			return false;
		}

		if (!(obj instanceof SingleInstanceProfilingEvent)) {
			return false;
		}

		final SingleInstanceProfilingEvent singleInstanceProfilingEvent = (SingleInstanceProfilingEvent) obj;

		if (!this.instanceName.equals(singleInstanceProfilingEvent.getInstanceName())) {
			return false;
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {

		return super.hashCode();
	}
}
