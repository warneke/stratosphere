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

import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.berkeley.icsi.memngt.protocols.ClientToDaemonProtocol;
import eu.stratosphere.nephele.util.StringUtils;

class AsynchronousMemoryRequester implements Runnable {

	private static final Log LOG = LogFactory.getLog(AsynchronousMemoryRequester.class);

	private final int pid;

	private final ClientToDaemonProtocol memoryNegiatorDaemon;

	private final MemoryPool memoryPool;

	private final Thread thread;

	private final AtomicBoolean isShutDown = new AtomicBoolean(false);

	private int amountOfMemoryToRequest = -1;

	public AsynchronousMemoryRequester(final int pid, final ClientToDaemonProtocol memoryNegiatorDaemon,
			final MemoryPool memoryPool) {
		this.pid = pid;
		this.memoryNegiatorDaemon = memoryNegiatorDaemon;
		this.memoryPool = memoryPool;
		this.thread = new Thread(this, "AsynchronousMemoryRequester");
		this.thread.start();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {

		while (!this.isShutDown.get()) {

			int amountOfMemoryToRequest;
			long start;
			synchronized (this) {
				try {
					while (this.amountOfMemoryToRequest <= 0) {
						wait();
					}
				} catch (InterruptedException e) {
					continue;
				}

				start = System.currentTimeMillis();
				amountOfMemoryToRequest = this.amountOfMemoryToRequest;
			}

			try {
				if (this.memoryNegiatorDaemon.requestAdditionalMemory(this.pid, amountOfMemoryToRequest)) {
					this.memoryPool.increaseGrantedShareAndAdjust(amountOfMemoryToRequest);
					LOG.info("Finished request for additional " + amountOfMemoryToRequest
						+ " kilobytes of memory after " + (System.currentTimeMillis() - start) + " milliseconds");
				}
			} catch (Exception e) {
				LOG.error(StringUtils.stringifyException(e));
			}

			synchronized (this) {
				this.amountOfMemoryToRequest = -1;
			}
		}
	}

	public void shutdown() {

		if (!this.isShutDown.compareAndSet(false, true)) {
			return;
		}

		synchronized (this) {
			notify();
		}

		this.thread.interrupt();
		try {
			this.thread.join();
		} catch (InterruptedException e) {
		}
	}

	public void requestAdditionalMemory(final int amountOfMemoryToRequest) {

		LOG.info("Requesting " + amountOfMemoryToRequest + " of additional memory at " + System.currentTimeMillis());

		synchronized (this) {
			if (this.amountOfMemoryToRequest > 0) {
				LOG.info("Another request for additional memory is still in progress, aborting...");
				return;
			}
			this.amountOfMemoryToRequest = amountOfMemoryToRequest;
			notify();
		}
	}
}
