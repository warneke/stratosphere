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

import eu.stratosphere.sopremo.cache.FunctionCache;
import eu.stratosphere.sopremo.cache.FunctionCacheCache;
import eu.stratosphere.sopremo.cache.NodeCache;
import eu.stratosphere.sopremo.function.FunctionNode;
import eu.stratosphere.sopremo.function.SopremoFunction;
import eu.stratosphere.sopremo.function.SopremoFunction2;
import eu.stratosphere.sopremo.function.SopremoFunction3;
import eu.stratosphere.sopremo.operator.Name;
import eu.stratosphere.sopremo.packages.BuiltinProvider;
import eu.stratosphere.sopremo.pact.SopremoUtil;
import eu.stratosphere.sopremo.type.ArrayNode;
import eu.stratosphere.sopremo.type.BooleanNode;
import eu.stratosphere.sopremo.type.IArrayNode;
import eu.stratosphere.sopremo.type.IJsonNode;
import eu.stratosphere.sopremo.type.NullNode;

/**
 * @author Arvid Heise
 */
@SuppressWarnings("serial")
public class SecondOrderFunctions implements BuiltinProvider {
	@Name(verb = "map")
	public static final SopremoFunction MAP = new SopremoFunction2<IArrayNode, FunctionNode>("map") {
		private final transient IArrayNode result = new ArrayNode(), parameters = new ArrayNode(1);

		private final transient FunctionCacheCache caches = new FunctionCacheCache();

		@Override
		protected IJsonNode call(IArrayNode input, final FunctionNode mapExpression) {
			SopremoUtil.assertArguments(mapExpression.getFunction(), 1);

			this.result.clear();
			final FunctionCache calls = this.caches.get(mapExpression.getFunction());
			for (int index = 0; index < input.size(); index++) {
				this.parameters.set(0, input.get(index));
				this.result.add(calls.get(index).call(this.parameters));
			}
			return this.result;
		}
	};

	@Name(verb = "filter")
	public static final SopremoFunction FILTER = new SopremoFunction2<IArrayNode, FunctionNode>("filter") {
		private final transient IArrayNode result = new ArrayNode(), parameters = new ArrayNode(1);

		@Override
		protected IJsonNode call(IArrayNode input, final FunctionNode filterExpression) {
			SopremoUtil.assertArguments(filterExpression.getFunction(), 1);

			this.result.clear();
			final SopremoFunction function = filterExpression.getFunction();
			for (final IJsonNode node : input) {
				this.parameters.set(0, node);
				if (function.call(this.parameters) == BooleanNode.TRUE)
					this.result.add(node);
			}
			return this.result;
		}
	};

	@Name(verb = "find")
	public static final SopremoFunction FIND = new SopremoFunction2<IArrayNode, FunctionNode>("find") {
		private final transient IArrayNode parameters = new ArrayNode(1);

		@Override
		protected IJsonNode call(IArrayNode input, final FunctionNode filterExpression) {
			SopremoUtil.assertArguments(filterExpression.getFunction(), 1);

			final SopremoFunction function = filterExpression.getFunction();
			for (final IJsonNode node : input) {
				this.parameters.set(0, node);
				if (function.call(this.parameters) == BooleanNode.TRUE)
					return node;
			}
			return NullNode.getInstance();
		}
	};

	@Name(verb = { "fold", "reduce" })
	public static final SopremoFunction FOLD = new SopremoFunction3<IArrayNode, IJsonNode, FunctionNode>("fold") {
		private final transient NodeCache nodeCache = new NodeCache();

		private final transient IArrayNode parameters = new ArrayNode();

		@Override
		protected IJsonNode call(IArrayNode input, IJsonNode initial, final FunctionNode foldExpression) {
			SopremoUtil.assertArguments(foldExpression.getFunction(), 2);

			final IJsonNode aggregator = this.nodeCache.clone(initial);
			this.parameters.set(0, aggregator);

			final SopremoFunction function = foldExpression.getFunction();
			for (final IJsonNode node : input) {
				this.parameters.set(1, node);
				aggregator.copyValueFrom(function.call(this.parameters));
			}

			return aggregator;
		}
	};
}
