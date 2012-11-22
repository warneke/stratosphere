package eu.stratosphere.sopremo.expressions;

import java.io.IOException;

import eu.stratosphere.sopremo.Singleton;
import eu.stratosphere.sopremo.type.IJsonNode;
import eu.stratosphere.sopremo.type.JsonUtil;
import eu.stratosphere.sopremo.type.MissingNode;
import eu.stratosphere.sopremo.type.NullNode;

/**
 * Represents all constants.
 */
@OptimizerHints(scope = Scope.ANY)
public class ConstantExpression extends EvaluationExpression {
	/**
	 * @author Arvid Heise
	 */
	@Singleton
	private static final class SingletonConstant extends ConstantExpression {
		/**
		 * 
		 */
		private static final long serialVersionUID = -2375203649638430872L;

		/**
		 * Initializes MissingConstant.
		 * 
		 * @param constant
		 */
		private SingletonConstant(IJsonNode constant) {
			super(constant);
		}

		@Override
		protected Object readResolve() {
			return ConstantExpression.MISSING;
		}

		@Override
		protected EvaluationExpression createCopy() {
			return this;
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4270374147359826240L;

	private final IJsonNode constant;

	public static final EvaluationExpression MISSING = new ConstantExpression(MissingNode.getInstance()) {
		/**
		 * 
		 */
		private static final long serialVersionUID = 7674060699864203775L;

		@Override
		protected EvaluationExpression createCopy() {
			return this;
		}

		@Override
		protected Object readResolve() {
			return MISSING;
		}
	};

	public static final EvaluationExpression NULL = new ConstantExpression(NullNode.getInstance()) {

		/**
		 * 
		 */
		private static final long serialVersionUID = -2375203649638430872L;

		@Override
		protected EvaluationExpression createCopy() {
			return this;
		}

		@Override
		protected Object readResolve() {
			return ConstantExpression.NULL;
		}
	};

	/**
	 * Initializes a ConstantExpression with the given JsonNode.
	 * 
	 * @param constant
	 *        the node that should be represented by this ConstantExpression
	 */
	public ConstantExpression(final IJsonNode constant) {
		this.constant = constant;
	}

	/**
	 * Initializes a ConstantExpression. The given constant will be mapped to a JsonNode before initializing this
	 * expression.
	 * 
	 * @param constant
	 *        this Objects JsonNode representation should be represented by this ConstantExpression
	 */
	public ConstantExpression(final Object constant) {
		this.constant = JsonUtil.OBJECT_MAPPER.valueToTree(constant);
	}

	/**
	 * Returns the constant.
	 * 
	 * @return the constant
	 */
	public IJsonNode getConstant() {
		return this.constant;
	}

	@Override
	public boolean equals(final Object obj) {
		if (!super.equals(obj))
			return false;
		final ConstantExpression other = (ConstantExpression) obj;
		return this.constant.equals(other.constant);
	}

	@Override
	public IJsonNode evaluate(final IJsonNode node) {
		// we can ignore 'target' because no new Object is created
		return this.constant;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.expressions.EvaluationExpression#createCopy()
	 */
	@Override
	protected EvaluationExpression createCopy() {
		return new ConstantExpression(this.constant.clone());
	}

	@Override
	public int hashCode() {
		return 41 * super.hashCode() + this.constant.hashCode();
	}

	@Override
	public void appendAsString(final Appendable appendable) throws IOException {
		if (this.constant instanceof CharSequence) {
			appendable.append("\'");
			this.constant.appendAsString(appendable);
			appendable.append("\'");
		}
		else
			this.constant.appendAsString(appendable);
	}

}