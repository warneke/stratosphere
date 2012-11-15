package eu.stratosphere.sopremo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eu.stratosphere.sopremo.aggregation.Aggregation;
import eu.stratosphere.sopremo.aggregation.FixedTypeTransitiveAggregation;
import eu.stratosphere.sopremo.aggregation.MaterializingAggregation;
import eu.stratosphere.sopremo.aggregation.TransitiveAggregation;
import eu.stratosphere.sopremo.cache.ArrayCache;
import eu.stratosphere.sopremo.cache.NodeCache;
import eu.stratosphere.sopremo.cache.PatternCache;
import eu.stratosphere.sopremo.expressions.ArithmeticExpression;
import eu.stratosphere.sopremo.expressions.ArithmeticExpression.ArithmeticOperator;
import eu.stratosphere.sopremo.expressions.ComparativeExpression;
import eu.stratosphere.sopremo.function.ExpressionFunction;
import eu.stratosphere.sopremo.function.SopremoFunction;
import eu.stratosphere.sopremo.function.SopremoFunction1;
import eu.stratosphere.sopremo.function.SopremoFunction2;
import eu.stratosphere.sopremo.function.SopremoFunction3;
import eu.stratosphere.sopremo.function.SopremoVarargFunction;
import eu.stratosphere.sopremo.function.SopremoVarargFunction1;
import eu.stratosphere.sopremo.operator.Name;
import eu.stratosphere.sopremo.packages.BuiltinProvider;
import eu.stratosphere.sopremo.tokenizer.RegexTokenizer;
import eu.stratosphere.sopremo.type.ArrayNode;
import eu.stratosphere.sopremo.type.CachingArrayNode;
import eu.stratosphere.sopremo.type.IArrayNode;
import eu.stratosphere.sopremo.type.IJsonNode;
import eu.stratosphere.sopremo.type.INumericNode;
import eu.stratosphere.sopremo.type.IntNode;
import eu.stratosphere.sopremo.type.NullNode;
import eu.stratosphere.sopremo.type.TextNode;

/**
 * Core functions.
 * 
 * @author Arvid Heise
 */
@SuppressWarnings("serial")
public class CoreFunctions implements BuiltinProvider {
	@Name(verb = "concat", noun = "concatenation")
	public static final Aggregation CONCAT = new FixedTypeTransitiveAggregation<TextNode>("concat", new TextNode()) {
		@Override
		protected void aggregateInto(TextNode aggregator, IJsonNode element) {
			aggregator.append((TextNode) element);
		}
	};

	/**
	 * Repeatedly applies the {@link ArithmeticOperator#ADDITION} to the children of the given node.
	 */
	@Name(verb = "sum", noun = "sum")
	public static final Aggregation SUM = new TransitiveAggregation<INumericNode>("sum", IntNode.ZERO) {
		private final transient NodeCache nodeCache = new NodeCache();

		@Override
		protected INumericNode aggregate(INumericNode aggregator, IJsonNode element) {
			return ArithmeticExpression.ArithmeticOperator.ADDITION.evaluate(aggregator, (INumericNode) element,
				this.nodeCache);
		}
	};

	@Name(verb = "count", noun = "count")
	public static final Aggregation COUNT = new FixedTypeTransitiveAggregation<IntNode>("count", IntNode.ZERO) {
		@Override
		protected void aggregateInto(IntNode aggregator, IJsonNode element) {
			aggregator.increment();
		}
	};

	@Name(noun = "first")
	public static final Aggregation FIRST = new TransitiveAggregation<IJsonNode>("first", NullNode.getInstance()) {
		@Override
		protected IJsonNode aggregate(IJsonNode aggregator, IJsonNode element) {
			return aggregator.isNull() ? element : aggregator;
		}
	};

	@Name(verb = "sort")
	public static final Aggregation SORT = new MaterializingAggregation("sort") {
		private final transient ArrayCache<IJsonNode> arrayCache = new ArrayCache<IJsonNode>(IJsonNode.class);
		
		@Override
		protected IJsonNode processNodes(final CachingArrayNode nodeArray) {
			final IJsonNode[] nodes = nodeArray.toArray(this.arrayCache);
			Arrays.sort(nodes);
			nodeArray.setAll(nodes);
			return nodeArray;
		}
	};

