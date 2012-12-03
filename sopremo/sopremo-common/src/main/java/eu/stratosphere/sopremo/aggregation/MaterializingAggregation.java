package eu.stratosphere.sopremo.aggregation;

import eu.stratosphere.sopremo.type.CachingArrayNode;
import eu.stratosphere.sopremo.type.IJsonNode;
import eu.stratosphere.util.reflect.ReflectUtil;

public class MaterializingAggregation extends Aggregation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3685213903416162250L;

	/**
	 * Initializes a new MaterializingAggregation with the given name.
	 * 
	 * @param name
	 *        the name that should be used
	 */
	protected MaterializingAggregation(final String name) {
		super(name);
	}

	protected transient final CachingArrayNode aggregator = new CachingArrayNode();

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.aggregation.Aggregation#initialize()
	 */
	@Override
	public void initialize() {
		this.aggregator.clear();
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.aggregation.Aggregation#aggregate(eu.stratosphere.sopremo.type.IJsonNode)
	 */
	@Override
	public void aggregate(IJsonNode element) {
		this.aggregator.addClone(element);
	}

	@Override
	public Aggregation clone() {
		try {
			return ReflectUtil.newInstance(this.getClass(), this.getName());
		} catch (Exception e) {
			throw new IllegalStateException("Aggregation must implement clone or conform to the ctor", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.aggregation.Aggregation#getFinalAggregate()
	 */
	@Override
	public IJsonNode getFinalAggregate() {
		return this.processNodes(this.aggregator);
	}

	protected IJsonNode processNodes(final CachingArrayNode nodeArray) {
		return nodeArray;
	}
}
