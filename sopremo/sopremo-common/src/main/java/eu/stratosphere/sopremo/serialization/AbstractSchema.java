package eu.stratosphere.sopremo.serialization;

import it.unimi.dsi.fastutil.ints.IntSet;

import java.io.IOException;
import java.util.Arrays;

import eu.stratosphere.pact.common.type.Value;
import eu.stratosphere.sopremo.AbstractSopremoType;
import eu.stratosphere.sopremo.pact.JsonNodeWrapper;
import eu.stratosphere.sopremo.pact.SopremoUtil;

/**
 * Base class for all schema that build upon {@link JsonNodeWrapper}.
 * 
 * @author Arvid Heise
 */
public abstract class AbstractSchema extends AbstractSopremoType implements Schema {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1012715697040531298L;

	private final Class<? extends Value>[] pactSchema;

	private final IntSet keyIndices;

	@SuppressWarnings("unchecked")
	protected AbstractSchema(final int numFields, final IntSet keyIndices) {
		if (keyIndices == null)
			throw new NullPointerException();
		this.keyIndices = keyIndices;
		this.pactSchema = new Class[numFields];
		Arrays.fill(this.pactSchema, JsonNodeWrapper.class);
	}

	@Override
	public IntSet getKeyIndices() {
		return this.keyIndices;
	}

	@Override
	public Class<? extends Value>[] getPactSchema() {
		return this.pactSchema;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.ISopremoType#appendAsString(java.lang.Appendable)
	 */
	@Override
	public void appendAsString(Appendable appendable) throws IOException {
		SopremoUtil.append(appendable, this.getClass().getSimpleName(), " [");
		for (int index = 0; index < this.pactSchema.length; index++)
			appendable.append(this.pactSchema[index].getSimpleName()).append(' ');
		appendable.append(']');
	}
}
