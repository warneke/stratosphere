package eu.stratosphere.sopremo.expressions;

import java.io.IOException;

import eu.stratosphere.sopremo.Singleton;

/**
 * This expression represents a Singleton.
 */
@Singleton
public abstract class SingletonExpression extends EvaluationExpression {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4108217673663116837L;

	private final String textualRepresentation;

	/**
	 * Initializes SingletonExpression.
	 */
	public SingletonExpression(final String textualRepresentation) {
		this.textualRepresentation = textualRepresentation;
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

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.expressions.EvaluationExpression#toString(java.lang.StringBuilder)
	 */
	@Override
	public void appendAsString(final Appendable appendable) throws IOException {
		super.appendAsString(appendable);
		appendable.append(this.textualRepresentation);
	}
}
