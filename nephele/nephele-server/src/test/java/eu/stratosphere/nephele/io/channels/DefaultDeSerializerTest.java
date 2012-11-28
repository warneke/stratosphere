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

import static org.junit.Assert.fail;

import org.junit.Test;

import eu.stratosphere.nephele.io.channels.serialization.SerializationTestType;

public class DefaultDeSerializerTest extends AbstractDeSerializerTest {

	@Test
	public void testSequenceOfIntegersWithAlignedBuffers() {

		try {
			testSequenceOfTypes(new IntTypeIterator(this.rnd, NUM_INTS), 8192, false);
			testSequenceOfTypes(new IntTypeIterator(this.rnd, NUM_INTS), 8192, true);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Test encountered an unexpected exception.");
		}
	}

	@Test
	public void testSequenceOfIntegersWithUnalignedBuffers() {

		try {
			testSequenceOfTypes(new IntTypeIterator(this.rnd, NUM_INTS), 8191, false);
			testSequenceOfTypes(new IntTypeIterator(this.rnd, NUM_INTS), 8191, true);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Test encountered an unexpected exception.");
		}
	}

	@Test
	public void testRandomTypes() {

		try {
			// test with an odd buffer size to force many unaligned cases
			testSequenceOfTypes(new RandomTypeIterator(this.rnd, NUM_TYPES), 8192, false);
			testSequenceOfTypes(new RandomTypeIterator(this.rnd, NUM_TYPES), 8192, true);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Test encountered an unexpected exception.");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RecordSerializer<SerializationTestType> createSerializer() {

		return new DefaultRecordSerializer<SerializationTestType>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RecordDeserializer<SerializationTestType> createDeserializer() {

		return new DefaultRecordDeserializer<SerializationTestType>(null);
	}

}
