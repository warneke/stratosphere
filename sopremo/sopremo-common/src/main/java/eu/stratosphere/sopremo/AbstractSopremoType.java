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
package eu.stratosphere.sopremo;

import java.io.IOException;

import eu.stratosphere.sopremo.pact.SopremoUtil;

/**
 * Provides basic implementations of the required methods of {@link SopremoType}
 * 
 * @author Arvid Heise
 */
public abstract class AbstractSopremoType implements ISopremoType {
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return toString(this);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public AbstractSopremoType clone() {
		AbstractSopremoType copy = this.createCopy();
		if (SopremoUtil.DEBUG)
			checkCopyType(copy);
		copy.copyPropertiesFrom(this);
		return copy;
	}

	protected void checkCopyType(AbstractSopremoType copy) {
		if (copy.getClass() != this.getClass())
			throw new AssertionError(String.format("Create copy returned wrong type. Expected %s but was %s", getClass(), copy.getClass()));
	}

	@SuppressWarnings("unchecked")
	public final <T extends AbstractSopremoType> T copy() {
		return (T) this.clone();
	}

	/**
	 * Copies the values of the given object. The object has the same type as this object.
	 */
	@Override
	public void copyPropertiesFrom(ISopremoType original) {
	}

	/**
	 * Creates a copy of this object with the same properties for the overriding class.<br/>
	 * A successive call to {@link #copyPropertiesFrom(EvaluationExpression)} ensures that all properties of super
	 * classes are applied.<br/>
	 * However, all final fields should be properly initialized by this method.
	 * 
	 * @return a newly created expression
	 */
	protected abstract AbstractSopremoType createCopy();

	protected Object readResolve() {
		this.initTransients();
		return this;
	}

	protected void initTransients() {
		SopremoUtil.initTransientFields(this);
	}

	/**
	 * Returns a string representation of the given {@link SopremoType}.
	 * 
	 * @param type
	 *        the SopremoType that should be used
	 * @return the string representation
	 */
	public static String toString(final ISopremoType type) {
		final StringBuilder builder = new StringBuilder();
		try {
			type.appendAsString(builder);
		} catch (IOException e) {
			// cannot happen
		}
		return builder.toString();
	}
}