	@Name(adjective = "all")
	public static final Aggregation ALL = new MaterializingAggregation("all") {
	};

	@Name(noun = "mean")
	public static final SopremoFunction MEAN = new ExpressionFunction(1,
		new ArithmeticExpression(SUM.asExpression(), ArithmeticOperator.DIVISION, COUNT.asExpression()));

	@Name(noun = "min")
	public static final Aggregation MIN = new TransitiveAggregation<IJsonNode>("min", NullNode.getInstance()) {
		@Override
		public IJsonNode aggregate(final IJsonNode aggregator, final IJsonNode node) {
			if (aggregator.isNull())
				return node.clone();
			else if (ComparativeExpression.BinaryOperator.LESS.evaluate(node, aggregator))
				return node;
			return aggregator;
		}
	};

	@Name(noun = "max")
	public static final Aggregation MAX = new TransitiveAggregation<IJsonNode>("max", NullNode.getInstance()) {
		@Override
		public IJsonNode aggregate(final IJsonNode aggregator, final IJsonNode node) {
			if (aggregator.isNull())
				return node.clone();
			else if (ComparativeExpression.BinaryOperator.LESS.evaluate(aggregator, node))
				aggregator.copyValueFrom(node);
			return aggregator;
		}
	};

	/**
	 * Creates a new array by combining sparse array information.<br />
	 * For example: [[0, "a"], [3, "d"], [2, "c"]] -&lt; ["a", missing, "c", "d"]
	 */
	@Name(verb = "assemble")
	public static final Aggregation ASSEMBLE_ARRAY = new FixedTypeTransitiveAggregation<ArrayNode>("assemble",
		new ArrayNode()) {

		@Override
		protected void aggregateInto(ArrayNode aggregator, IJsonNode element) {
			IArrayNode part = (IArrayNode) element;
			aggregator.add(((INumericNode) part.get(0)).getIntValue(), part.get(1));
		}
	};

	/**
	 * Adds the specified node to the array at the given index
	 * 
	 * @param array
	 *        the array that should be extended
	 * @param index
	 *        the position of the insert
	 * @param node
	 *        the node to add
	 * @return array with the added node
	 */
	@Name(verb = "add")
	public static final SopremoFunction ADD = new SopremoFunction3<IArrayNode, IntNode, IJsonNode>("add") {
		/*
		 * (non-Javadoc)
		 * @see eu.stratosphere.sopremo.function.SopremoFunction3#call(eu.stratosphere.sopremo.type.IJsonNode,
		 * eu.stratosphere.sopremo.type.IJsonNode, eu.stratosphere.sopremo.type.IJsonNode)
		 */
		@Override
		protected IJsonNode call(final IArrayNode array, final IntNode index, final IJsonNode node) {
			array.add(resolveIndex(index.getIntValue(), array.size()), node);
			return array;
		}
	};

	@Name(noun = "camelCase")
	public static final SopremoFunction CAMEL_CASE = new SopremoFunction1<TextNode>("camelCase") {
		private final StringBuilder builder = new StringBuilder();

		private final TextNode result = new TextNode();

		/*
		 * (non-Javadoc)
		 * @see eu.stratosphere.sopremo.function.SopremoFunction3#call(eu.stratosphere.sopremo.type.IJsonNode,
		 * eu.stratosphere.sopremo.type.IJsonNode, eu.stratosphere.sopremo.type.IJsonNode)
		 */
		@Override
		protected IJsonNode call(final TextNode input) {
			this.builder.append(input);

			boolean capitalize = true;
			for (int index = 0, length = this.builder.length(); index < length; index++) {
				final char ch = this.builder.charAt(index);
				if (Character.isWhitespace(ch))
					capitalize = true;
				else if (capitalize) {
					this.builder.setCharAt(index, Character.toUpperCase(ch));
					capitalize = false;
				} else {
					final char lowerCh = Character.toLowerCase(ch);
					if (lowerCh != ch)
						this.builder.setCharAt(index, lowerCh);
				}
			}
			this.result.setValue(this.builder);
			return this.result;
		}
	};

