package eu.stratosphere.sopremo.expressions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import eu.stratosphere.sopremo.EvaluationException;
import eu.stratosphere.sopremo.ISopremoType;
import eu.stratosphere.sopremo.aggregation.Aggregation;
import eu.stratosphere.sopremo.expressions.tree.ChildIterator;
import eu.stratosphere.sopremo.expressions.tree.ConcatenatingChildIterator;
import eu.stratosphere.sopremo.type.ArrayNode;
import eu.stratosphere.sopremo.type.IArrayNode;
import eu.stratosphere.sopremo.type.IJsonNode;
import eu.stratosphere.sopremo.type.IStreamArrayNode;

/**
 * Batch aggregates one stream of {@link IJsonNode} with several {@link AggregationFunction}s.
 * 
 * @author Arvid Heise
 */
public class BatchAggregationExpression extends PathSegmentExpression {
	/**
	 * 
	 */
	private static final long serialVersionUID = 39927024374509247L;

	private final List<Partial> partials;

	private final transient IArrayNode results = new ArrayNode();

	/**
	 * Initializes a BatchAggregationExpression with the given {@link AggregationFunction}s.
	 * 
	 * @param functions
	 *        all functions that should be used
	 */
	public BatchAggregationExpression(final Aggregation... functions) {
		this(Arrays.asList(functions));
	}

	/**
	 * Initializes a BatchAggregationExpression with the given {@link AggregationFunction}s.
	 * 
	 * @param functions
	 *        a set of all functions that should be used
	 */
	public BatchAggregationExpression(final List<Aggregation> functions) {
		this.partials = new ArrayList<Partial>(functions.size());
		for (final Aggregation function : functions)
			this.partials.add(new Partial(function, this.partials.size()));
	}

	/**
	 * Adds a new {@link AggregationFunction}.
	 * 
	 * @param function
	 *        the function that should be added
	 * @return the function which has been added as a {@link Partial}
	 */
	public AggregationExpression add(final Aggregation function) {
		return this.add(function.clone(), EvaluationExpression.VALUE);
	}

	/**
	 * Adds a new {@link AggregationFunction} with the given preprocessing.
	 * 
	 * @param function
	 *        the function that should be added
	 * @param preprocessing
	 *        the preprocessing that should be used for this function
	 * @return the function which has been added as a {@link Partial}
	 */
	public AggregationExpression add(final Aggregation function, final EvaluationExpression preprocessing) {
		final Partial partial = new Partial(function.clone(), this.partials.size()).withInputExpression(preprocessing);
		this.partials.add(partial);
		return partial;
	}

	/**
	 * Returns the partial aggregation at the given index.
	 * 
	 * @param index
	 *        the index
	 * @return the partial aggregation
	 */
	public EvaluationExpression get(int index) {
		return this.partials.get(index);
	}

