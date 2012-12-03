package eu.stratosphere.sopremo.function;

import eu.stratosphere.sopremo.AbstractSopremoType;
import eu.stratosphere.sopremo.expressions.EvaluationExpression;
import eu.stratosphere.util.reflect.ReflectUtil;

public abstract class MacroBase extends Callable<EvaluationExpression, EvaluationExpression[]> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 93515191173062050L;

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.AbstractSopremoType#createCopy()
	 */
	@Override
	protected AbstractSopremoType createCopy() {
		return ReflectUtil.newInstance(this.getClass());
	}
}
