package eu.stratosphere.sopremo.base;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import eu.stratosphere.pact.common.contract.Contract;
import eu.stratosphere.pact.common.contract.ReduceContract;
import eu.stratosphere.pact.common.contract.ReduceContract.Combinable;
import eu.stratosphere.sopremo.EvaluationContext;
import eu.stratosphere.sopremo.expressions.ConstantExpression;
import eu.stratosphere.sopremo.expressions.EvaluationExpression;
import eu.stratosphere.sopremo.expressions.ExpressionUtil;
import eu.stratosphere.sopremo.expressions.InputSelection;
import eu.stratosphere.sopremo.operator.CompositeOperator;
import eu.stratosphere.sopremo.operator.ElementaryOperator;
import eu.stratosphere.sopremo.operator.InputCardinality;
import eu.stratosphere.sopremo.operator.JsonStream;
import eu.stratosphere.sopremo.operator.Name;
import eu.stratosphere.sopremo.operator.Operator;
import eu.stratosphere.sopremo.operator.OutputCardinality;
import eu.stratosphere.sopremo.operator.PactBuilderUtil;
import eu.stratosphere.sopremo.operator.Property;
import eu.stratosphere.sopremo.operator.SopremoModule;
import eu.stratosphere.sopremo.pact.JsonCollector;
import eu.stratosphere.sopremo.pact.SopremoCoGroup;
import eu.stratosphere.sopremo.pact.SopremoReduce;
import eu.stratosphere.sopremo.serialization.Schema;
import eu.stratosphere.sopremo.type.ArrayNode;
import eu.stratosphere.sopremo.type.IArrayNode;
import eu.stratosphere.sopremo.type.IStreamArrayNode;
import eu.stratosphere.sopremo.type.NullNode;

