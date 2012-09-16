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

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.berkeley.icsi.memngt.protocols.ClientToDaemonProtocol;
import edu.berkeley.icsi.memngt.protocols.DaemonToClientProtocol;
import edu.berkeley.icsi.memngt.protocols.RegistrationException;
import edu.berkeley.icsi.memngt.rpc.RPCService;
import edu.berkeley.icsi.memngt.utils.ClientUtils;
import eu.stratosphere.nephele.services.memorymanager.MemoryAllocationException;
import eu.stratosphere.nephele.services.memorymanager.MemoryManager;
import eu.stratosphere.nephele.services.memorymanager.MemorySegment;
import eu.stratosphere.nephele.template.AbstractInvokable;

public class DynamicMemoryManager implements MemoryManager, DaemonToClientProtocol {

	/**
	 * The default memory page size. Currently set to 32 kilobytes.
	 */
	public static final int DEFAULT_PAGE_SIZE = 32 * 1024;

	/**
	 * The minimal memory page size. Currently set to 4 kilobytes.
	 */
	public static final int MIN_PAGE_SIZE = 4 * 1024;

	/**
	 * The default value for the minimum number of pages to be requested/relinquished per adaptation.
	 */
	public static final int DEFAULT_ADAPTATION_GRANULARITY = 128;

	/**
	 * The Log.
	 */
	private static final Log LOG = LogFactory.getLog(DefaultMemoryManager.class);

	/**
	 * The port the RPC service of the local memory negotiator listens on.
	 */
	private static final int MEMORY_NEGOTIATOR_RPC_PORT = 8001;

	// --------------------------------------------------------------------------------------------

	private final Object lock = new Object(); // The lock used on the shared structures.

	private final RPCService rpcService;

	private final ClientToDaemonProtocol memoryNegiatorDaemon;

	private final ArrayDeque<byte[]> freeSegments; // the free memory segments

	private final HashMap<AbstractInvokable, Set<DefaultMemorySegment>> allocatedSegments;

	private final long roundingMask; // mask used to round down sizes to multiples of the page size

	private final int pageSize; // the page size, in bytes

	private final int pageSizeBits; // the number of bits that the power-of-two page size corresponds to

	private final int adaptationGranularity; // the minimum number of pages that requested/relinquish per adaptation

	private boolean isShutDown; // flag whether the close() has already been invoked.

	private int grantedMemoryShare; // the size of the granted memory share from the memory negotiator daemon

	// ------------------------------------------------------------------------
	// Constructors / Destructors
	// ------------------------------------------------------------------------

	public DynamicMemoryManager() throws IOException {
		this(DEFAULT_PAGE_SIZE, DEFAULT_ADAPTATION_GRANULARITY);
	}

	public DynamicMemoryManager(int pageSize, final int adaptationGranularity) throws IOException {

		if (pageSize <= MIN_PAGE_SIZE) {
			throw new IllegalArgumentException("The page size must be at least " + MIN_PAGE_SIZE + " bytes.");
		}
		if ((pageSize & (pageSize - 1)) != 0) {
			// not a power of two
			throw new IllegalArgumentException("The given page size is not a power of two.");
		}

		// assign page size and bit utilities
		this.pageSize = pageSize;
		this.roundingMask = ~((long) (pageSize - 1));
		int log = 0;
		while ((pageSize = pageSize >>> 1) != 0)
			log++;
		this.pageSizeBits = log;

		// set the adaptation granularity
		this.adaptationGranularity = adaptationGranularity;

		// initialize the free segments and allocated segments tracking structures
		this.freeSegments = new ArrayDeque<byte[]>();
		this.allocatedSegments = new HashMap<AbstractInvokable, Set<DefaultMemorySegment>>();

		// initialize the RPC connection to the memory
		this.rpcService = new RPCService(MEMORY_NEGOTIATOR_RPC_PORT);
		this.rpcService.setProtocolCallbackHandler(DaemonToClientProtocol.class, this);

		// TODO: Make port configurable
		this.memoryNegiatorDaemon = this.rpcService.getProxy(new InetSocketAddress(8000), ClientToDaemonProtocol.class);

		// determine granted memory share
		try {
			this.grantedMemoryShare = this.memoryNegiatorDaemon.registerClient("Nephele Task Manager",
				ClientUtils.getPID(),
				MEMORY_NEGOTIATOR_RPC_PORT);
		} catch (RegistrationException re) {
			throw new IOException(re);
		}

		LOG.info("Memory negotiator daemon granted memory share of " + this.grantedMemoryShare + " kilobytes");

		// fill segment pool
		synchronized (this) {
			adaptMemoryResources();
		}
	}

