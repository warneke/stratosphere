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

import eu.stratosphere.sopremo.type.IArrayNode;
import eu.stratosphere.sopremo.type.IJsonNode;
import eu.stratosphere.sopremo.type.SubArrayNode;
import eu.stratosphere.util.reflect.ReflectUtil;

/**
 * @author Arvid Heise
 */
public abstract class SopremoVarargFunction1<Arg1 extends IJsonNode> extends SopremoVarargFunction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3723608612033386096L;

	private final SubArrayNode varargArguments = new SubArrayNode();

	/**
	 * Initializes SopremoVarargFunction1.
	 * 
	 * @param name
	 */
	public SopremoVarargFunction1(String name) {
		super(name, 1);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.function.Callable#call(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public IJsonNode call(IArrayNode params) {
		this.varargArguments.init(params, 1);
		return this.call((Arg1) params.get(0), this.varargArguments);
	}
	
	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.function.Callable#clone()
	 */
	@Override
	public SopremoFunction clone() {
		return ReflectUtil.newInstance(this.getClass(), getName());
	}

	protected abstract IJsonNode call(Arg1 arg1, IArrayNode varargs);
}
