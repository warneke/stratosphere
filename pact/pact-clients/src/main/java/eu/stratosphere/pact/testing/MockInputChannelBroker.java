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
package eu.stratosphere.pact.testing;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import eu.stratosphere.nephele.event.task.AbstractEvent;
import eu.stratosphere.nephele.io.channels.AbstractInputChannel;
import eu.stratosphere.nephele.io.channels.Buffer;
import eu.stratosphere.nephele.io.channels.ByteBufferedInputChannelBroker;
import eu.stratosphere.nephele.io.compression.CompressionException;
import eu.stratosphere.nephele.io.compression.Decompressor;
import eu.stratosphere.nephele.taskmanager.routing.RoutingService;
import eu.stratosphere.nephele.taskmanager.transferenvelope.TransferEnvelope;

/**
 * @author Arvid Heise
 */
public class MockInputChannelBroker implements ByteBufferedInputChannelBroker, MockChannelBroker {

	private final AbstractInputChannel<?> inputChannel;

	private RoutingService routingService;

	private Queue<TransferEnvelope> queuedEnvelopes = new LinkedList<TransferEnvelope>();

	/**
	 * Initializes MockInputChannelBroker.
	 * 
	 * @param inputChannel
	 * @param transitBufferPool
	 */
	public MockInputChannelBroker(AbstractInputChannel<?> inputChannel, RoutingService routingService) {
		this.inputChannel = inputChannel;
		this.routingService = routingService;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.nephele.io.channels.bytebuffered.ByteBufferedInputChannelBroker#releaseConsumedReadBuffer()
	 */
	@Override
	public void releaseConsumedReadBuffer(final Buffer buffer) {
		TransferEnvelope transferEnvelope = null;
		synchronized (this.queuedEnvelopes) {

			if (this.queuedEnvelopes.isEmpty())
				return;

			transferEnvelope = this.queuedEnvelopes.poll();
		}

		final Buffer consumedBuffer = transferEnvelope.getBuffer();
		if (consumedBuffer == null)
			return;

		// Recycle consumed read buffer
		buffer.recycleBuffer();
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.nephele.io.channels.bytebuffered.ByteBufferedInputChannelBroker#getReadBufferToConsume()
	 */
	@Override
	public Buffer getReadBufferToConsume() {
		TransferEnvelope transferEnvelope = null;

		synchronized (this.queuedEnvelopes) {

			if (this.queuedEnvelopes.isEmpty())
				return null;

			transferEnvelope = this.queuedEnvelopes.peek();

			// If envelope does not have a buffer, remove it immediately
			if (transferEnvelope.getBuffer() == null)
				this.queuedEnvelopes.poll();
		}

		// Make sure we have all necessary buffers before we go on
		if (transferEnvelope.getBuffer() == null) {

			// No buffers necessary
			final List<AbstractEvent> eventList = transferEnvelope.getEventList();
			if (eventList != null)
				if (!eventList.isEmpty()) {
					final Iterator<AbstractEvent> it = eventList.iterator();
					while (it.hasNext())
						this.inputChannel.processEvent(it.next());
				}

			return null;
		}

		final Buffer buffer = transferEnvelope.getBuffer(); // No need to copy anything

		// Process events
		final List<AbstractEvent> eventList = transferEnvelope.getEventList();
		if (eventList != null)
			if (!eventList.isEmpty()) {
				final Iterator<AbstractEvent> it = eventList.iterator();
				while (it.hasNext())
					this.inputChannel.processEvent(it.next());
			}

		return buffer;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * eu.stratosphere.nephele.io.channels.bytebuffered.ByteBufferedInputChannelBroker#transferEventToOutputChannel(
	 * eu.stratosphere.nephele.event.task.AbstractEvent)
	 */
	@Override
	public void transferEventToOutputChannel(AbstractEvent event) throws IOException, InterruptedException {
		final TransferEnvelope ephemeralTransferEnvelope = new TransferEnvelope(0, this.inputChannel.getJobID(),
			this.inputChannel.getID());
		ephemeralTransferEnvelope.addEvent(event);
		this.routingService.routeEnvelopeFromInputChannel(ephemeralTransferEnvelope);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * eu.stratosphere.pact.testing.MockChannelBroker#queueTransferEnvelope(eu.stratosphere.nephele.event.task.AbstractEvent
	 * )
	 */
	@Override
	public void queueTransferEnvelope(TransferEnvelope transferEnvelope) {
		synchronized (this.queuedEnvelopes) {
			this.queuedEnvelopes.add(transferEnvelope);
		}

		// Notify the channel about the new data
		this.inputChannel.checkForNetworkEvents();
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.pact.testing.MockChannelBroker#getChannel()
	 */
	@Override
	public AbstractInputChannel<?> getChannel() {
		return this.inputChannel;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.nephele.io.channels.bytebuffered.ByteBufferedInputChannelBroker#getDecompressor()
	 */
	@Override
	public Decompressor getDecompressor() throws CompressionException {

		return null;
	}
}
