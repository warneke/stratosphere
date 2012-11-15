package eu.stratosphere.sopremo.aggregation;

import eu.stratosphere.sopremo.type.IJsonNode;
import eu.stratosphere.util.reflect.ReflectUtil;

public abstract class TransitiveAggregation<ElementType extends IJsonNode> extends Aggregation {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4836890030948315219L;

	protected final ElementType initialAggregate;

	protected transient ElementType aggregator;

	@SuppressWarnings("unchecked")
	public TransitiveAggregation(final String name, final ElementType initialAggregate) {
		super(name);
		this.initialAggregate = initialAggregate;
		this.aggregator = (ElementType) ReflectUtil.newInstance(this.initialAggregate.getClass());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * eu.stratosphere.sopremo.aggregation.AggregationFunction#getFinalAggregate(eu.stratosphere.sopremo.type.IJsonNode,
	 * eu.stratosphere.sopremo.type.IJsonNode)
	 */
	@Override
	public ElementType getFinalAggregate() {
		return this.aggregator;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize() {
		if (this.aggregator.getType() != this.initialAggregate.getType())
			this.aggregator = (ElementType) ReflectUtil.newInstance(this.initialAggregate.getClass());
		this.aggregator.copyValueFrom(this.initialAggregate);
	}

	@Override
	public Aggregation clone() {
		try {
			// initial aggregate does not need to be cloned as it is never modified
			return ReflectUtil.newInstance(getClass(), this.getName(), this.initialAggregate);
		} catch (Exception e) {
			throw new IllegalStateException("Aggregation must implement clone or conform to the ctor", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.aggregation.Aggregation#aggregate(eu.stratosphere.sopremo.type.IJsonNode)
	 */
	@Override
	public void aggregate(IJsonNode element) {
		this.aggregator = aggregate(this.aggregator, element);
	}

	protected abstract ElementType aggregate(ElementType aggregator, IJsonNode element);
}
