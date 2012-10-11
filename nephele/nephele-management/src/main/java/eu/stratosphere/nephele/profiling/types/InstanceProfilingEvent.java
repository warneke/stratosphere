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

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import eu.stratosphere.nephele.jobgraph.JobID;

/**
 * Instance profiling events are a special subclass of profiling events. They contain profiling information about the
 * utilization of a particular instance during a job execution.
 * <p>
 * This class is not thread-safe.
 * 
 * @author warneke
 * @author stanik
 */
public abstract class InstanceProfilingEvent extends ProfilingEvent {

	/**
	 * The interval of time this profiling event covers in milliseconds.
	 */
	private int profilingInterval;

	/**
	 * The percentage of time the CPU(s) spent in state IOWAIT during the profiling interval.
	 */
	private int ioWaitCPU;

	/**
	 * The percentage of time the CPU(s) spent in state IDLE during the profiling interval.
	 */
	private int idleCPU;

	/**
	 * The percentage of time the CPU(s) spent in state USER during the profiling interval.
	 */
	private int userCPU;

	/**
	 * The percentage of time the CPU(s) spent in state SYSTEM during the profiling interval.
	 */
	private int systemCPU;

	/**
	 * The percentage of time the CPU(s) spent in state HARD_IRQ during the profiling interval.
	 */
	private int hardIrqCPU;

	/**
	 * The percentage of time the CPU(s) spent in state SOFT_IRQ during the profiling interval.
	 */
	private int softIrqCPU;

	/**
	 * The total amount of this instance's main memory in bytes.
	 */
	private long totalMemory;

	private long stratosphereMemory;

	private long hdfsMemory;

	private long otherMemory;

	/**
	 * The number of bytes received via network during the profiling interval.
	 */
	private long receivedBytes;

	/**
	 * The number of bytes transmitted via network during the profiling interval.
	 */
	private long transmittedBytes;

	public InstanceProfilingEvent(final int profilingInterval, final int ioWaitCPU, final int idleCPU,
			final int userCPU, final int systemCPU, final int hardIrqCPU, final int softIrqCPU, final long totalMemory,
			final long stratosphereMemory, final long hdfsMemory, final long otherMemory, final long receivedBytes,
			final long transmittedBytes, final JobID jobID, final long timestamp,
			final long profilingTimestamp) {

		super(jobID, timestamp, profilingTimestamp);

		this.profilingInterval = profilingInterval;

		this.ioWaitCPU = ioWaitCPU;
		this.idleCPU = idleCPU;
		this.userCPU = userCPU;
		this.systemCPU = systemCPU;
		this.hardIrqCPU = hardIrqCPU;
		this.softIrqCPU = softIrqCPU;

		this.totalMemory = totalMemory;
		this.stratosphereMemory = stratosphereMemory;
		this.hdfsMemory = hdfsMemory;
		this.otherMemory = otherMemory;

		this.receivedBytes = receivedBytes;
		this.transmittedBytes = transmittedBytes;
	}

	/**
	 * Default constructor for serialization/deserialization.
	 */
	public InstanceProfilingEvent() {
		super();
	}

	/**
	 * Returns the interval of time this profiling event covers in milliseconds.
	 * 
	 * @return the interval of time this profiling event covers in milliseconds
	 */
	public final int getProfilingInterval() {
		return this.profilingInterval;
	}

	/**
	 * Returns the total amount of memory of the corresponding instance.
	 * 
	 * @return the total amount of memory in bytes
	 */
	public final long getTotalMemory() {
		return this.totalMemory;
	}

	public final long getStratosphereMemory() {
		return this.stratosphereMemory;
	}

	public final long getHDFSMemory() {
		return this.hdfsMemory;
	}

	public final long getOtherMemory() {
		return this.otherMemory;
	}

	/**
	 * Returns the percentage of time the CPU(s) spent in state USER during the profiling interval.
	 * 
	 * @return the percentage of time the CPU(s) spent in state USER during the profiling interval
	 */
	public final int getUserCPU() {
		return this.userCPU;
	}

	/**
	 * Returns the percentage of time the CPU(s) spent in state SYSTEM during the profiling interval.
	 * 
	 * @return the percentage of time the CPU(s) spent in state SYSTEM during the profiling interval
	 */
	public final int getSystemCPU() {
		return this.systemCPU;
	}

	/**
	 * Returns the percentage of time the CPU(s) spent in state IDLE during the profiling interval. Prior to Linux
	 * 2.5.41, this includes IO-wait time.
	 * 
	 * @return the percentage of time the CPU(s) spent in state IDLE during the profiling interval
	 */
	public final int getIdleCPU() {
		return this.idleCPU;
	}