	@Name(verb = "extract")
	public static final SopremoFunction EXTRACT = new SopremoFunction3<TextNode, TextNode, IJsonNode>("extract") {
		private final transient PatternCache patternCache = new PatternCache();

		private final TextNode stringResult = new TextNode();

		private final CachingArrayNode arrayResult = new CachingArrayNode();

		/*
		 * (non-Javadoc)
		 * @see eu.stratosphere.sopremo.function.SopremoFunction3#call(eu.stratosphere.sopremo.type.IJsonNode,
		 * eu.stratosphere.sopremo.type.IJsonNode, eu.stratosphere.sopremo.type.IJsonNode)
		 */
		@Override
		protected IJsonNode call(final TextNode input, final TextNode pattern, final IJsonNode defaultValue) {
			final Pattern compiledPattern = this.patternCache.getPatternOf(pattern);
			final Matcher matcher = compiledPattern.matcher(input.getTextValue());

			if (!matcher.find())
				return defaultValue;

			if (matcher.groupCount() == 0) {
				this.stringResult.setValue(matcher.group(0));
				return this.stringResult;
			}

			if (matcher.groupCount() == 1) {
				this.stringResult.setValue(matcher.group(1));
				return this.stringResult;
			}

			this.arrayResult.clear();
			for (int index = 1; index <= matcher.groupCount(); index++) {
				TextNode group = (TextNode) this.arrayResult.reuseUnusedNode();
				if (group == null)
					this.arrayResult.add(group = new TextNode());
				group.setValue(matcher.group(index));
			}
			return this.arrayResult;
		}
	}.withDefaultParameters(NullNode.getInstance());

	@Name(noun = "format", verb = "format")
	public static final SopremoFunction FORMAT = new SopremoVarargFunction1<TextNode>("format") {
		private final TextNode result = new TextNode();

		private final transient ArrayCache<Object> arrayCache = new ArrayCache<Object>(Object.class);

		/*
		 * (non-Javadoc)
		 * @see eu.stratosphere.sopremo.function.SopremoVarargFunction1#call(eu.stratosphere.sopremo.type.IJsonNode,
		 * eu.stratosphere.sopremo.type.IArrayNode)
		 */
		@Override
		protected IJsonNode call(TextNode format, IArrayNode varargs) {
			final Object[] paramsAsObjects = this.arrayCache.getArray(varargs.size());
			for (int index = 0; index < paramsAsObjects.length; index++)
				paramsAsObjects[index] = varargs.get(index).toString();

			this.result.clear();
			this.result.asFormatter().format(format.getTextValue().toString(), paramsAsObjects);
			return this.result;
		}
	};

	@Name(verb = "subtract")
	public static final SopremoFunction SUBTRACT = new SopremoVarargFunction1<IArrayNode>("subtract") {
		private final HashSet<IJsonNode> filterSet = new HashSet<IJsonNode>();

		private final IArrayNode result = new ArrayNode();

		/*
		 * (non-Javadoc)
		 * @see eu.stratosphere.sopremo.function.SopremoVarargFunction1#call(eu.stratosphere.sopremo.type.IJsonNode,
		 * eu.stratosphere.sopremo.type.IArrayNode)
		 */
		@Override
		protected IJsonNode call(IArrayNode input, IArrayNode elementsToRemove) {
			this.filterSet.clear();
			for (IJsonNode elementToFilter : elementsToRemove)
				this.filterSet.add(elementToFilter);

			this.result.clear();
			for (int index = 0; index < input.size(); index++)
				if (!this.filterSet.contains(input.get(index)))
					this.result.add(input.get(index));
			return this.result;
		}
	};

	@Name(noun = "length")
	public static final SopremoFunction LENGTH = new SopremoFunction1<TextNode>("length") {
		private final IntNode result = new IntNode();

		@Override
		protected IJsonNode call(TextNode node) {
			this.result.setValue(node.getTextValue().length());
			return this.result;
		}
	};

