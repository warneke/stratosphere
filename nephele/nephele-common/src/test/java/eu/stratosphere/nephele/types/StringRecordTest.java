package eu.stratosphere.nephele.types;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.DataInput;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import eu.stratosphere.nephele.util.CommonTestUtils;

/**
 * 
 * @author Mathias Peters <mathias.peters@informatik.hu-berlin.de>
 * TODO: {@link StringRecord} has a lot of public methods that need to be tested. 
 */
public class StringRecordTest {

	
	@Mock
	private DataInput inputMock;
	
	
	@Before
	public void setUp()
	{
		initMocks(this);
	}
	
	/**
	 * Tests the serialization/deserialization of the {@link StringRecord} class.
	 */
	@Test
	public void testStringRecord() {

		final StringRecord orig = new StringRecord("Test Record");

		try {

			final StringRecord copy = (StringRecord) CommonTestUtils.createCopy(orig);

			assertEquals(orig.getLength(), copy.getLength());
			assertEquals(orig.toString(), copy.toString());
			assertEquals(orig, copy);
			assertEquals(orig.hashCode(), copy.hashCode());

		} catch (IOException ioe) {
			fail(ioe.getMessage());
		}
	}
	
	@Test
	public void shouldReadProperInputs() throws IOException
	{
		when(inputMock.readBoolean()).thenReturn(true);
		when(inputMock.readInt()).thenReturn(10);
		
		
		String readString = StringRecord.readString(inputMock);
		assertThat(readString, is(not(nullValue())));
		
		
	}
	
	
	@Test
	public void shouldReadNegativeInputs() throws IOException
	{
		when(inputMock.readBoolean()).thenReturn(true);
		when(inputMock.readInt()).thenReturn(-1);
		
		String readString = StringRecord.readString(inputMock);
		assertThat(readString, is(nullValue()));
	}
}