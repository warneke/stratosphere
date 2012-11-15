package eu.stratosphere.sopremo.expressions;

import java.io.IOException;

import javolution.util.FastMap;
import javolution.util.FastMap.Entry;
import eu.stratosphere.sopremo.cache.ExpressionCache;
import eu.stratosphere.sopremo.expressions.tree.ChildIterator;
import eu.stratosphere.sopremo.expressions.tree.NamedChildIterator;
import eu.stratosphere.sopremo.type.ArrayNode;
import eu.stratosphere.sopremo.type.CachingArrayNode;
import eu.stratosphere.sopremo.type.IArrayNode;
import eu.stratosphere.sopremo.type.IJsonNode;

/**
 * Returns a grouped representation of the elements of the given {@link IArrayNode}.
 */
public class GroupingExpression extends EvaluationExpression {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7602198150833087978L;

	private EvaluationExpression groupingExpression;

	/**
	 * Initializes a GroupingExpression with the given expressions.
	 * 
	 * @param groupingExpression
	 *        the expression that should be used to determine the grouping keys
	 * @param resultExpression
	 *        the expression that should be used on the elements within a group
	 */
	public GroupingExpression(final EvaluationExpression groupingExpression, final EvaluationExpression resultExpression) {
		this.groupingExpression = groupingExpression;
		this.resultExpressions = new ExpressionCache<EvaluationExpression>(resultExpression);
	}

	private final transient IArrayNode result = new ArrayNode();

	private transient ExpressionCache<EvaluationExpression> resultExpressions =
		new ExpressionCache<EvaluationExpression>(null);

	@Override
	public IJsonNode evaluate(final IJsonNode node) {
		this.result.clear();

		if (((IArrayNode) node).size() == 0)
			return this.result;

		this.fillGroups((IArrayNode) node);

		final ExpressionCache<EvaluationExpression> resultExpressions = this.resultExpressions;

		int index = 0;
		for (FastMap.Entry<IJsonNode, CachingArrayNode> e = this.groups.head(), end = this.groups.tail(); (e =
			e.getNext()) != end;) {
			final CachingArrayNode group = e.getValue();
			if (!group.isEmpty())
				this.result.add(resultExpressions.get(index++).evaluate(group));
		}

		this.emptyGroups();

		return this.result;
	}

	private void emptyGroups() {
		for (FastMap.Entry<IJsonNode, CachingArrayNode> e = this.groups.head(), end = this.groups.tail(); (e =
			e.getNext()) != end;)
			e.getValue().clear();
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.expressions.ExpressionParent#iterator()
	 */
	@Override
	public ChildIterator iterator() {
		return new NamedChildIterator("groupingExpression", "second") {

			@Override
			protected void set(int index, EvaluationExpression childExpression) {
				if (index == 0)
					GroupingExpression.this.groupingExpression = childExpression;
				else
					GroupingExpression.this.resultExpressions =
						new ExpressionCache<EvaluationExpression>(childExpression);
			}

			@Override
			protected EvaluationExpression get(int index) {
				if (index == 0)
					return GroupingExpression.this.groupingExpression;
				return GroupingExpression.this.resultExpressions.getTemplate();
			}
		};
	}

	private final transient FastMap<IJsonNode, CachingArrayNode> groups = new FastMap<IJsonNode, CachingArrayNode>();

	private void fillGroups(final IArrayNode array) {
		for (final IJsonNode node : array) {
			final IJsonNode key = this.groupingExpression.evaluate(node);
			final Entry<IJsonNode, CachingArrayNode> entry = this.groups.getEntry(key);
			CachingArrayNode group;
			if (entry == null)
				this.groups.put(key.clone(), group = new CachingArrayNode());
			else
				group = entry.getValue();
			group.addClone(node);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + this.groupingExpression.hashCode();
		result = prime * result + getResultExpression().hashCode();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.expressions.EvaluationExpression#createCopy()
	 */
	@Override
	protected EvaluationExpression createCopy() {
		return new GroupingExpression(this.groupingExpression.clone(), getResultExpression().clone());
	}

	@Override
	public boolean equals(final Object obj) {
		if (!super.equals(obj))
			return false;
		final GroupingExpression other = (GroupingExpression) obj;
		return this.groupingExpression.equals(other.groupingExpression)
			&& getResultExpression().equals(other.resultExpressions.getTemplate());
	}

	@Override
	public void appendAsString(final Appendable appendable) throws IOException {
		appendable.append("g(");
		this.groupingExpression.appendAsString(appendable);
		appendable.append(") -> ");
		getResultExpression().appendAsString(appendable);
	}

	private EvaluationExpression getResultExpression() {
		return this.resultExpressions.getTemplate();
	}

}
