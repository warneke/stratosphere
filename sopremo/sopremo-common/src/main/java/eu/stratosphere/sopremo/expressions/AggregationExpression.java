package eu.stratosphere.sopremo.expressions;

import java.io.IOException;

import eu.stratosphere.sopremo.aggregation.Aggregation;
import eu.stratosphere.sopremo.type.IJsonNode;
import eu.stratosphere.sopremo.type.IStreamArrayNode;

/**
 * Returns an aggregate of the elements of a {@link IArrayNode}.
 * The result is calculated with help of the specified {@link AggregationExpression}.
 */
public class AggregationExpression extends PathSegmentExpression {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1420818869290609780L;

	private final Aggregation aggregation;

	/**
	 * Initializes an AggregationExpression with the given {@link AggregationFunction} and an additional preprocessing.
	 * 
	 * @param aggregation
	 *        the aggregation which will should be used for aggregation
	 * @param preprocessing
	 *        an {@link EvaluationExpression} which evaluates each element of the input before they are used for
	 *        aggregation.
	 */
	public AggregationExpression(final Aggregation aggregation) {
		this.aggregation = aggregation.clone();
	}

	@Override
	protected IJsonNode evaluateSegment(final IJsonNode nodes) {
		this.aggregation.initialize();
		for (final IJsonNode node : (IStreamArrayNode) nodes)
			this.aggregation.aggregate(this.getInputExpression().evaluate(node));
		return this.aggregation.getFinalAggregate();
	}

	/**
	 * Returns the aggregation.
	 * 
	 * @return the aggregation
	 */
	public Aggregation getAggregation() {
		return this.aggregation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + this.aggregation.hashCode();
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (!super.equals(obj))
			return false;
		final AggregationExpression other = (AggregationExpression) obj;
		return this.aggregation.equals(other.aggregation);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.expressions.EvaluationExpression#createCopy()
	 */
	@Override
	protected EvaluationExpression createCopy() {
		return new AggregationExpression(this.aggregation.clone());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * eu.stratosphere.sopremo.expressions.PathSegmentExpression#withInputExpression(eu.stratosphere.sopremo.expressions
	 * .EvaluationExpression)
	 */
	@Override
	public AggregationExpression withInputExpression(EvaluationExpression inputExpression) {
		return (AggregationExpression) super.withInputExpression(inputExpression);
	}

	@Override
	public void appendAsString(final Appendable appendable) throws IOException {
		this.aggregation.appendAsString(appendable);
		appendable.append('(');
		if (this.getInputExpression() != EvaluationExpression.VALUE)
			this.getInputExpression().appendAsString(appendable);
		appendable.append(')');
	}
}
