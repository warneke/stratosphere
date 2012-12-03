package eu.stratosphere.sopremo.expressions;

import java.io.IOException;

import eu.stratosphere.sopremo.type.ArrayNode;
import eu.stratosphere.sopremo.type.IArrayNode;
import eu.stratosphere.sopremo.type.IJsonNode;
import eu.stratosphere.sopremo.type.NullNode;

/**
 * Merges several arrays by taking the first non-null value for each respective array.
 * 
 * @author Arvid Heise
 */
@OptimizerHints(scope = Scope.ARRAY, transitive = true, minNodes = 1, maxNodes = OptimizerHints.UNBOUND, iterating = true)
public class ArrayMerger extends EvaluationExpression {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6884623565349727369L;

	private final transient IArrayNode result = new ArrayNode();

	@Override
	public IJsonNode evaluate(final IJsonNode node) {
		this.result.clear();

		for (final IJsonNode nextNode : (IArrayNode) node)
			if (nextNode != NullNode.getInstance()) {
				final IArrayNode array = (IArrayNode) nextNode;
				for (int index = 0; index < array.size(); index++)
					if (this.result.size() <= index)
						this.result.add(array.get(index));
					else if (this.isNull(this.result.get(index)) && !this.isNull(array.get(index)))
						this.result.set(index, array.get(index));
			}

		return this.result;
	}

	private boolean isNull(final IJsonNode value) {
		return value == null || value.isNull();
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.expressions.EvaluationExpression#createCopy()
	 */
	@Override
	protected EvaluationExpression createCopy() {
		return new ArrayMerger();
	}

	@Override
	public void appendAsString(final Appendable appendable) throws IOException {
		appendable.append("[*]+...+[*]");
	}

}
