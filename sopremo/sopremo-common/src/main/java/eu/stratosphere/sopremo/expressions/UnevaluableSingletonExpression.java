package eu.stratosphere.sopremo.expressions;

import eu.stratosphere.sopremo.Singleton;

/**
 * This expression represents a Singleton.
 */
@Singleton
public abstract class UnevaluableSingletonExpression extends UnevaluableExpression {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4108217673663116837L;

	/**
	 * Initializes SingletonExpression.
	 */
	public UnevaluableSingletonExpression(final String textualRepresentation) {
		super(textualRepresentation);
	}

	@Override
	public boolean equals(final Object obj) {
		return obj == this;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.expressions.EvaluationExpression#createCopy()
	 */
	@Override
	protected EvaluationExpression createCopy() {
		return this;
	}

	@Override
	public int hashCode() {
		return this.getClass().hashCode();
	}

	@Override
	protected abstract Object readResolve();

}