@InputCardinality(min = 1, max = 2)
@OutputCardinality(1)
@Name(verb = "group")
public class Grouping extends CompositeOperator<Grouping> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1452280003631381562L;

	private final static EvaluationExpression GROUP_ALL = new ConstantExpression(NullNode.getInstance());

	private EvaluationExpression resultProjection = EvaluationExpression.VALUE;

	private final Int2ObjectMap<EvaluationExpression> keyExpressions = new Int2ObjectArrayMap<EvaluationExpression>(1);

	private EvaluationExpression defaultGroupingKey = GROUP_ALL;

	@Override
	public void addImplementation(SopremoModule module, EvaluationContext context) {
		Operator<?> output;
		switch (this.getNumInputs()) {
		case 0:
			throw new IllegalStateException("No input given for grouping");
		case 1:
			output = new GroupProjection().withResultProjection(this.resultProjection).
				withKeyExpression(0, this.getGroupingKey(0).remove(new InputSelection(0))).
				withInputs(module.getInputs());
			break;
		case 2:
			output = new CoGroupProjection().withResultProjection(this.resultProjection).
				withKeyExpression(0, this.getGroupingKey(0).remove(new InputSelection(0))).
				withKeyExpression(1, this.getGroupingKey(1).remove(new InputSelection(1))).
				withInputs(module.getInputs());
			break;
		default:
			throw new IllegalStateException("More than two sources are not supported");
			// List<JsonStream> inputs = new ArrayList<JsonStream>();
			// List<EvaluationExpression> keyExpressions = new ArrayList<EvaluationExpression>();
			// for (int index = 0; index < numInputs; index++) {
			// inputs.add(OperatorUtil.positionEncode(module.getInput(index), index, numInputs));
			// keyExpressions.add(new PathExpression(new InputSelection(index), getGroupingKey(index)));
			// }
			// final UnionAll union = new UnionAll().
			// withInputs(inputs);
			// final PathExpression projection =
			// new PathExpression(new AggregationExpression(new ArrayUnion()), this.resultProjection);
			// output = new GroupProjection(projection).
			// withInputs(union);
			// break;
		}

		module.getOutput(0).setInput(0, output);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		final Grouping other = (Grouping) obj;
		return this.resultProjection.equals(other.resultProjection);
	}

	public EvaluationExpression getResultProjection() {
		return this.resultProjection;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + this.resultProjection.hashCode();
		return result;
	}

	@Property(preferred = true)
	@Name(preposition = "into")
	public void setResultProjection(EvaluationExpression resultProjection) {
		if (resultProjection == null)
			throw new NullPointerException("resultProjection must not be null");

		this.resultProjection =
			ExpressionUtil.replaceAggregationWithBatchAggregation(
				ExpressionUtil.replaceIndexAccessWithAggregation(resultProjection));
	}

	public Grouping withResultProjection(EvaluationExpression resultProjection) {
		this.setResultProjection(resultProjection);
		return this;
	}

	@Property(preferred = true, input = true)
	@Name(preposition = "by")
	public void setGroupingKey(final int inputIndex, final EvaluationExpression keyExpression) {
		this.keyExpressions.put(inputIndex, keyExpression);
	}

	public void setGroupingKey(final JsonStream input, final EvaluationExpression keyExpression) {
		if (keyExpression == null)
			throw new NullPointerException("keyExpression must not be null");

		this.keyExpressions.put(getSafeInputIndex(input), keyExpression);
	}

	public Grouping withGroupingKey(int inputIndex, EvaluationExpression groupingKey) {
		this.setGroupingKey(inputIndex, groupingKey);
		return this;
	}

	public Grouping withGroupingKey(EvaluationExpression groupingKey) {
		this.setDefaultGroupingKey(groupingKey);
		return this;
	}

	public EvaluationExpression getGroupingKey(final int index) {
		final EvaluationExpression keyExpression = this.keyExpressions.get(index);
		if (keyExpression == null)
			return this.getDefaultGroupingKey();
		return keyExpression;
	}

	public EvaluationExpression getGroupingKey(final JsonStream input) {
		return getGroupingKey(getSafeInputIndex(input));
	}

	public EvaluationExpression getDefaultGroupingKey() {
		return this.defaultGroupingKey;
	}

	@Property(hidden = true)
	public void setDefaultGroupingKey(EvaluationExpression defaultGroupingKey) {
		if (defaultGroupingKey == null)
			throw new NullPointerException("defaultGroupingKey must not be null");

		this.defaultGroupingKey = defaultGroupingKey;
	}

	@Override
	public String toString() {
		return String.format("%s to %s", super.toString(), this.resultProjection);
	}

	@InputCardinality(min = 2, max = 2)
	public static class CoGroupProjection extends ElementaryOperator<CoGroupProjection> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 561729616462154707L;

		public static class Implementation extends SopremoCoGroup {
			private final IArrayNode streams = new ArrayNode(2); 
			
			@Override
			protected void coGroup(IStreamArrayNode values1, IStreamArrayNode values2, JsonCollector out) {
				this.streams.set(0, values1);
				this.streams.set(1, values2);
				out.collect(this.streams);
			}
		}
	}

	@InputCardinality(1)
	public static class GroupProjection extends ElementaryOperator<GroupProjection> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 561729616462154707L;

		/*
		 * (non-Javadoc)
		 * @see
		 * eu.stratosphere.sopremo.operator.ElementaryOperator#getContract(eu.stratosphere.sopremo.serialization.Schema)
		 */
		@Override
		protected Contract getContract(Schema globalSchema) {
			ReduceContract.Builder builder =
				ReduceContract.builder(isCombinable() ? CombinableImplementation.class : Implementation.class);
			if (!getKeyExpressions(0).contains(GROUP_ALL)) {
				int[] keyIndices = this.getKeyIndices(globalSchema, this.getKeyExpressions(0));
				PactBuilderUtil.addKeys(builder, this.getKeyClasses(globalSchema, keyIndices), keyIndices);
			}
			builder.name(this.toString());
			return builder.build();
		}

		private boolean isCombinable() {
			// TODO: make grouping combinable if all functions are transitive
			return false;
		}

		@Combinable
		public static class CombinableImplementation extends SopremoReduce {
			@Override
			protected void reduce(final IStreamArrayNode values, final JsonCollector out) {
				out.collect(values);
			}
		}

		public static class Implementation extends SopremoReduce {
			@Override
			protected void reduce(final IStreamArrayNode values, final JsonCollector out) {
				out.collect(values);
			}
		}
	}
}
