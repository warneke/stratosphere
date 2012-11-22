package eu.stratosphere.sopremo.query;

import eu.stratosphere.sopremo.AbstractSopremoType;
import eu.stratosphere.sopremo.expressions.EvaluationExpression;
import eu.stratosphere.sopremo.packages.DefaultConstantRegistry;
import eu.stratosphere.sopremo.packages.IConstantRegistry;

public class StackedConstantRegistry extends StackedRegistry<EvaluationExpression, IConstantRegistry> implements
		IConstantRegistry {
	public StackedConstantRegistry() {
		super(new DefaultConstantRegistry());
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7496813129214724902L;

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.packages.IConstantRegistry#put(java.lang.Class)
	 */
	@Override
	public void put(Class<?> javaConstants) {
		this.getTopRegistry().put(javaConstants);
	}
	
	/* (non-Javadoc)
	 * @see eu.stratosphere.sopremo.AbstractSopremoType#createCopy()
	 */
	@Override
	protected AbstractSopremoType createCopy() {
		return new StackedConstantRegistry();
	}
}