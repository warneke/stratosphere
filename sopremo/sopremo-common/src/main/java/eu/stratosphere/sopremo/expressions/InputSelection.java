package eu.stratosphere.sopremo.expressions;

import java.io.IOException;

import javolution.text.TypeFormat;
import eu.stratosphere.sopremo.EvaluationException;
import eu.stratosphere.sopremo.type.IArrayNode;
import eu.stratosphere.sopremo.type.IJsonNode;

/**
 * Returns the element of an array which is saved at the specified index.
 */
@OptimizerHints(scope = Scope.ANY, minNodes = 1, maxNodes = OptimizerHints.UNBOUND)
public class InputSelection extends PathSegmentExpression {
	private static final long serialVersionUID = -3767687525625180324L;

	private final int index;

	/**
	 * Initializes an InputSelection with the given index.
	 * 
	 * @param index
	 *        the index of the element that should be returned
	 */
	public InputSelection(final int index) {
		this.index = index;
	}

	@Override
	public boolean equals(final Object obj) {
		if (!super.equals(obj))
			return false;
		final InputSelection other = (InputSelection) obj;
		return this.index == other.index;
	}

	@Override
	protected IJsonNode evaluateSegment(final IJsonNode node) {
		if (!node.isArray())
			throw new EvaluationException("Cannot select input " + node.getClass().getSimpleName());
		return ((IArrayNode) node).get(this.index);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.expressions.EvaluationExpression#createCopy()
	 */
	@Override
	protected EvaluationExpression createCopy() {
		return new InputSelection(this.index);
	}

	/**
	 * Returns the index
	 * 
	 * @return the index
	 */
	public int getIndex() {
		return this.index;
	}

	@Override
	public int hashCode() {
		return 37 * super.hashCode() + this.index;
	}

	@Override
	public void appendAsString(final Appendable appendable) throws IOException {
		this.appendInputAsString(appendable);
		appendable.append("in");
		TypeFormat.format(this.index, appendable);
	}
}