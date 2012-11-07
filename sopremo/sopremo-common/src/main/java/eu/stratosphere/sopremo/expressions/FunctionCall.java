package eu.stratosphere.sopremo.expressions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.stratosphere.sopremo.EvaluationException;
import eu.stratosphere.sopremo.expressions.tree.ChildIterator;
import eu.stratosphere.sopremo.expressions.tree.GenericListChildIterator;
import eu.stratosphere.sopremo.function.SopremoFunction;
import eu.stratosphere.sopremo.packages.EvaluationScope;
import eu.stratosphere.sopremo.type.IJsonNode;
import eu.stratosphere.sopremo.type.JsonUtil;

/**
 * Calls the specified function with the provided parameters and returns the result.
 */
@OptimizerHints(scope = Scope.ANY, minNodes = 0, maxNodes = OptimizerHints.UNBOUND)
public class FunctionCall extends EvaluationExpression implements ExpressionParent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 90022725022477041L;

	private String functionName;

	private SopremoFunction function;

	private List<CachingExpression<IJsonNode>> paramExprs;

	/**
	 * Initializes a MethodCall with the given function name and expressions which evaluate to the method parameters.
	 * 
	 * @param functionName
	 *        the name of the function that should be called
	 * @param function
	 *        the function itself
	 * @param params
	 *        expressions which evaluate to the method parameters
	 */
	public FunctionCall(final String functionName, final SopremoFunction function, final EvaluationExpression... params) {
		this(functionName, function, Arrays.asList(params));
	}

	/**
	 * Initializes a MethodCall with the given function name and expressions which evaluate to the method parameters.
	 * 
	 * @param functionName
	 *        the name of the function that should be called
	 * @param function
	 *        the function itself
	 * @param params
	 *        expressions which evaluate to the method parameters
	 */
	public FunctionCall(final String functionName, final SopremoFunction function,
			final List<EvaluationExpression> params) {
		if (functionName == null)
			throw new NullPointerException("Function name must not be null");
		if (function == null)
			throw new NullPointerException("Function must not be null");
		this.functionName = functionName;
		this.function = function;
		this.paramExprs = CachingExpression.listOfAny(params);
	}

	/**
	 * Initializes a MethodCall with the given function name and expressions which evaluate to the method parameters.
	 * 
	 * @param functionName
	 *        the name of the function that should be called
	 * @param context
	 *        the {@link EvaluationContext} which contains the
	 *        {@link eu.stratosphere.sopremo.packages.IFunctionRegistry} that contains the definition under the function
	 *        name
	 * @param params
	 *        expressions which evaluate to the method parameters
	 */
	public FunctionCall(final String functionName, final EvaluationScope scope,
			final EvaluationExpression... params) {
		this(functionName, checkIfMethodExists(functionName, scope), Arrays.asList(params));
	}

	private static SopremoFunction checkIfMethodExists(final String functionName, final EvaluationScope scope) {
		final SopremoFunction function = (SopremoFunction) scope.getFunctionRegistry().get(functionName);
		if(function == null)
			throw new IllegalArgumentException(String.format("No method %s found", functionName));
		return function;
	}

	/**
	 * Initializes a MethodCall with the given function name and expressions which evaluate to the method parameters.
	 * 
	 * @param functionName
	 *        the name of the function that should be called
	 * @param context
	 *        the {@link EvaluationContext} which contains the
	 *        {@link eu.stratosphere.sopremo.packages.IFunctionRegistry} that contains the definition under the function
	 *        name
	 * @param params
	 *        expressions which evaluate to the method parameters
	 */
	public FunctionCall(final String functionName, final EvaluationScope scope,
			final List<EvaluationExpression> params) {
		this(functionName, checkIfMethodExists(functionName, scope), params);
	}
	
	/* (non-Javadoc)
	 * @see eu.stratosphere.sopremo.expressions.EvaluationExpression#clone()
	 */
	@Override
	public FunctionCall clone() {
		final FunctionCall clone = (FunctionCall) super.clone();
		clone.paramExprs = new ArrayList<CachingExpression<IJsonNode>>(this.paramExprs.size());
		for (CachingExpression<IJsonNode> paramExpr : this.paramExprs) 
			clone.paramExprs.add(paramExpr.clone());
		return clone;
	}

	@Override
	public boolean equals(final Object obj) {
		if (!super.equals(obj))
			return false;
		final FunctionCall other = (FunctionCall) obj;
		return this.function.equals(other.function) && this.paramExprs.equals(other.paramExprs);
	}

	/**
	 * Returns the function.
	 * 
	 * @return the function
	 */
	public SopremoFunction getFunction() {
		return this.function;
	}

	/**
	 * Sets the function to the specified value.
	 *
	 * @param function the function to set
	 */
	public void setFunction(SopremoFunction function) {
		if (function == null)
			throw new NullPointerException("function must not be null");

		this.function = function;
	}
	
	/**
	 * Returns the functionName.
	 * 
	 * @return the functionName
	 */
	public String getFunctionName() {
		return this.functionName;
	}
	
	/**
	 * Returns the paramExprs.
	 * 
	 * @return the paramExprs
	 */
	public List<EvaluationExpression> getParameters() {
		final ArrayList<EvaluationExpression> parameters = new ArrayList<EvaluationExpression>();
		for (CachingExpression<IJsonNode> param : this.paramExprs) 
			parameters.add(param.getInnerExpression());
		return parameters;
	}
	
	/**
	 * Sets the functionName to the specified value.
	 *
	 * @param functionName the functionName to set
	 */
	public void setFunctionName(String functionName) {
		if (functionName == null)
			throw new NullPointerException("functionName must not be null");

		this.functionName = functionName;
	}

	@Override
	public int hashCode() {
		int hash = super.hashCode();
		hash = hash * 53 + this.function.hashCode();
		hash = hash * 53 + this.paramExprs.hashCode();
		return hash;
	}

	@Override
	public IJsonNode evaluate(final IJsonNode node, IJsonNode target) {
		final IJsonNode[] params = new IJsonNode[this.paramExprs.size()];
		for (int index = 0; index < params.length; index++)
			params[index] = this.paramExprs.get(index).evaluate(node);

		try {
			return this.function.call(JsonUtil.asArray(params), target);
		} catch (Exception e) {
			throw new EvaluationException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.expressions.ExpressionParent#iterator()
	 */
	@Override
	public ChildIterator iterator() {
		return new GenericListChildIterator<CachingExpression<IJsonNode>>(this.paramExprs.listIterator()) {
			/* (non-Javadoc)
			 * @see eu.stratosphere.sopremo.expressions.tree.GenericListChildIterator#convert(eu.stratosphere.sopremo.expressions.EvaluationExpression)
			 */
			@Override
			protected CachingExpression<IJsonNode> convert(EvaluationExpression childExpression) {
				return CachingExpression.ofAny(childExpression);
			}
		};
	}

	@Override
	public void toString(final StringBuilder builder) {
		builder.append(this.functionName);
		builder.append('(');
		appendChildExpressions(builder, this.paramExprs, ", ");
		builder.append(')');
	}

}