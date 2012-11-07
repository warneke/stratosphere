package eu.stratosphere.sopremo.expressions;

import eu.stratosphere.sopremo.expressions.tree.ChildIterator;
import eu.stratosphere.sopremo.expressions.tree.NamedChildIterator;
import eu.stratosphere.sopremo.pact.SopremoUtil;
import eu.stratosphere.sopremo.type.ArrayNode;
import eu.stratosphere.sopremo.type.IArrayNode;
import eu.stratosphere.sopremo.type.IJsonNode;
import eu.stratosphere.sopremo.type.IStreamArrayNode;
import eu.stratosphere.sopremo.type.PullingStreamArrayNode;

/**
 * Projects an array onto another one.
 */
@OptimizerHints(scope = Scope.ARRAY, iterating = true)
public class ArrayProjection extends EvaluationExpression implements ExpressionParent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8420269355727456913L;

	private EvaluationExpression expression;

	/**
	 * Initializes an ArrayProjection with the given {@link EvaluationExpression}.
	 * 
	 * @param expression
	 *        the expression which evaluates the elements of the input array to the elements of the output array
	 */
	public ArrayProjection(final EvaluationExpression expression) {
		this.expression = expression;
	}

	@Override
	public boolean equals(final Object obj) {
		if (!super.equals(obj))
			return false;
		final ArrayProjection other = (ArrayProjection) obj;
		return this.expression.equals(other.expression);
	}

	/**
	 * Returns the expression.
	 * 
	 * @return the expression
	 */
	public EvaluationExpression getExpression() {
		return this.expression;
	}

	@Override
	public IJsonNode evaluate(final IJsonNode node, final IJsonNode target) {
		if (!(node instanceof IArrayNode)) {
			// virtual projection
			final PullingStreamArrayNode targetArray = SopremoUtil.ensureType(target, PullingStreamArrayNode.class);
			targetArray.setSource((IStreamArrayNode) node);
			targetArray.setExpressionAndContext(this.expression);
			return targetArray;
		}
		// materialized projection
		final IArrayNode array = (IArrayNode) node;
		final IArrayNode targetArray = SopremoUtil.reinitializeTarget(target, ArrayNode.class);
		for (int index = 0, size = array.size(); index < size; index++)
			targetArray.add(this.expression.evaluate(array.get(index), targetArray.get(index)));

		return targetArray;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.expressions.ExpressionParent#iterator()
	 */
	@Override
	public ChildIterator iterator() {
		return new NamedChildIterator("expression") {
			@Override
			protected void set(int index, EvaluationExpression childExpression) {
				ArrayProjection.this.expression = childExpression;
			}

			@Override
			protected EvaluationExpression get(int index) {
				return ArrayProjection.this.expression;
			}
		};
	}

	@Override
	public IJsonNode set(final IJsonNode node, final IJsonNode value) {
		final IArrayNode arrayNode = (ArrayNode) node;
		for (int index = 0, size = arrayNode.size(); index < size; index++)
			arrayNode.set(index, this.expression.set(arrayNode.get(index), value));
		return arrayNode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + this.expression.hashCode();
		return result;
	}

	@Override
	public void toString(final StringBuilder builder) {
		builder.append("[*]");
		builder.append(this.expression);
	}
}
