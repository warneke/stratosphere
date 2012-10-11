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

package eu.stratosphere.nephele.services.memorymanager.spi;

import edu.berkeley.icsi.memngt.pools.AbstractMemoryPool;
import edu.berkeley.icsi.memngt.utils.ClientUtils;

public final class MemoryPool extends AbstractMemoryPool<byte[]> {

	private final int pageSize;

	MemoryPool(int pageSize) {
		super("Nephele Memory Pool", calculatePoolCapacity(pageSize / 1024));
		this.pageSize = pageSize;
	}

	private static int calculatePoolCapacity(final int pageSize) {

		return ClientUtils.getMaximumUsableMemory() / pageSize;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected byte[] allocatedNewBuffer() {
		return new byte[this.pageSize];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getSizeOfBuffer(byte[] buffer) {
		return buffer.length / 1024;
	}
}
