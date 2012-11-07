package eu.stratosphere.sopremo.expressions;

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
public class ArrayCreation extends EvaluationExpression implements ExpressionParent {
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

	@Override
	public IJsonNode evaluate(final IJsonNode node, final IJsonNode target) {
		final IArrayNode targetArray = SopremoUtil.reinitializeTarget(target, ArrayNode.class);

		for (int index = 0; index < this.elements.size(); index++)
			targetArray.add(this.elements.get(index).evaluate(node, targetArray.get(index)));

		return targetArray;
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
		return new ListChildIterator(this.elements.listIterator()) ;
	}

	@Override
	public void toString(final StringBuilder builder) {
		this.appendChildExpressions(builder, this.elements, ", ");
	}
}