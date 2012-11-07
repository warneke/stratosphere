package eu.stratosphere.sopremo.expressions;

import eu.stratosphere.sopremo.expressions.tree.ChildIterator;
import eu.stratosphere.sopremo.expressions.tree.NamedChildIterator;
import eu.stratosphere.sopremo.type.IJsonNode;
import eu.stratosphere.sopremo.type.TypeCoercer;

/**
 * Converts the result of an evaluation to a various number of node types.
 */
@OptimizerHints(scope = Scope.NUMBER)
public class CoerceExpression extends EvaluationExpression implements ExpressionParent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1954495592440005318L;

	private final Class<IJsonNode> targetType;

	private CachingExpression<IJsonNode> valueExpression;

	/**
	 * Initializes a CoerceExpression with the given value and the given type.
	 * 
	 * @param targetType
	 *        the class of the node the result should be converted to
	 * @param value
	 *        the expression which evaluates to the result
	 */
	@SuppressWarnings("unchecked")
	public CoerceExpression(final Class<? extends IJsonNode> targetType, final EvaluationExpression value) {
		this.targetType = (Class<IJsonNode>) targetType;
		this.valueExpression = CachingExpression.ofSubclass(value, IJsonNode.class);
	}

	/**
	 * Initializes a CoerceExpression with the given type.
	 * 
	 * @param targetType
	 *        the class of the node the result should be converted to
	 */
	public CoerceExpression(final Class<? extends IJsonNode> targetType) {
		this(targetType, EvaluationExpression.VALUE);
	}

	/**
	 * Returns the valueExpression.
	 * 
	 * @return the valueExpression
	 */
	public EvaluationExpression getValueExpression() {
		return this.valueExpression;
	}

	/**
	 * Sets a new valueExpression.
	 * 
	 * @param valueExpression
	 *        the {@link EvaluationExpression} that should be set as the new valueExpression
	 */
	public void setValueExpression(final EvaluationExpression valueExpression) {
		if (valueExpression == null)
			throw new NullPointerException("valueExpression must not be null");

		this.valueExpression = CachingExpression.ofSubclass(valueExpression, IJsonNode.class);
	}

	@Override
	public IJsonNode evaluate(final IJsonNode node, final IJsonNode target) {
		return TypeCoercer.INSTANCE.coerce(this.valueExpression.evaluate(node), target, this.targetType);
	}

	@Override
	public void toString(final StringBuilder builder) {
		builder.append('(').append(this.targetType).append(')');
		if (this.valueExpression != EvaluationExpression.VALUE)
			builder.append(' ').append(this.valueExpression);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + this.targetType.hashCode();
		result = prime * result + this.valueExpression.hashCode();
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (!super.equals(obj))
			return false;
		final CoerceExpression other = (CoerceExpression) obj;
		return this.targetType == other.targetType && this.valueExpression.equals(other.valueExpression);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.expressions.ExpressionParent#iterator()
	 */
	@Override
	public ChildIterator iterator() {
		return new NamedChildIterator("valueExpression") {
			@Override
			protected void set(int index, EvaluationExpression childExpression) {
				CoerceExpression.this.valueExpression.innerExpression = childExpression;
			}
	
			@Override
			protected EvaluationExpression get(int index) {
				return CoerceExpression.this.valueExpression.innerExpression;
			}
		};
	}
}
