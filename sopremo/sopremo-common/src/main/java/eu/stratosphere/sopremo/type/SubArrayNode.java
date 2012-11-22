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
package eu.stratosphere.sopremo.type;

import java.util.Iterator;

import com.google.common.collect.Iterators;

/**
 * Represents a reusable view on arrays.
 * 
 * @author Arvid Heise
 */
public class SubArrayNode extends AbstractArrayNode {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6671341164046217136L;

	private IArrayNode originalArray = ArrayNode.EMPTY;

	private int startIndex, length;

	public void init(IArrayNode originalArray, int startIndex, int length) {
		this.originalArray = originalArray;
		this.startIndex = startIndex;
		this.length = length;
	}

	public void init(IArrayNode originalArray, int startIndex) {
		this.init(originalArray, startIndex, originalArray.size() - startIndex);
	}

	public void setSize(int length) {
		if (length < 0)
			throw new IllegalArgumentException();
		this.length = length;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.type.IArrayNode#size()
	 */
	@Override
	public int size() {
		return this.length;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.type.IArrayNode#add(eu.stratosphere.sopremo.type.IJsonNode)
	 */
	@Override
	public IArrayNode add(IJsonNode node) {
		this.originalArray.add(this.startIndex + this.length, node);
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.type.IArrayNode#add(int, eu.stratosphere.sopremo.type.IJsonNode)
	 */
	@Override
	public IArrayNode add(int index, IJsonNode element) {
		this.originalArray.add(this.startIndex + index, element);
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.type.IArrayNode#get(int)
	 */
	@Override
	public IJsonNode get(int index) {
		return this.originalArray.get(this.startIndex + index);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.type.IArrayNode#set(int, eu.stratosphere.sopremo.type.IJsonNode)
	 */
	@Override
	public IJsonNode set(int index, IJsonNode node) {
		return this.originalArray.set(this.startIndex + index, node);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.type.IArrayNode#remove(int)
	 */
	@Override
	public IJsonNode remove(int index) {
		if (index < 0 || index >= this.size())
			return MissingNode.getInstance();

		this.length--;
		return this.originalArray.remove(this.startIndex + index);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.type.IArrayNode#clear()
	 */
	@Override
	public void clear() {
		while (this.length-- > 0)
			this.originalArray.remove(this.startIndex);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.type.IStreamArrayNode#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return this.length > 0;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<IJsonNode> iterator() {
		final Iterator<IJsonNode> iterator = this.originalArray.iterator();
		Iterators.advance(iterator, this.startIndex);
		return Iterators.limit(iterator, this.length);
	}
}
