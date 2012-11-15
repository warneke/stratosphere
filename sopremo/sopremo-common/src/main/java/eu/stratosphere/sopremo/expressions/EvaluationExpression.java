package eu.stratosphere.sopremo.expressions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.stratosphere.sopremo.AbstractSopremoType;
import eu.stratosphere.sopremo.ICloneable;
import eu.stratosphere.sopremo.ISerializableSopremoType;
import eu.stratosphere.sopremo.expressions.tree.ChildIterator;
import eu.stratosphere.sopremo.expressions.tree.ListChildIterator;
import eu.stratosphere.sopremo.pact.SopremoUtil;
import eu.stratosphere.sopremo.type.IJsonNode;
import eu.stratosphere.util.IsEqualPredicate;
import eu.stratosphere.util.IsInstancePredicate;
import eu.stratosphere.util.Predicate;

/**
 * Base class for all evaluable expressions that can form an expression tree.<br>
 * All implementing classes are not thread-safe unless otherwise noted.
 */
public abstract class EvaluationExpression extends AbstractSopremoType implements ISerializableSopremoType,
		Iterable<EvaluationExpression>, ICloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1226647739750484403L;

	/**
	 * Represents an expression that returns the input node without any modifications. The constant is mostly used for
	 * {@link Operator}s that do not perform any transformation to the input, such as a filter operator.
	 */
	public static final EvaluationExpression VALUE = new SingletonExpression("x") {

		/**
		 * 
		 */
		private static final long serialVersionUID = -6430819532311429108L;

		@Override
		public IJsonNode evaluate(final IJsonNode node) {
			return node;
		}

		@Override
		public IJsonNode set(final IJsonNode node, final IJsonNode value) {
			return value;
		};

		@Override
		protected Object readResolve() {
			return VALUE;
		}
	};

	/*
	 * (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public ChildIterator iterator() {
		@SuppressWarnings("unchecked")
		final List<EvaluationExpression> emptyList = Collections.EMPTY_LIST;
		return new ListChildIterator(emptyList.listIterator());
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public final EvaluationExpression clone() {
		EvaluationExpression copy = this.createCopy();
		copy.copyPropertiesFrom(this);
		return copy;
	}

	// private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
	// ois.defaultReadObject();
	// initTransients();
	// }

	protected Object readResolve() {
		initTransients();
		return this;
	}

	protected void initTransients() {
		SopremoUtil.initTransientFields(this);
	}

	/**
	 * Copies the values of the given expression. The expression has the same type as this expression.
	 */
	@SuppressWarnings("unused")
	protected void copyPropertiesFrom(EvaluationExpression original) {
	}

	/**
	 * Creates a copy of this expression with the same properties for the overriding class.<br/>
	 * A successive call to {@link #copyPropertiesFrom(EvaluationExpression)} ensures that all properties of super
	 * classes are applied.<br/>
	 * However, all final fields should be properly initialized by this method.
	 * 
	 * @return a newly created expression
	 */
	protected abstract EvaluationExpression createCopy();

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		return true;
	}

	public EvaluationExpression findFirst(final Predicate<? super EvaluationExpression> predicate) {
		if (predicate.isTrue(this))
			return this;
		for (EvaluationExpression child : this) {
			final EvaluationExpression expr = child.findFirst(predicate);
			if (expr != null)
				return child.findFirst(predicate);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T extends EvaluationExpression> T findFirst(final Class<T> evaluableClass) {
		return (T) this.findFirst(new IsInstancePredicate(evaluableClass));
	}

	@SuppressWarnings("unchecked")
	public <T extends EvaluationExpression> List<T> findAll(final Class<T> evaluableClass) {
		return (List<T>) this.findAll(new IsInstancePredicate(evaluableClass));
	}

	public List<EvaluationExpression> findAll(final Predicate<? super EvaluationExpression> predicate) {
		final ArrayList<EvaluationExpression> expressions = new ArrayList<EvaluationExpression>();
		this.findAll(predicate, expressions);
		return expressions;
	}

	private void findAll(final Predicate<? super EvaluationExpression> predicate,
			final ArrayList<EvaluationExpression> expressions) {
		if (predicate.isTrue(this))
			expressions.add(this);

		for (EvaluationExpression child : this)
			child.findAll(predicate, expressions);
	}

	/**
	 * Recursively invokes the transformation function on all children and on the expression itself.<br>
	 * In general, this method can modify this expression in-place.<br>
	 * To retain the original expression, next to the transformed expression, use {@link #clone()}.
	 * 
	 * @param function
	 *        the transformation function
	 * @return the transformed expression
	 */
	public EvaluationExpression transformRecursively(final TransformFunction function) {
		final ChildIterator iterator = this.iterator();
		while (iterator.hasNext()) {
			EvaluationExpression evaluationExpression = iterator.next();
			iterator.set(evaluationExpression.transformRecursively(function));
		}
		return function.call(this);
	}

	/**
	 * Replaces all expressions that satisfy the <code>replacePredicate</code> with the given
	 * <code>replaceFragment</code> .
	 * 
	 * @param replacePredicate
	 *        the predicate that indicates whether an expression should be replaced
	 * @param replaceFragment
	 *        the expression which should replace another one
	 * @return the expression with the replaces
	 */
	public EvaluationExpression replace(final Predicate<? super EvaluationExpression> replacePredicate,
			final EvaluationExpression replaceFragment) {
		return this.replace(replacePredicate, new TransformFunction() {
			@Override
			public EvaluationExpression call(final EvaluationExpression argument) {
				return replaceFragment;
			}
		});
	}

	/**
	 * Replaces all expressions that satisfy the <code>replacePredicate</code> with the given
	 * <code>replaceFunction</code> .
	 * 
	 * @param replacePredicate
	 *        the predicate that indicates whether an expression should be replaced
	 * @param replaceFunction
	 *        the function that is used to replace an expression
	 * @return the expression with the replaces
	 */
	public EvaluationExpression replace(final Predicate<? super EvaluationExpression> replacePredicate,
			final TransformFunction replaceFunction) {
		return this.transformRecursively(new TransformFunction() {
			@Override
			public EvaluationExpression call(final EvaluationExpression evaluationExpression) {
				return replacePredicate.isTrue(evaluationExpression) ? replaceFunction.call(evaluationExpression)
					: evaluationExpression;
			}
		});
	}

	/**
	 * Replaces all expressions that are equal to <code>toReplace</code> with the given <code>replaceFragment</code> .
	 * 
	 * @param toReplace
	 *        the expressions that should be replaced
	 * @param replaceFragment
	 *        the expression which should replace another one
	 * @return the expression with the replaces
	 */
	public EvaluationExpression replace(final EvaluationExpression toReplace, final EvaluationExpression replaceFragment) {
		return this.replace(new IsEqualPredicate(toReplace), replaceFragment);
	}

	/**
	 * Removes all sub-trees in-place that satisfy the predicate.<br>
	 * If expressions cannot be completely removed, they are replaced by {@link EvaluationExpression#VALUE}.
	 * 
	 * @param predicate
	 *        the predicate that determines whether to remove an expression
	 * @return this expression without removed sub-expressions
	 */
	public EvaluationExpression remove(final Predicate<? super EvaluationExpression> predicate) {
		if (predicate.isTrue(this))
			return VALUE;

		this.removeRecursively(this, predicate);
		return this;
	}

	private void removeRecursively(final EvaluationExpression expressionParent,
			final Predicate<? super EvaluationExpression> predicate) {
		final ChildIterator iterator = expressionParent.iterator();
		while (iterator.hasNext()) {
			EvaluationExpression child = iterator.next();
			if (predicate.isTrue(child)) {
				if (!iterator.canChildrenBeRemoved())
					iterator.set(VALUE);
				else
					iterator.remove();
			} else
				child.removeRecursively(this, predicate);
		}
	}

	/**
	 * Removes all sub-trees in-place that are equal to the given expression.<br>
	 * If expressions cannot be completely removed, they are replaced by {@link EvaluationExpression#VALUE}.
	 * 
	 * @param expressionToRemove
	 *        the expression to compare to
	 * @return this expression without removed sub-expressions
	 */
	public EvaluationExpression remove(final EvaluationExpression expressionToRemove) {
		return this.remove(new IsEqualPredicate(expressionToRemove));
	}

	/**
	 * Removes all sub-trees in-place that start with the given expression type.<br>
	 * If expressions cannot be completely removed, they are replaced by {@link EvaluationExpression#VALUE}.
	 * 
	 * @param expressionType
	 *        the expression type to remove
	 * @return this expression without removed sub-expressions
	 */
	public EvaluationExpression remove(final Class<?> expressionType) {
		return this.remove(new IsInstancePredicate(expressionType));
	}

	/**
	 * Evaluates the given node in the provided context.<br>
	 * The given node can either be a normal {@link JsonNode} or one of the following special nodes:
	 * <ul>
	 * <li>{@link CompactArrayNode} wrapping an array of nodes if the evaluation is performed for more than one
	 * {@link JsonStream},
	 * <li>{@link StreamArrayNode} wrapping an iterator of incoming nodes which is most likely the content of a complete
	 * {@link JsonStream} that is going to be aggregated, or
	 * <li>CompactArrayNode of StreamArrayNodes when aggregating multiple JsonStreams.
	 * </ul>
	 * <br>
	 * Consequently, the result may also be of one of the previously mentioned types.<br>
	 * The ContextType provides additional information that is relevant for the evaluation, for instance all registered
	 * functions in the {@link FunctionRegistry}.
	 * 
	 * @param node
	 *        the node that should be evaluated or a special node representing containing several nodes
	 * @param target
	 *        the target that should be used
	 * @param context
	 *        the context in which the node should be evaluated
	 * @return the node resulting from the evaluation or several nodes wrapped in a special node type
	 */
	public abstract IJsonNode evaluate(IJsonNode node);

	@Override
	public int hashCode() {
		return 37;
	}

	/**
	 * Sets the value of the node specified by this expression using the given {@link EvaluationContext}.
	 * 
	 * @param node
	 *        the node to change
	 * @param value
	 *        the value to set
	 * @param context
	 *        the current <code>EvaluationContext</code>
	 * @return the node or a new node if the expression directly accesses the node
	 */
	public IJsonNode set(final IJsonNode node, final IJsonNode value) {
		throw new UnsupportedOperationException(String.format(
			"Cannot change the value with expression %s of node %s to %s", this, node, value));
	}

	public EvaluationExpression simplify() {
		{
			final ChildIterator iterator = this.iterator();
			while (iterator.hasNext()) {
				EvaluationExpression evaluationExpression = iterator.next();
				iterator.set(evaluationExpression.simplify());
			}
		}
		return this;
	}

	/**
	 * Appends a string representation of this expression to the builder. The method should return the same result as
	 * {@link #toString()} but provides a better performance when a string is composed of several child expressions.
	 * 
	 * @param builder
	 *        the builder to append to
	 */
	@Override
	public void appendAsString(final Appendable appendable) throws IOException {
	}

	public String printAsTree() {
		StringBuilder builder = new StringBuilder();
		try {
			this.printAsTree(builder, 0);
		} catch (IOException e) {
		}
		return builder.toString();
	}

	protected void printAsTree(final Appendable appendable, int level) throws IOException {
		for (int index = 0; index < level; index++)
			appendable.append(' ');
		appendable.append(this.getClass().getSimpleName()).append(' ');
		this.appendAsString(appendable);
		appendable.append('\n');

		for (EvaluationExpression child : this)
			child.printAsTree(appendable, level + 1);
	}

	protected void appendChildExpressions(final Appendable appendable,
			final List<? extends EvaluationExpression> children, final String separator) throws IOException {
		for (int index = 0; index < children.size(); index++) {
			if (children.get(index) == null)
				appendable.append("!null!");
			else
				children.get(index).appendAsString(appendable);
			if (index < children.size() - 1)
				appendable.append(separator);
		}
	}
}
