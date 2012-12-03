package eu.stratosphere.sopremo.expressions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.stratosphere.sopremo.expressions.tree.ChildIterator;
import eu.stratosphere.sopremo.expressions.tree.ListChildIterator;
import eu.stratosphere.sopremo.pact.SopremoUtil;
import eu.stratosphere.sopremo.type.ArrayNode;
import eu.stratosphere.sopremo.type.IArrayNode;
import eu.stratosphere.sopremo.type.IJsonNode;

/**
 * Creates an array of the given expressions.
 * 
 * @author Arvid Heise
 */
@OptimizerHints(scope = Scope.ANY)
public class ArrayCreation extends EvaluationExpression {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1681947333740209285L;

	private final List<EvaluationExpression> elements;

	/**
	 * Initializes ArrayCreation to create an array of the given expressions.
	 * 
	 * @param elements
	 *        the expressions that evaluate to the elements in the array
	 */
	public ArrayCreation(final EvaluationExpression... elements) {
		this.elements = new ArrayList<EvaluationExpression>(Arrays.asList(elements));
	}

	/**
	 * Initializes ArrayCreation to create an array of the given expressions.
	 * 
	 * @param elements
	 *        the expressions that evaluate to the elements in the array
	 */
	public ArrayCreation(final List<EvaluationExpression> elements) {
		this.elements = new ArrayList<EvaluationExpression>(elements);
	}

	public ArrayCreation add(EvaluationExpression expression) {
		this.elements.add(expression);
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.expressions.EvaluationExpression#createCopy()
	 */
	@Override
	protected EvaluationExpression createCopy() {
		return new ArrayCreation(SopremoUtil.deepClone(this.elements));
	}

	public int size() {
		return this.elements.size();
	}

	@Override
	public boolean equals(final Object obj) {
		if (!super.equals(obj))
			return false;
		final ArrayCreation other = (ArrayCreation) obj;
		return this.elements.equals(other.elements);
	}

	private final IArrayNode result = new ArrayNode();

	@Override
	public IJsonNode evaluate(final IJsonNode node) {
		this.result.clear();

		for (int index = 0; index < this.elements.size(); index++)
			this.result.add(this.elements.get(index).evaluate(node));

		return this.result;
	}

	@Override
	public int hashCode() {
		return 53 * super.hashCode() + this.elements.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.expressions.ExpressionParent#iterator()
	 */
	@Override
	public ChildIterator iterator() {
		return new ListChildIterator(this.elements.listIterator());
	}

	@Override
	public void appendAsString(final Appendable appendable) throws IOException {
		this.appendChildExpressions(appendable, this.elements, ", ");
	}
}