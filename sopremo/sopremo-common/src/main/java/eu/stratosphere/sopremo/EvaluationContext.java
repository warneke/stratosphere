package eu.stratosphere.sopremo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import eu.stratosphere.sopremo.expressions.EvaluationExpression;
import eu.stratosphere.sopremo.operator.Operator;
import eu.stratosphere.sopremo.packages.DefaultConstantRegistry;
import eu.stratosphere.sopremo.packages.DefaultFunctionRegistry;
import eu.stratosphere.sopremo.packages.EvaluationScope;
import eu.stratosphere.sopremo.packages.IConstantRegistry;
import eu.stratosphere.sopremo.packages.IFunctionRegistry;
import eu.stratosphere.sopremo.pact.SopremoUtil;
import eu.stratosphere.sopremo.serialization.GeneralSchema;
import eu.stratosphere.sopremo.serialization.Schema;

/**
 * Provides additional context to the evaluation of {@link Evaluable}s, such as access to all registered functions.
 * 
 * @author Arvid Heise
 */
public class EvaluationContext extends AbstractSopremoType implements ISerializableSopremoType, EvaluationScope {
	private static final long serialVersionUID = 7701485388451926506L;

	private final IFunctionRegistry methodRegistry;

	private final IConstantRegistry constantRegistry;

	private final LinkedList<String> operatorStack = new LinkedList<String>();

	private List<Schema> inputSchemas = new ArrayList<Schema>(), outputSchemas = new ArrayList<Schema>();

	private Schema schema;

	private transient int inputCount = 0;

	private EvaluationExpression resultProjection = EvaluationExpression.VALUE;

	// public LinkedList<Operator<?>> getOperatorStack() {
	// return this.operatorStack;
	// }

	private int taskId;

	/**
	 * Initializes EvaluationContext.
	 */
	public EvaluationContext() {
		this(0, 0, new DefaultFunctionRegistry(), new DefaultConstantRegistry());
	}

	/**
	 * Initializes EvaluationContext.
	 */
	public EvaluationContext(final int numInputs, final int numOutputs, IFunctionRegistry methodRegistry,
			IConstantRegistry constantRegistry) {
		this.methodRegistry = methodRegistry;
		this.constantRegistry = constantRegistry;
		this.setInputsAndOutputs(numInputs, numOutputs);
	}

	/**
	 * Initializes EvaluationContext.
	 */
	public EvaluationContext(EvaluationContext context) {
		this(context.inputSchemas.size(), context.outputSchemas.size(), context.methodRegistry, context.constantRegistry);
		copyPropertiesFrom(context);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.SopremoType#toString(java.lang.StringBuilder)
	 */
	@Override
	public void appendAsString(final Appendable appendable) throws IOException {
		appendable.append("Context @ ").append(this.operatorStack.toString()).append("\n").
			append("Methods: ");
		this.methodRegistry.appendAsString(appendable);
		appendable.append("\nConstants: ");
		this.constantRegistry.appendAsString(appendable);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.AbstractSopremoType#clone()
	 */
	@Override
	public EvaluationContext clone() {
		return (EvaluationContext) super.clone();
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.AbstractSopremoType#copyPropertiesFrom(eu.stratosphere.sopremo.AbstractSopremoType)
	 */
	@Override
	public void copyPropertiesFrom(ISopremoType original) {
		final EvaluationContext context = (EvaluationContext) original;
		this.inputSchemas.addAll(SopremoUtil.deepClone(context.inputSchemas));
		this.outputSchemas.addAll(SopremoUtil.deepClone(context.outputSchemas));
		this.schema = context.schema;
		this.resultProjection = context.resultProjection.clone();
		this.operatorStack.addAll(context.operatorStack);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.packages.RegistryScope#getConstantRegistry()
	 */
	@Override
	public IConstantRegistry getConstantRegistry() {
		return this.constantRegistry;
	}

	public String getCurrentOperator() {
		return this.operatorStack.peek();
	}

	/**
	 * Returns the {@link FunctionRegistry} containing all registered function in the current evaluation context.
	 * 
	 * @return the FunctionRegistry
	 */
	@Override
	public IFunctionRegistry getFunctionRegistry() {
		return this.methodRegistry;
	}

	/**
	 * Returns the inputCount.
	 * 
	 * @return the inputCount
	 */
	public int getInputCount() {
		return this.inputCount;
	}

	/**
	 * Returns the inputSchemas.
	 * 
	 * @return the inputSchemas
	 */
	public Schema getInputSchema(@SuppressWarnings("unused") final int index) {
		return this.schema;
		// return this.inputSchemas[index];
	}

	/**
	 * Returns the outputSchemas.
	 * 
	 * @return the outputSchemas
	 */
	public Schema getOutputSchema(@SuppressWarnings("unused") final int index) {
		return this.schema;
		// return this.outputSchemas[index];
	}

	public EvaluationExpression getResultProjection() {
		return this.resultProjection;
	}

	public int getTaskId() {
		return this.taskId;
	}

	public void incrementInputCount() {
		this.inputCount++;
	}

	public String operatorTrace() {
		final Iterator<String> descendingIterator = this.operatorStack.descendingIterator();
		final StringBuilder builder = new StringBuilder(descendingIterator.next());
		while (descendingIterator.hasNext())
			builder.append("->").append(descendingIterator.next());
		return builder.toString();
	}

	public String popOperator() {
		return this.operatorStack.pop();
	}

	public void pushOperator(final Operator<?> e) {
		// reset inputs to avoid serialization
		this.operatorStack.push(e.getName());
	}

	public void setInputsAndOutputs(final int numInputs, final int numOutputs) {
		for (int index = 0; index < numInputs; index++)
			this.inputSchemas.add(new GeneralSchema());
		for (int index = 0; index < numOutputs; index++)
			this.outputSchemas.add(new GeneralSchema());
	}

	public void setResultProjection(final EvaluationExpression resultProjection) {
		if (resultProjection == null)
			throw new NullPointerException("resultProjection must not be null");

		this.resultProjection = resultProjection;
	}

	/**
	 * @param schema
	 */
	public void setSchema(final Schema schema) {
		this.schema = schema;
	}

	public void setTaskId(final int taskId) {
		this.taskId = taskId;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.AbstractSopremoType#createCopy()
	 */
	@Override
	protected AbstractSopremoType createCopy() {
		return new EvaluationContext(this.inputSchemas.size(), this.outputSchemas.size(),
			(IFunctionRegistry) this.methodRegistry.clone(), (IConstantRegistry) this.constantRegistry.clone());
	}
}