	/**
	 * Adapts the resources of the memory manager according to the granted memory share of
	 * the memory negotiator daemon.
	 */
	private void adaptMemoryResources() {

		LOG.debug("Adapting memory resources");

		final int pid = ClientUtils.getPID();

		int added = 0;
		while (ClientUtils.getPhysicalMemorySize(pid) < this.grantedMemoryShare) {
			for (int i = 0; i < this.adaptationGranularity; ++i) {
				this.freeSegments.add(new byte[this.pageSize]);
				++added;
			}
		}

		int removed = 0;
		while (ClientUtils.getPhysicalMemorySize(pid) > this.grantedMemoryShare) {
			for (int i = 0; i < this.adaptationGranularity; ++i) {
				this.freeSegments.poll();
				++removed;

				// No more free segments to relinquish
				if (this.freeSegments.isEmpty()) {
					break;
				}
			}
			System.gc();
		}

		if (ClientUtils.getPhysicalMemorySize(pid) > this.grantedMemoryShare) {
			LOG.info("Need to relinquish more memory ");
		}

		if (LOG.isInfoEnabled()) {
			LOG.info("Physical memory size is " + ClientUtils.getPhysicalMemorySize(pid) +
				", granted memory share is " + this.grantedMemoryShare + " (added " + added +
				" memory segments, reliquished " + removed + ")");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.nephele.services.memorymanager.MemoryManager#shutdown()
	 */
	@Override
	public void shutdown() {

		// -------------------- BEGIN CRITICAL SECTION -------------------
		synchronized (this.lock) {

			if (!this.isShutDown) {
				if (LOG.isDebugEnabled())
					LOG.debug("Shutting down MemoryManager instance " + toString());

				// mark as shutdown and release memory
				this.isShutDown = true;
				this.freeSegments.clear();

				// go over all allocated segments and release them
				for (Set<DefaultMemorySegment> segments : this.allocatedSegments.values()) {
					for (DefaultMemorySegment seg : segments) {
						seg.destroy();
					}
				}
			}
		}
		// -------------------- END CRITICAL SECTION -------------------

		// shut down memory negotiator RPC service
		this.rpcService.shutDown();
	}

	// ------------------------------------------------------------------------
	// MemoryManager interface implementation
	// ------------------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.nephele.services.memorymanager.MemoryManager#allocatePages(eu.stratosphere.nephele.template.
	 * AbstractInvokable, int)
	 */
	@Override
	public List<MemorySegment> allocatePages(final AbstractInvokable owner, final int numPages)
			throws MemoryAllocationException {

		final ArrayList<MemorySegment> segs = new ArrayList<MemorySegment>(numPages);
		allocatePages(owner, segs, numPages);
		return segs;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.nephele.services.memorymanager.MemoryManager#allocatePages(eu.stratosphere.nephele.template.
	 * AbstractInvokable, java.util.List, int)
	 */
	@Override
	public void allocatePages(final AbstractInvokable owner, final List<MemorySegment> target, final int numPages)
			throws MemoryAllocationException {

		// sanity check
		if (owner == null) {
			throw new IllegalAccessError("The memory owner must not be null.");
		}

		// reserve array space, if applicable
		if (target instanceof ArrayList) {
			((ArrayList<MemorySegment>) target).ensureCapacity(numPages);
		}

		// -------------------- BEGIN CRITICAL SECTION -------------------
		synchronized (this.lock) {

			if (this.isShutDown) {
				throw new IllegalStateException("Memory manager has been shut down.");
			}

			if (numPages > this.freeSegments.size()) {
				throw new MemoryAllocationException("Could not allocate " + numPages + " pages. Only " +
					this.freeSegments.size() + " pages are remaining.");
			}

			Set<DefaultMemorySegment> segmentsForOwner = this.allocatedSegments.get(owner);
			if (segmentsForOwner == null) {
				segmentsForOwner = new HashSet<DefaultMemorySegment>(4 * numPages / 3 + 1);
				this.allocatedSegments.put(owner, segmentsForOwner);
			}

			for (int i = numPages; i > 0; i--) {
				byte[] buffer = this.freeSegments.poll();
				final DefaultMemorySegment segment = new DefaultMemorySegment(owner, buffer, 0, this.pageSize);
				target.add(segment);
				segmentsForOwner.add(segment);
			}
		}
		// -------------------- END CRITICAL SECTION -------------------
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.nephele.services.memorymanager.MemoryManager#allocatePages(eu.stratosphere.nephele.template.
	 * AbstractInvokable, long)
	 */
	@Override
	public List<MemorySegment> allocatePages(final AbstractInvokable owner, final long numBytes)
			throws MemoryAllocationException {

		return allocatePages(owner, getNumPages(numBytes));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.nephele.services.memorymanager.MemoryManager#allocatePages(eu.stratosphere.nephele.template.
	 * AbstractInvokable, java.util.List, long)
	 */
	@Override
	public void allocatePages(final AbstractInvokable owner, final List<MemorySegment> target, final long numBytes)
			throws MemoryAllocationException {

		allocatePages(owner, target, getNumPages(numBytes));
	}

	// ------------------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see
	 * eu.stratosphere.nephele.services.memorymanager.MemoryManager#release(eu.stratosphere.nephele.services.memorymanager
	 * .MemorySegment)
	 */
	@Override
	public void release(final MemorySegment segment)
	{
		// check if segment is null or has already been freed
		if (segment == null || segment.isFreed() || !(segment instanceof DefaultMemorySegment)) {
			return;
		}

		final DefaultMemorySegment defSeg = (DefaultMemorySegment) segment;
		final AbstractInvokable owner = defSeg.owner;

		// -------------------- BEGIN CRITICAL SECTION -------------------
		synchronized (this.lock) {

			if (this.isShutDown) {
				throw new IllegalStateException("Memory manager has been shut down.");
			}

			// remove the reference in the map for the owner
			try {
				Set<DefaultMemorySegment> segsForOwner = this.allocatedSegments.get(owner);

				if (segsForOwner != null) {
					segsForOwner.remove(defSeg);
					if (segsForOwner.isEmpty()) {
						this.allocatedSegments.remove(owner);
					}
				}
			} catch (Throwable t) {
				LOG.error("Error removing book-keeping reference to allocated memory segment.", t);
			} finally {
				// release the memory in any case
				byte[] buffer = defSeg.destroy();
				this.freeSegments.add(buffer);
			}
		}
		// -------------------- END CRITICAL SECTION -------------------
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.nephele.services.memorymanager.MemoryManager#release(java.util.Collection)
	 */
	@Override
	public <T extends MemorySegment> void release(Collection<T> segments) {

		// sanity checks
		if (segments == null) {
			return;
		}

		final Iterator<T> segmentsIterator = segments.iterator();

		// -------------------- BEGIN CRITICAL SECTION -------------------
		synchronized (this.lock) {

			if (this.isShutDown) {
				throw new IllegalStateException("Memory manager has been shut down.");
			}

			AbstractInvokable lastOwner = null;
			Set<DefaultMemorySegment> segsForOwner = null;

			// go over all segments
			while (segmentsIterator.hasNext()) {

				final MemorySegment seg = segmentsIterator.next();
				if (seg.isFreed()) {
					continue;
				}

				final DefaultMemorySegment defSeg = (DefaultMemorySegment) seg;
				final AbstractInvokable owner = defSeg.owner;

				try {
					// get the list of segments by this owner only if it is a different owner than for
					// the previous one (or it is the first segment)
					if (lastOwner != owner) {
						lastOwner = owner;
						segsForOwner = this.allocatedSegments.get(owner);
					}

					// remove the segment from the list
					if (segsForOwner != null) {
						segsForOwner.remove(defSeg);
						if (segsForOwner.isEmpty()) {
							this.allocatedSegments.remove(owner);
						}
					}
				} catch (Throwable t) {
					LOG.error("Error removing book-keeping reference to allocated memory segment.", t);
				} finally {
					// release the memory in any case
					byte[] buffer = defSeg.destroy();
					this.freeSegments.add(buffer);
				}
			}
		}
		// -------------------- END CRITICAL SECTION -------------------
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.nephele.services.memorymanager.MemoryManager#releaseAll(eu.stratosphere.nephele.template.
	 * AbstractInvokable)
	 */
	@Override
	public void releaseAll(AbstractInvokable owner) {

		// -------------------- BEGIN CRITICAL SECTION -------------------
		synchronized (this.lock) {

			if (this.isShutDown) {
				throw new IllegalStateException("Memory manager has been shut down.");
			}

			// get all segments
			final Set<DefaultMemorySegment> segments = this.allocatedSegments.remove(owner);

			// all segments may have been freed previously individually
			if (segments == null || segments.isEmpty()) {
				return;
			}

			// free each segment
			for (DefaultMemorySegment seg : segments) {
				final byte[] buffer = seg.destroy();
				this.freeSegments.add(buffer);
			}

			segments.clear();
		}
		// -------------------- END CRITICAL SECTION -------------------
	}

	// ------------------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.nephele.services.memorymanager.MemoryManager#getPageSize()
	 */
	@Override
	public int getPageSize() {
		return this.pageSize;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.nephele.services.memorymanager.MemoryManager#computeNumberOfPages(long)
	 */
	@Override
	public int computeNumberOfPages(final long numBytes) {
		return getNumPages(numBytes);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.nephele.services.memorymanager.MemoryManager#roundDownToPageSizeMultiple(long)
	 */
	@Override
	public long roundDownToPageSizeMultiple(final long numBytes) {
		return numBytes & this.roundingMask;
	}

	// ------------------------------------------------------------------------

	private final int getNumPages(final long numBytes) {

		if (numBytes < 0) {
			throw new IllegalArgumentException("The number of bytes to allocate must not be negative.");
		}

		final long numPages = numBytes >>> this.pageSizeBits;
		if (numPages <= Integer.MAX_VALUE) {
			return (int) numPages;
		} else {
			throw new IllegalArgumentException("The given number of bytes correstponds to more than MAX_INT pages.");
		}
	}

	// ------------------------------------------------------------------------

	private static final class DefaultMemorySegment extends MemorySegment {

		private AbstractInvokable owner;

		DefaultMemorySegment(final AbstractInvokable owner, final byte[] memory, final int offset, final int size) {
			super(memory, offset, size);
			this.owner = owner;
		}

		byte[] destroy() {

			final byte[] buffer = this.memory;
			this.memory = null;
			this.wrapper = null;
			return buffer;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void grantedMemoryShareChanged(final int sizeOfNewShare) throws IOException {

		LOG.info("Received request to adjust granted memory share to " + sizeOfNewShare + " kilobytes");

		synchronized (this.lock) {

			this.grantedMemoryShare = sizeOfNewShare;
			adaptMemoryResources();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean verifyEmpty() {

		// With a dynamic number of segments this check does not make sense, so we always return <code>true</code>
		return true;
	}
}
