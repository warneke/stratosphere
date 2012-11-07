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

import junit.framework.Assert;

import org.junit.Test;

import eu.stratosphere.sopremo.expressions.EvaluationExpression;
import eu.stratosphere.sopremo.expressions.FunctionCall;
import eu.stratosphere.sopremo.function.FunctionPointerNode;
import eu.stratosphere.sopremo.type.CachingArrayNode;
import eu.stratosphere.sopremo.type.IArrayNode;
import eu.stratosphere.sopremo.type.JsonUtil;

/**
 * @author Arvid Heise
 */
public class SecondOrderFunctionsTest {

	@Test
	public void shouldMapElements() {
		CachingArrayNode result = new CachingArrayNode();
		final IArrayNode input = JsonUtil.createArrayNode(1, 2, 3);
		final FunctionPointerNode pointer = new FunctionPointerNode();
		EvaluationContext context = new EvaluationContext();
		context.getFunctionRegistry().put(MathFunctions.class);
		pointer.setFunctionCall(new FunctionCall("sqr", context, EvaluationExpression.VALUE));
		SecondOrderFunctions.map(result, input, pointer);

		Assert.assertEquals(JsonUtil.createArrayNode(1, 4, 9), result);
	}
}
