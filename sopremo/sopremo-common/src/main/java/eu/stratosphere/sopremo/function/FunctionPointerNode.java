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
package eu.stratosphere.sopremo.function;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import eu.stratosphere.sopremo.expressions.FunctionCall;
import eu.stratosphere.sopremo.type.AbstractJsonNode;
import eu.stratosphere.sopremo.type.IJsonNode;

/**
 * @author Arvid Heise
 */
public class FunctionPointerNode extends AbstractJsonNode {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1790138677031870181L;

	private FunctionCall functionCall;

	/**
	 * Initializes FunctionPointerNode.
	 */
	public FunctionPointerNode() {
	}

	/**
	 * Sets the functionCall to the specified value.
	 * 
	 * @param functionCall
	 *        the functionCall to set
	 */
	public void setFunctionCall(FunctionCall functionCall) {
		if (functionCall == null)
			throw new NullPointerException("functionCall must not be null");

		this.functionCall = functionCall;
	}

	/**
	 * Returns the functionCall.
	 * 
	 * @return the functionCall
	 */
	public FunctionCall getFunctionCall() {
		return this.functionCall;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.type.IJsonNode#clear()
	 */
	@Override
	public void clear() {
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.type.IJsonNode#copyValueFrom(eu.stratosphere.sopremo.type.IJsonNode)
	 */
	@Override
	public void copyValueFrom(IJsonNode otherNode) {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.type.AbstractJsonNode#getType()
	 */
	@Override
	public Type getType() {
		return Type.CustomNode;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.type.AbstractJsonNode#read(java.io.DataInput)
	 */
	@Override
	public void read(DataInput in) throws IOException {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.type.AbstractJsonNode#write(java.io.DataOutput)
	 */
	@Override
	public void write(DataOutput out) throws IOException {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.type.AbstractJsonNode#getJavaValue()
	 */
	@Override
	public SopremoFunction getJavaValue() {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.type.AbstractJsonNode#compareToSameType(eu.stratosphere.sopremo.type.IJsonNode)
	 */
	@Override
	public int compareToSameType(IJsonNode other) {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.type.AbstractJsonNode#hashCode()
	 */
	@Override
	public int hashCode() {
		throw new UnsupportedOperationException();
	}

	public IJsonNode evaluate(IJsonNode node, IJsonNode target) {
		return this.functionCall.evaluate(node, target);
	}

}