	/**
	 * Returns the partial aggregation at the given index.
	 * 
	 * @param index
	 *        the index
	 * @return the partial aggregation
	 */
	Partial getPartial(int index) {
		return this.partials.get(index);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * eu.stratosphere.sopremo.expressions.PathSegmentExpression#evaluateSegment(eu.stratosphere.sopremo.type.IJsonNode)
	 */
	@Override
	protected IJsonNode evaluateSegment(final IJsonNode node) {
		final IStreamArrayNode stream = (IStreamArrayNode) node;
		if (stream.isEmpty())
			return this.results;

		this.results.clear();

		for (Partial partial : this.partials)
			partial.getAggregation().initialize();
		for (final IJsonNode input : stream)
			for (Partial partial : this.partials) {
				final IJsonNode preprocessedValue = partial.getInputExpression().evaluate(input);
				if (preprocessedValue.isMissing())
					throw new EvaluationException(String.format("Cannot access %s for aggregation %s",
						partial.getInputExpression(), partial));
				partial.getAggregation().aggregate(preprocessedValue);
			}

		for (int index = 0; index < this.partials.size(); index++)
			this.results.set(index, this.partials.get(index).getAggregation().getFinalAggregate());

		return this.results;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.expressions.EvaluationExpression#createCopy()
	 */
	@Override
	protected EvaluationExpression createCopy() {
		return new BatchAggregationExpression();
	}

	/* (non-Javadoc)
	 * @see eu.stratosphere.sopremo.expressions.PathSegmentExpression#copyPropertiesFrom(eu.stratosphere.sopremo.ISopremoType)
	 */
	@Override
	public void copyPropertiesFrom(ISopremoType original) {
		super.copyPropertiesFrom(original);
		for (Partial partial : ((BatchAggregationExpression) original).partials)
			this.partials.add(partial.partialClone(this));
	}

	private static class CloneHelper {
		private BatchAggregationExpression clone;

		public CloneHelper(BatchAggregationExpression clone) {
			this.clone = clone;
		}

		private BitSet clonedPartials = new BitSet();
	}

	private static final ThreadLocal<Map<BatchAggregationExpression, CloneHelper>> CLONE_MAP =
		new ThreadLocal<Map<BatchAggregationExpression, CloneHelper>>() {
			@Override
			protected Map<BatchAggregationExpression, CloneHelper> initialValue() {
				return new IdentityHashMap<BatchAggregationExpression, CloneHelper>();
			}
		};

	private static BatchAggregationExpression getClone(BatchAggregationExpression expression, Partial partial) {
		final Map<BatchAggregationExpression, CloneHelper> map = CLONE_MAP.get();
		CloneHelper cloneHelper = map.get(expression);
		if (cloneHelper == null || cloneHelper.clonedPartials.get(partial.index))
			map.put(expression, cloneHelper = new CloneHelper((BatchAggregationExpression) expression.clone()));
		cloneHelper.clonedPartials.set(partial.index);
		return cloneHelper.clone;
	}

	final class Partial extends AggregationExpression {
		/**
		 * 
		 */
		private static final long serialVersionUID = -7597385785040647923L;

		private final int index;

		/**
		 * Initializes a Partial with the given function, preprocessing and index.
		 * 
		 * @param function
		 *        an {@link AggregationFunction} that should be used by this Partial
		 * @param preprocessing
		 *        the preprocessing that should be used by this Partial
		 * @param index
		 *        the index of this Partial
		 */
		public Partial(final Aggregation function, final int index) {
			super(function);
			this.index = index;
		}

		BatchAggregationExpression getBatch() {
			return BatchAggregationExpression.this;
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * eu.stratosphere.sopremo.expressions.PathSegmentExpression#withInputExpression(eu.stratosphere.sopremo.expressions
		 * .EvaluationExpression)
		 */
		@Override
		public Partial withInputExpression(EvaluationExpression inputExpression) {
			return (Partial) super.withInputExpression(inputExpression);
		}

		private Partial partialClone(BatchAggregationExpression outer) {
			final Partial partial = outer.new Partial(this.getAggregation().clone(), this.index);
			partial.copyPropertiesFrom(this);
			return partial;
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * eu.stratosphere.sopremo.expressions.PathSegmentExpression#evaluate(eu.stratosphere.sopremo.type.IJsonNode)
		 */
		@Override
		public IJsonNode evaluate(IJsonNode node) {
			return ((IArrayNode) BatchAggregationExpression.this.evaluate(node)).get(this.index);
		}

		/*
		 * (non-Javadoc)
		 * @see eu.stratosphere.sopremo.expressions.AggregationExpression#toString(java.lang.StringBuilder)
		 */
		@Override
		public void appendAsString(Appendable appendable) throws IOException {
			this.getAggregation().appendAsString(appendable);
			appendable.append('(');
			BatchAggregationExpression.this.appendAsString(appendable);
			// if (this.getInputExpression() != EvaluationExpression.VALUE)
			// this.getInputExpression().appendAsString(appendable);
			appendable.append(')');
		}

		/*
		 * (non-Javadoc)
		 * @see eu.stratosphere.sopremo.expressions.PathSegmentExpression#iterator()
		 */
		@Override
		public ChildIterator iterator() {
			return new ConcatenatingChildIterator(super.iterator(), BatchAggregationExpression.this.iterator());
		}

		/*
		 * (non-Javadoc)
		 * @see eu.stratosphere.sopremo.expressions.AggregationExpression#createCopy()
		 */
		@Override
		protected EvaluationExpression createCopy() {
			return getClone(BatchAggregationExpression.this, this).partials.get(this.index);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (this.partials == null ? 0 : this.partials.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (!super.equals(obj))
			return false;
		final BatchAggregationExpression other = (BatchAggregationExpression) obj;
		return this.partials.equals(other.partials);
	}

	@Override
	public void appendAsString(final Appendable appendable) throws IOException {
		this.getInputExpression().appendAsString(appendable);
		appendable.append('^');
	}
}