	@Name(verb = "replace")
	public static final SopremoFunction REPLACE = new SopremoFunction3<TextNode, TextNode, TextNode>("replace") {
		private final transient PatternCache patternCache = new PatternCache();

		private final TextNode result = new TextNode();

		/*
		 * (non-Javadoc)
		 * @see eu.stratosphere.sopremo.function.SopremoFunction3#call(eu.stratosphere.sopremo.type.IJsonNode,
		 * eu.stratosphere.sopremo.type.IJsonNode, eu.stratosphere.sopremo.type.IJsonNode)
		 */
		@Override
		protected IJsonNode call(final TextNode input, final TextNode search, final TextNode replace) {
			final Pattern compiledPattern = this.patternCache.getPatternOf(search);
			final Matcher matcher = compiledPattern.matcher(input.getTextValue());
			this.result.setValue(matcher.replaceAll(replace.toString()));
			return this.result;
		}
	}.withDefaultParameters(TextNode.EMPTY_STRING);

	private static final TextNode WHITESPACES = TextNode.valueOf("\\p{javaWhitespace}+");

	@Name(verb = "split")
	public static final SopremoFunction SPLIT = new SopremoFunction2<TextNode, TextNode>("split") {
		private final transient PatternCache patternCache = new PatternCache();

		private final CachingArrayNode result = new CachingArrayNode();

		private final Map<Pattern, RegexTokenizer> tokenizers = new IdentityHashMap<Pattern, RegexTokenizer>();

		/*
		 * (non-Javadoc)
		 * @see eu.stratosphere.sopremo.function.SopremoFunction3#call(eu.stratosphere.sopremo.type.IJsonNode,
		 * eu.stratosphere.sopremo.type.IJsonNode, eu.stratosphere.sopremo.type.IJsonNode)
		 */
		@Override
		protected IJsonNode call(final TextNode input, final TextNode splitString) {
			final Pattern searchPattern = this.patternCache.getPatternOf(splitString);
			RegexTokenizer regexTokenizer = this.tokenizers.get(searchPattern);
			if (regexTokenizer == null)
				this.tokenizers.put(searchPattern, regexTokenizer = new RegexTokenizer(searchPattern));
			regexTokenizer.tokenizeInto(input, this.result);
			return this.result;
		}
	}.withDefaultParameters(WHITESPACES);

	@Name(noun = "substring")
	public static final SopremoFunction SUBSTRING = new SopremoFunction3<TextNode, IntNode, IntNode>("substring") {
		private final TextNode result = new TextNode();

		/*
		 * (non-Javadoc)
		 * @see eu.stratosphere.sopremo.function.SopremoFunction3#call(eu.stratosphere.sopremo.type.IJsonNode,
		 * eu.stratosphere.sopremo.type.IJsonNode, eu.stratosphere.sopremo.type.IJsonNode)
		 */
		@Override
		protected IJsonNode call(final TextNode input, final IntNode from, final IntNode to) {
			final int length = input.length();
			final int fromPos = resolveIndex(from.getIntValue(), length);
			final int toPos = resolveIndex(to.getIntValue(), length);
			this.result.setValue(input, fromPos, toPos);
			return this.result;
		}
	}.withDefaultParameters(new IntNode(-1));

	@Name(verb = "trim")
	public static final SopremoFunction TRIM = new SopremoFunction1<TextNode>("trim") {
		private final TextNode result = new TextNode();

		@Override
		protected IJsonNode call(final TextNode input) {
			int start = 0, end = input.length() - 1;
			while (start < end && input.charAt(start) == ' ')
				start++;
			while (end > start && input.charAt(end) == ' ')
				end--;
			this.result.setValue(input, start, end + 1);
			return this.result;
		}
	}.withDefaultParameters(new IntNode(-1));

	@Name(verb = "unionAll")
	public static final SopremoFunction UNION_ALL = new SopremoVarargFunction("unionAll", 0) {
		private final IArrayNode union = new ArrayNode();

		@Override
		public IJsonNode call(final IArrayNode params) {
			this.union.clear();
			for (final IJsonNode param : params)
				for (final IJsonNode child : (IArrayNode) param)
					this.union.add(child);
			return this.union;
		}
	};

	private static int resolveIndex(final int index, final int size) {
		if (index < 0)
			return size + index;
		return index;
	}
}
