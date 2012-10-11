/***********************************************************************************************************************
 *
 * Copyright (C) 2010 by the Stratosphere project (http://stratosphere.eu)
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
 * Instance summary profiling events summarize the profiling events of all instances involved in computing a Nephele
 * job.
 * <p>
 * This class is not thread-safe.
 * 
 * @author warneke
 */
public final class InstanceSummaryProfilingEvent extends InstanceProfilingEvent {

	public InstanceSummaryProfilingEvent(final int profilingInterval, final int ioWaitCPU, final int idleCPU,
			final int userCPU, final int systemCPU, final int hardIrqCPU, final int softIrqCPU, final long totalMemory,
			final long stratosphereMemory, final long hdfsMemory, final long otherMemory,
			final long receivedBytes, final long transmittedBytes, final JobID jobID,
			final long timestamp, final long profilingTimestamp) {
		super(profilingInterval, ioWaitCPU, idleCPU, userCPU, systemCPU, hardIrqCPU, softIrqCPU, totalMemory,
			stratosphereMemory, hdfsMemory, otherMemory, receivedBytes, transmittedBytes, jobID,
			timestamp, profilingTimestamp);
	}

	/**
	 * Default constructor for serialization/deserialization.
	 */
	public InstanceSummaryProfilingEvent() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object obj) {

		if (!super.equals(obj)) {
			return false;
		}

		if (!(obj instanceof InstanceSummaryProfilingEvent)) {
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
