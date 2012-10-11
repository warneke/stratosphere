package eu.stratosphere.pact.runtime.sort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import eu.stratosphere.nephele.execution.Environment;
import eu.stratosphere.nephele.services.iomanager.IOManager;
import eu.stratosphere.nephele.services.memorymanager.DynamicMemoryManager;
import eu.stratosphere.nephele.services.memorymanager.MemoryAllocationException;
import eu.stratosphere.nephele.services.memorymanager.MemoryManager;
import eu.stratosphere.nephele.services.memorymanager.MemorySegment;
import eu.stratosphere.nephele.template.AbstractInvokable;
import eu.stratosphere.pact.common.generic.types.TypeComparator;
import eu.stratosphere.pact.common.generic.types.TypeSerializer;
import eu.stratosphere.pact.common.util.MutableObjectIterator;
import eu.stratosphere.pact.runtime.util.MathUtils;

public class DynamicUnilateralSortMerger<E> extends UnilateralSortMerger<E> {

	private static final Log LOG = LogFactory.getLog(DynamicUnilateralSortMerger.class);

	private final AbstractInvokable parentTask;

	private final TypeSerializer<E> serializer;

	private final TypeComparator<E> comparator;

	private final DynamicMemoryManager dynamicMemoryManager;

	private AtomicInteger nextIDforElement;

