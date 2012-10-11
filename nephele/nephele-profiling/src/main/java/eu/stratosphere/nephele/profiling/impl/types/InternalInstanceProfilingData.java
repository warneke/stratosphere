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

package eu.stratosphere.nephele.profiling.impl.types;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import eu.stratosphere.nephele.instance.InstanceConnectionInfo;

public class InternalInstanceProfilingData implements InternalProfilingData {

	private InstanceConnectionInfo instanceConnectionInfo;

	private int profilingInterval;

	private int ioWaitCPU;

	private int idleCPU;

	private int userCPU;

	private int systemCPU;

	private int hardIrqCPU;

	private int softIrqCPU;

	private long totalMemory;

	private long stratosphereMemory;

	private long hdfsMemory;

	private long otherMemory;

	private long receivedBytes;

	private long transmittedBytes;

	public InternalInstanceProfilingData() {
		this.ioWaitCPU = -1;
		this.idleCPU = -1;
		this.instanceConnectionInfo = new InstanceConnectionInfo();
		this.profilingInterval = -1;
		this.systemCPU = -1;
		this.totalMemory = -1;
		this.stratosphereMemory = -1;
		this.hdfsMemory = -1;
		this.otherMemory = -1;
		this.userCPU = -1;
		this.receivedBytes = -1;
		this.transmittedBytes = -1;
	}

	public InternalInstanceProfilingData(InstanceConnectionInfo instanceConnectionInfo, int profilingInterval) {

		this.instanceConnectionInfo = instanceConnectionInfo;
		this.profilingInterval = profilingInterval;
		this.ioWaitCPU = -1;
		this.idleCPU = -1;
		this.systemCPU = -1;
		this.totalMemory = -1;
		this.stratosphereMemory = -1;
		this.hdfsMemory = -1;
		this.otherMemory = -1;
		this.userCPU = -1;
		this.receivedBytes = -1;
		this.transmittedBytes = -1;
	}

	public int getIOWaitCPU() {
		return this.ioWaitCPU;
	}

	public int getIdleCPU() {
		return this.idleCPU;
	}

	public int getHardIrqCPU() {
		return this.hardIrqCPU;
	}

	public int getSoftIrqCPU() {
		return this.softIrqCPU;
	}

	public InstanceConnectionInfo getInstanceConnectionInfo() {
		return this.instanceConnectionInfo;
	}

	public int getProfilingInterval() {
		return this.profilingInterval;
	}

	public int getSystemCPU() {
		return this.systemCPU;
	}

	public long getTotalMemory() {
		return this.totalMemory;
	}

	public long getStratosphereMemory() {
		return this.stratosphereMemory;
	}

	public long getHDFSMemory() {
		return this.hdfsMemory;
	}

	public long getOtherMemory() {
		return this.otherMemory;
	}

	public int getUserCPU() {
		return this.userCPU;
	}

	public long getReceivedBytes() {
		return this.receivedBytes;
	}

	public long getTransmittedBytes() {
		return this.transmittedBytes;
	}

	@Override
	public void read(DataInput in) throws IOException {

		this.ioWaitCPU = in.readInt();
		this.idleCPU = in.readInt();
		this.instanceConnectionInfo.read(in);
		this.profilingInterval = in.readInt();
		this.systemCPU = in.readInt();
		this.totalMemory = in.readLong();
		this.stratosphereMemory = in.readLong();
		this.hdfsMemory = in.readLong();
		this.otherMemory = in.readLong();
		this.userCPU = in.readInt();
		this.receivedBytes = in.readLong();
		this.transmittedBytes = in.readLong();
		this.hardIrqCPU = in.readInt();
		this.softIrqCPU = in.readInt();

	}

	@Override
	public void write(DataOutput out) throws IOException {

		out.writeInt(this.ioWaitCPU);
		out.writeInt(this.idleCPU);
		this.instanceConnectionInfo.write(out);
		out.writeInt(this.profilingInterval);
		out.writeInt(this.systemCPU);
		out.writeLong(this.totalMemory);
		out.writeLong(this.stratosphereMemory);
		out.writeLong(this.hdfsMemory);
		out.writeLong(this.otherMemory);
		out.writeInt(this.userCPU);
		out.writeLong(this.receivedBytes);
		out.writeLong(this.transmittedBytes);
		out.writeInt(this.hardIrqCPU);
		out.writeInt(this.softIrqCPU);

	}

	public void setIoWaitCPU(int ioWaitCPU) {
		this.ioWaitCPU = ioWaitCPU;
	}

	public void setIdleCPU(int idleCPU) {
		this.idleCPU = idleCPU;
	}

	public void setSystemCPU(int systemCPU) {
		this.systemCPU = systemCPU;
	}

	public void setHardIrqCPU(int hardIrqCPU) {
		this.hardIrqCPU = hardIrqCPU;
	}

	public void setSoftIrqCPU(int softIrqCPU) {
		this.softIrqCPU = softIrqCPU;
	}

	public void setTotalMemory(long totalMemory) {
		this.totalMemory = totalMemory;
	}

	public void setStratosphereMemory(long stratosphereMemory) {
		this.stratosphereMemory = stratosphereMemory;
	}

	public void setHDFSMemory(long hdfsMemory) {
		this.hdfsMemory = hdfsMemory;
	}

	public void setOtherMemory(long otherMemory) {
		this.otherMemory = otherMemory;
	}

	public void setUserCPU(int userCPU) {
		this.userCPU = userCPU;
	}

	public void setReceivedBytes(long receivedBytes) {
		this.receivedBytes = receivedBytes;
	}

	public void setTransmittedBytes(long transmittedBytes) {
		this.transmittedBytes = transmittedBytes;
	}

}
