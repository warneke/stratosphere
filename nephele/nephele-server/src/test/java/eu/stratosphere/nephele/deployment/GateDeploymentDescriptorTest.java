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

package eu.stratosphere.nephele.deployment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import eu.stratosphere.nephele.io.GateID;
import eu.stratosphere.nephele.io.channels.ChannelID;
import eu.stratosphere.nephele.io.channels.ChannelType;
import eu.stratosphere.nephele.io.compression.CompressionLevel;
import eu.stratosphere.nephele.util.ServerTestUtils;

/**
 * This class contains unit tests for the {@link GateDeploymentDescriptor} class.
 * 
 * @author warneke
 */
public class GateDeploymentDescriptorTest {

	/**
	 * Tests the constructor of the {@link GateDeploymentDescriptor} class with valid arguments.
	 */
	@Test
	public void testConstructorWithValidArguments() {

		final GateID gateID = GateID.generate();
		final ChannelType channelType = ChannelType.INMEMORY;
		final CompressionLevel compressionLevel = CompressionLevel.HEAVY_COMPRESSION;
		final List<ChannelDeploymentDescriptor> channels = new ArrayList<ChannelDeploymentDescriptor>(0);

		final GateDeploymentDescriptor gdd = new GateDeploymentDescriptor(gateID, channelType, compressionLevel, false,
			channels);

		assertEquals(gateID, gdd.getGateID());
		assertEquals(channelType, gdd.getChannelType());
		assertEquals(compressionLevel, gdd.getCompressionLevel());
		assertEquals(channels.size(), gdd.getNumberOfChannelDescriptors());
	}

	/**
	 * Tests the constructor of the {@link GateDeploymentDescriptor} class with valid arguments.
	 */
	@Test
	public void testConstructorWithInvalidArguments() {

		final GateID gateID = GateID.generate();
		final ChannelType channelType = ChannelType.INMEMORY;
		final CompressionLevel compressionLevel = CompressionLevel.HEAVY_COMPRESSION;
		final List<ChannelDeploymentDescriptor> channels = new ArrayList<ChannelDeploymentDescriptor>(0);

		boolean firstExceptionCaught = false;
		boolean secondExceptionCaught = false;
		boolean thirdExceptionCaught = false;
		boolean forthExceptionCaught = false;

		try {
			new GateDeploymentDescriptor(null, channelType, compressionLevel, false, channels);
		} catch (IllegalArgumentException e) {
			firstExceptionCaught = true;
		}

		try {
			new GateDeploymentDescriptor(gateID, null, compressionLevel, false, channels);
		} catch (IllegalArgumentException e) {
			secondExceptionCaught = true;
		}

		try {
			new GateDeploymentDescriptor(gateID, channelType, null, false, channels);
		} catch (IllegalArgumentException e) {
			thirdExceptionCaught = true;
		}

		try {
			new GateDeploymentDescriptor(gateID, channelType, compressionLevel, false, null);
		} catch (IllegalArgumentException e) {
			forthExceptionCaught = true;
		}

		if (!firstExceptionCaught) {
			fail("First argument was illegal but not detected");
		}

		if (!secondExceptionCaught) {
			fail("Second argument was illegal but not detected");
		}

		if (!thirdExceptionCaught) {
			fail("Third argument was illegal but not detected");
		}

		if (!forthExceptionCaught) {
			fail("Forth argument was illegal but not detected");
		}
	}

	/**
	 * Tests the serialization/deserialization of the {@link GateDeploymentDescriptor} class.
	 */
	@Test
	public void testSerialization() {

		final GateID gateID = GateID.generate();
		final ChannelType channelType = ChannelType.INMEMORY;
		final CompressionLevel compressionLevel = CompressionLevel.HEAVY_COMPRESSION;
		final List<ChannelDeploymentDescriptor> channels = new ArrayList<ChannelDeploymentDescriptor>(0);
		final ChannelDeploymentDescriptor cdd = new ChannelDeploymentDescriptor(ChannelID.generate(),
			ChannelID.generate());
		channels.add(cdd);

		final GateDeploymentDescriptor orig = new GateDeploymentDescriptor(gateID, channelType, compressionLevel,
			false, channels);

		final GateDeploymentDescriptor copy = ServerTestUtils.createCopy(orig);

		assertFalse(orig.getGateID() == copy.getGateID());

		assertEquals(orig.getGateID(), copy.getGateID());
		assertEquals(orig.getChannelType(), copy.getChannelType());
		assertEquals(orig.getCompressionLevel(), copy.getCompressionLevel());
		assertEquals(orig.getNumberOfChannelDescriptors(), copy.getNumberOfChannelDescriptors());
		assertEquals(orig.getChannelDescriptor(0).getOutputChannelID(), copy.getChannelDescriptor(0)
			.getOutputChannelID());
		assertEquals(orig.getChannelDescriptor(0).getInputChannelID(), copy.getChannelDescriptor(0).getInputChannelID());
	}
}