	public DynamicUnilateralSortMerger(final MemoryManager memoryManager, final IOManager ioManager,
			final MutableObjectIterator<E> input, final AbstractInvokable parentTask,
			final TypeSerializer<E> serializer, final TypeComparator<E> comparator, final long initialMemory,
			final int maxNumFileHandles, final float startSpillingFraction) throws IOException,
			MemoryAllocationException {

		super(memoryManager, ioManager, input, parentTask, serializer, comparator, initialMemory, -1,
			maxNumFileHandles, startSpillingFraction, false);

		LOG.info("Dynamic unilateral sort merger started with " + initialMemory + " bytes of initial memory");

		if (!(memoryManager instanceof DynamicMemoryManager)) {
			throw new IllegalArgumentException(
				"The dynamic unilateral sort merger can only be used together with the dynamic memory manager");
		}

		this.parentTask = parentTask;
		this.serializer = serializer;
		this.comparator = comparator;
		this.dynamicMemoryManager = (DynamicMemoryManager) memoryManager;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ThreadBase<E> getReadingThread(final ExceptionHandler<IOException> exceptionHandler,
			final MutableObjectIterator<E> reader, final CircularQueues<E> queues, final AbstractInvokable parentTask,
			final TypeSerializer<E> serializer, final long startSpillingBytes) {

		final CircularElement<E> element = queues.empty.peek();
		if (element == null) {
			throw new IllegalStateException("Cannot determine the number of segments per sort buffer");
		}

		final long roundedCapacity = this.memoryManager.roundDownToPageSizeMultiple(element.buffer.getCapacity());
		final int numberOfSegments = MathUtils.checkedDownCast(roundedCapacity / this.memoryManager.getPageSize());

		LOG.info("Determined the number of segments per sort buffer to be " + numberOfSegments);

		long totalMemory = 0L;
		final Iterator<CircularElement<E>> it = queues.empty.iterator();
		while (it.hasNext()) {
			totalMemory += it.next().buffer.getCapacity();
		}

		final float startSpillingFraction = (float) startSpillingBytes / (float) totalMemory;

		this.nextIDforElement = new AtomicInteger(queues.empty.size());

		return new DynamicReadingThread<E>(exceptionHandler, reader, queues, serializer.createInstance(), parentTask,
			totalMemory, startSpillingBytes, startSpillingFraction, numberOfSegments, this);
	}

	private static final class DynamicReadingThread<E> extends ThreadBase<E> {

		/**
		 * The input channels to read from.
		 */
		private final MutableObjectIterator<E> reader;

		/**
		 * The object into which the thread reads the data from the input.
		 */
		private final E readTarget;

		/**
		 * The fraction of the buffers that must be full before the spilling starts.
		 */
		private long startSpillingBytes;

		private final float startSpillingFraction;

		private long totalMemory;

		private final DynamicUnilateralSortMerger<E> sortMerger;

		private final int numberOfSegmentsPerSortBuffer;

		public DynamicReadingThread(final ExceptionHandler<IOException> exceptionHandler,
				final MutableObjectIterator<E> reader, final CircularQueues<E> queues, final E readTarget,
				final AbstractInvokable parentTask, final long totalMemory, final long startSpillingBytes,
				final float startSpillingFraction, final int numberOfSegmentsPerSortBuffer,
				final DynamicUnilateralSortMerger<E> sortMerger) {

			super(exceptionHandler,
				"SortMerger Reading Thread of " + getTaskNameWithIndex(parentTask.getEnvironment()), queues, parentTask);

			// members
			this.reader = reader;
			this.readTarget = readTarget;
			this.startSpillingBytes = startSpillingBytes;
			this.startSpillingFraction = startSpillingFraction;
			this.sortMerger = sortMerger;
			this.numberOfSegmentsPerSortBuffer = numberOfSegmentsPerSortBuffer;
			this.totalMemory = totalMemory;
		}

		/**
		 * The entry point for the thread. Gets a buffer for all threads and then loops as long as there is input
		 * available.
		 */
		@Override
		public void go() throws IOException {

			final MutableObjectIterator<E> reader = this.reader;

			final E current = this.readTarget;
			E leftoverRecord = null;

			CircularElement<E> element = null;
			long bytesUntilSpilling = this.startSpillingBytes;
			boolean done = false;

			// check if we should directly spill
			if (bytesUntilSpilling < 1L) {
				bytesUntilSpilling = 0L;

				// add the spilling marker
				System.out.println("Requesting spilling 1");
				this.queues.sort.add(UnilateralSortMerger.<E> spillingMarker());
			}

			// now loop until all channels have no more input data
			while (!done && isRunning()) {

				// grab the next buffer
				while (element == null) {
					try {
						element = this.queues.empty.take();
					} catch (InterruptedException iex) {
						if (isRunning()) {
							LOG.error("Reading thread was interrupted (without being shut down) while grabbing a buffer. "
								+
								"Retrying to grab buffer...");
						} else {
							return;
						}
					}
				}

				// get the new buffer and check it
				final NormalizedKeySorter<E> buffer = element.buffer;
				if (!buffer.isEmpty()) {
					throw new IOException("New buffer is not empty.");
				}

				if (LOG.isDebugEnabled()) {
					LOG.debug("Retrieved empty read buffer " + element.id + ".");
				}

				// write the last leftover pair, if we have one
				if (leftoverRecord != null) {
					if (!buffer.write(leftoverRecord)) {
						throw new IOException(
							"Record could not be written to empty buffer: Serialized record exceeds buffer capacity.");
					}
					leftoverRecord = null;
				}

				// we have two distinct code paths, depending on whether the spilling
				// threshold will be crossed in the current buffer, or not.
				if (bytesUntilSpilling > 0 && buffer.getCapacity() >= bytesUntilSpilling) {

					boolean fullBuffer = false;

					// spilling will be triggered while this buffer is filled
					// loop until the buffer is full or the reader is exhausted
					while (isRunning() && reader.next(current))
					{
						if (!buffer.write(current)) {
							leftoverRecord = current;
							fullBuffer = true;
							break;
						}
						if (bytesUntilSpilling - buffer.getOccupancy() <= 0) {

							try {
								bytesUntilSpilling = requestMoreMemory(bytesUntilSpilling);
							} catch (InterruptedException ie) {
								if (isRunning()) {
									LOG.error("Reading thread was interrupted (without being shut down) while requesting additional buffer");
								} else {
									return;
								}
							}
							if (bytesUntilSpilling < 0L) {
								bytesUntilSpilling = 0L;

								// send the spilling marker
								System.out.println("Requesting spilling 2");
								final CircularElement<E> SPILLING_MARKER = spillingMarker();
								this.queues.sort.add(SPILLING_MARKER);

								// we drop out of this loop and continue with the loop that
								// does not have the check
								break;
							}
						}
					}

					if (fullBuffer) {
						// buffer is full. it may be that the last element would have crossed the
						// spilling threshold, so check it
						if (bytesUntilSpilling > 0L) {
							bytesUntilSpilling -= buffer.getCapacity();
							if (bytesUntilSpilling <= 0L) {

								try {
									bytesUntilSpilling = requestMoreMemory(bytesUntilSpilling);
								} catch (InterruptedException ie) {
									if (isRunning()) {
										LOG.error("Reading thread was interrupted (without being shut down) while requesting additional buffer");
									} else {
										return;
									}
								}
								if (bytesUntilSpilling < 0L) {
									bytesUntilSpilling = 0L;
									// send the spilling marker
									System.out.println("Requesting spilling 3");
									final CircularElement<E> SPILLING_MARKER = spillingMarker();
									this.queues.sort.add(SPILLING_MARKER);
								}
							}
						}

						// send the buffer
						if (LOG.isDebugEnabled()) {
							LOG.debug("Emitting full buffer from reader thread: " + element.id + ".");
						}
						this.queues.sort.add(element);
						element = null;
						continue;
					}
				}
				else if (bytesUntilSpilling > 0L) {
					// this block must not be entered, if the last loop dropped out because
					// the input is exhausted.
					bytesUntilSpilling -= buffer.getCapacity();
					if (bytesUntilSpilling <= 0L) {

						try {
							bytesUntilSpilling = requestMoreMemory(bytesUntilSpilling);
						} catch (InterruptedException ie) {
							if (isRunning()) {
								LOG.error("Reading thread was interrupted (without being shut down) while requesting additional buffer");
							} else {
								return;
							}
						}
						if (bytesUntilSpilling < 0L) {
							bytesUntilSpilling = 0L;
							// send the spilling marker
							System.out.println("Requesting spilling 4");
							final CircularElement<E> SPILLING_MARKER = spillingMarker();
							this.queues.sort.add(SPILLING_MARKER);
						}
					}
				}

				// no spilling will be triggered (any more) while this buffer is being processed
				// loop until the buffer is full or the reader is exhausted
				while (isRunning() && reader.next(current)) {
					if (!buffer.write(current)) {
						leftoverRecord = current;
						break;
					}
				}

				// check whether the buffer is exhausted or the reader is
				if (leftoverRecord != null) {
					if (LOG.isDebugEnabled()) {
						LOG.debug("Emitting full buffer from reader thread: " + element.id + ".");
					}
				}
				else {
					done = true;
					if (LOG.isDebugEnabled()) {
						LOG.debug("Emitting final buffer from reader thread: " + element.id + ".");
					}
				}

				// we can use add to add the element because we have no capacity restriction
				if (!buffer.isEmpty()) {
					this.queues.sort.add(element);
				}
				else {
					this.queues.empty.add(element);
				}
				element = null;
			}

			// we read all there is to read, or we are no longer running
			if (!isRunning()) {
				return;
			}

			// add the sentinel to notify the receivers that the work is done
			// send the EOF marker
			final CircularElement<E> EOF_MARKER = endMarker();
			this.queues.sort.add(EOF_MARKER);
			LOG.debug("Reading thread done.");
			System.out.println("Reader finished with " + this.totalMemory);
		}

		private long requestMoreMemory(long bytesUntilSpilling) throws InterruptedException {

			System.out.println("Old value for bytes until spilling: " + bytesUntilSpilling);

			final CircularElement<E> element = this.sortMerger.requestMoreMemory(this.numberOfSegmentsPerSortBuffer);
			if (element == null) {
				return -1L;
			}

			if (!this.queues.empty.add(element)) {
				throw new IllegalStateException("Unable to add newly allocated sort buffer to queue");
			}

			// Update amount of total memory
			final long bytesProcessed = this.startSpillingBytes - bytesUntilSpilling;
			System.out.println("Bytes processed: " + bytesProcessed);
			this.totalMemory += element.buffer.getCapacity();
			System.out.println("New total memory: " + this.totalMemory);
			this.startSpillingBytes = (long) (this.totalMemory * this.startSpillingFraction);
			System.out.println("New start spilling bytes: " + this.startSpillingBytes);
			bytesUntilSpilling = this.startSpillingBytes - bytesProcessed;

			System.out.println("New value for bytes until spilling: " + bytesUntilSpilling);

			return bytesUntilSpilling;
		}
	}

	private CircularElement<E> requestMoreMemory(final int numberOfSegments) throws InterruptedException {

		final ArrayList<MemorySegment> newSegments = new ArrayList<MemorySegment>(numberOfSegments);

		try {
			this.dynamicMemoryManager.allocateAdditionalPages(this.parentTask, newSegments, numberOfSegments);
		} catch (MemoryAllocationException e) {
			return null;
		}

		LOG.info("Allocated " + newSegments.size() + " additional memory segments for "
			+ getTaskNameWithIndex(this.parentTask.getEnvironment()));

		final TypeComparator<E> comp = this.comparator.duplicate();
		final NormalizedKeySorter<E> buffer = new NormalizedKeySorter<E>(this.serializer, comp, newSegments);

		return new CircularElement<E>(this.nextIDforElement.getAndIncrement(), buffer);
	}

	private static String getTaskNameWithIndex(final Environment environment) {

		final StringBuilder sb = new StringBuilder(environment.getTaskName());
		sb.append(" (");
		sb.append(environment.getIndexInSubtaskGroup() + 1);
		sb.append('/');
		sb.append(environment.getCurrentNumberOfSubtasks());
		sb.append(')');

		return sb.toString();
	}
}