	/**
	 * Returns the percentage of time the CPU(s) spent in state IOWAIT during the profiling interval. Prior to Linux
	 * 2.5.41, included in idle.
	 * 
	 * @return the percentage of time the CPU(s) spent in state IOWAIT during the profiling interval.
	 */
	public final int getIOWaitCPU() {
		return this.ioWaitCPU;
	}

	/**
	 * Returns the percentage of time the CPU(s) spent in state HARD_IRQ during the profiling interval.
	 * 
	 * @return the percentage of time the CPU(s) spent in state HARD_IRQ during the profiling interval
	 */
	public final int getHardIrqCPU() {
		return this.hardIrqCPU;
	}

	/**
	 * Returns the percentage of time the CPU(s) spent in state SOFT_IRQ during the profiling interval.
	 * 
	 * @return the percentage of time the CPU(s) spent in state SOFT_IRQ during the profiling interval
	 */
	public final int getSoftIrqCPU() {
		return this.softIrqCPU;
	}

	/**
	 * Returns the number of bytes received via network during the profiling interval.
	 * 
	 * @return the number of bytes received via network during the profiling interval
	 */
	public final long getReceivedBytes() {
		return this.receivedBytes;
	}

	/**
	 * Returns the number of bytes transmitted via network during the profiling interval.
	 * 
	 * @return the number of bytes transmitted via network during the profiling interval
	 */
	public final long getTransmittedBytes() {
		return this.transmittedBytes;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void read(final DataInput in) throws IOException {
		super.read(in);

		this.profilingInterval = in.readInt();

		this.ioWaitCPU = in.readInt();
		this.idleCPU = in.readInt();
		this.userCPU = in.readInt();
		this.systemCPU = in.readInt();
		this.hardIrqCPU = in.readInt();
		this.softIrqCPU = in.readInt();

		this.totalMemory = in.readLong();
		this.stratosphereMemory = in.readLong();
		this.hdfsMemory = in.readLong();
		this.otherMemory = in.readLong();

		this.receivedBytes = in.readLong();
		this.transmittedBytes = in.readLong();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(final DataOutput out) throws IOException {
		super.write(out);

		out.writeInt(this.profilingInterval);

		out.writeInt(this.ioWaitCPU);
		out.writeInt(this.idleCPU);
		out.writeInt(this.userCPU);
		out.writeInt(this.systemCPU);
		out.writeInt(this.hardIrqCPU);
		out.writeInt(this.softIrqCPU);

		out.writeLong(this.totalMemory);
		out.writeLong(this.stratosphereMemory);
		out.writeLong(this.hdfsMemory);
		out.writeLong(this.otherMemory);

		out.writeLong(this.receivedBytes);
		out.writeLong(this.transmittedBytes);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object obj) {

		if (!super.equals(obj)) {
			return false;
		}

		if (!(obj instanceof InstanceProfilingEvent)) {
			return false;
		}

		final InstanceProfilingEvent instanceProfilingEvent = (InstanceProfilingEvent) obj;

		if (this.profilingInterval != instanceProfilingEvent.getProfilingInterval()) {
			return false;
		}

		if (this.ioWaitCPU != instanceProfilingEvent.getIOWaitCPU()) {
			return false;
		}
		if (this.idleCPU != instanceProfilingEvent.getIdleCPU()) {
			return false;
		}

		if (this.userCPU != instanceProfilingEvent.getUserCPU()) {
			return false;
		}

		if (this.systemCPU != instanceProfilingEvent.getSystemCPU()) {
			return false;
		}

		if (this.hardIrqCPU != instanceProfilingEvent.getHardIrqCPU()) {
			return false;
		}

		if (this.softIrqCPU != instanceProfilingEvent.getSoftIrqCPU()) {
			return false;
		}

		if (this.totalMemory != instanceProfilingEvent.getTotalMemory()) {
			return false;
		}

		if (this.stratosphereMemory != instanceProfilingEvent.getStratosphereMemory()) {
			return false;
		}

		if (this.hdfsMemory != instanceProfilingEvent.getHDFSMemory()) {
			return false;
		}

		if (this.otherMemory != instanceProfilingEvent.getOtherMemory()) {
			return false;
		}

		if (this.receivedBytes != instanceProfilingEvent.getReceivedBytes()) {
			return false;
		}

		if (this.transmittedBytes != instanceProfilingEvent.getTransmittedBytes()) {
			return false;
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {

		long hashCode = getJobID().hashCode() + getTimestamp() + getProfilingTimestamp();
		hashCode += (this.profilingInterval + this.ioWaitCPU + this.idleCPU + this.userCPU + this.systemCPU
			+ this.hardIrqCPU + this.softIrqCPU);
		hashCode += (this.totalMemory + this.stratosphereMemory + this.hdfsMemory + this.otherMemory);
		hashCode -= Integer.MAX_VALUE;

		return (int) (hashCode % Integer.MAX_VALUE);
	}
}
