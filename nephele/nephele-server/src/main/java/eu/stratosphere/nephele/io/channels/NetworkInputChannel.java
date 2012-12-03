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

package eu.stratosphere.nephele.io.channels;

import eu.stratosphere.nephele.io.InputGate;
import eu.stratosphere.nephele.io.compression.CompressionLevel;
import eu.stratosphere.nephele.types.Record;

public final class NetworkInputChannel<T extends Record> extends AbstractInputChannel<T> {

	public NetworkInputChannel(final InputGate<T> inputGate, final int channelIndex, final ChannelID channelID,
			final ChannelID connectedChannelID, final CompressionLevel compressionLevel,
			final RecordDeserializer<T> deserializer) {
		super(inputGate, channelIndex, channelID, connectedChannelID, compressionLevel, deserializer);
	}

	@Override
	public ChannelType getType() {

		return ChannelType.NETWORK;
	}
}