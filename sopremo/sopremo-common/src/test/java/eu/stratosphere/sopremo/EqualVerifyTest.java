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
package eu.stratosphere.sopremo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Before;

import eu.stratosphere.sopremo.expressions.ConstantExpression;
import eu.stratosphere.sopremo.expressions.EvaluationExpression;
import eu.stratosphere.util.reflect.BoundTypeUtil;

/**
 * @author arv
 * @param <T>
 */
public class EqualVerifyTest<T> {
	protected T first, second;

	protected Collection<T> more;

	protected Class<T> type;

	/**
	 * Initializes EqualVerifyTest.
	 */
	public EqualVerifyTest() {
		super();
	}

	protected void initInstances(final T first, final T second, final Collection<T> more) {
		this.first = first;
		this.second = second;
		this.more = more;
	}

	protected T createDefaultInstance(@SuppressWarnings("unused") final int index) {
		try {
			return this.type.newInstance();
		} catch (final Exception e) {
			return null;
		}
	}

	protected void createInstances() {
		this.initInstances(this.createDefaultInstance(0), this.createDefaultInstance(1), Collections.singleton(this.createDefaultInstance(2)));
	}

	@SuppressWarnings("unchecked")
	@Before
	public void initInstances() {
		if (this.type == null) {
			this.type =
				(Class<T>) BoundTypeUtil.getBindingOfSuperclass(this.getClass(), EqualVerifyTest.class).getParameters()[0].getType();
			this.createInstances();
		}
	}

	protected void initVerifier(final EqualsVerifier<T> equalVerifier) {
		final BitSet blackBitSet = new BitSet();
		blackBitSet.set(1);
		final ArrayList<Object> redList = new ArrayList<Object>();
		redList.add(null);
		final ArrayList<Object> blackList = new ArrayList<Object>(redList);
		blackList.add(null);
		final Map<Object, Object> blackMap = new HashMap<Object, Object>();
		blackMap.put("test", null);

		equalVerifier
			.suppress(Warning.NULL_FIELDS)
			.suppress(Warning.NONFINAL_FIELDS)
			.withPrefabValues(BitSet.class, new BitSet(), blackBitSet)
			.withPrefabValues(List.class, redList, blackList)
			.withPrefabValues(EvaluationExpression.class, new ConstantExpression("red"),
				new ConstantExpression("black"))
			.withPrefabValues(Map.class, new HashMap<Object, Object>(), blackMap)
			.usingGetClass();
	}

	@SuppressWarnings("unchecked")
	public void shouldComplyEqualsContract() {
		if (this.first == null)
			Assert.fail("Cannot create default instance; "
				+ "please override createDefaultInstance or shouldComplyEqualsContract");
		try {
			// check if there is a equal method
			this.first.getClass().getDeclaredMethod("equals", Object.class);
			this.shouldComplyEqualsContract(this.first, this.second, 
				this.more.toArray((T[]) Array.newInstance(this.type, this.more.size())));
		} catch (NoSuchMethodException e) {
			// then we do not have to test it
		}
	}

	public void shouldComplyEqualsContract(final T first, final T second, final T... more) {
		final EqualsVerifier<T> equalVerifier = EqualsVerifier.forExamples(first, second, more);
		this.initVerifier(equalVerifier);
		equalVerifier.verify();
	}

}