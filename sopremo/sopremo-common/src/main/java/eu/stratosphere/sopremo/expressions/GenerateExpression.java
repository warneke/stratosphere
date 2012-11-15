package eu.stratosphere.sopremo.expressions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Formatter;

import eu.stratosphere.sopremo.EvaluationContext;
import eu.stratosphere.sopremo.SopremoRuntime;
import eu.stratosphere.sopremo.type.IJsonNode;
import eu.stratosphere.sopremo.type.TextNode;

/**
 * Generates an unique, pattern-based ID.
 */
public class GenerateExpression extends EvaluationExpression {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3577122499530356668L;

	private final String pattern;

	private long id;

	private transient EvaluationContext context;

	/**
	 * Initializes a GenerateExpression with the given pattern.
	 * 
	 * @param patternString
	 *        The pattern that should be used to generate the ID's.
	 *        Use '%s' inside the pattern string to specify the positions of the context based part and the expression
	 *        based part of the generated ID's.
	 */
	public GenerateExpression(String patternString) {
		final int patternPos = patternString.indexOf("%");
		if (patternPos == -1)
			patternString += "%s_%s";
		else if (patternString.indexOf("%", patternPos + 1) == -1)
			patternString = patternString.replaceAll("%", "%s_%");
		this.pattern = patternString;
		this.context = SopremoRuntime.getInstance().getCurrentEvaluationContext();
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.expressions.EvaluationExpression#createCopy()
	 */
	@Override
	protected EvaluationExpression createCopy() {
		return new GenerateExpression(this.pattern);
	}

	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		ois.defaultReadObject();
		this.context = SopremoRuntime.getInstance().getCurrentEvaluationContext();
	}

	private transient final TextNode result = new TextNode();

	private transient final Formatter formatter = new Formatter(this.result);

	@Override
	public IJsonNode evaluate(final IJsonNode node) {
		this.result.clear();
		this.formatter.format(this.pattern, this.context.getTaskId(), this.id++);
		return this.result;
	}
}
