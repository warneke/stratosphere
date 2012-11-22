package eu.stratosphere.sopremo.base;

import eu.stratosphere.nephele.configuration.Configuration;
import eu.stratosphere.sopremo.expressions.EvaluationExpression;
import eu.stratosphere.sopremo.expressions.ObjectAccess;
import eu.stratosphere.sopremo.operator.ElementaryOperator;
import eu.stratosphere.sopremo.operator.InputCardinality;
import eu.stratosphere.sopremo.operator.Property;
import eu.stratosphere.sopremo.pact.JsonCollector;
import eu.stratosphere.sopremo.pact.SopremoMap;
import eu.stratosphere.sopremo.type.IArrayNode;
import eu.stratosphere.sopremo.type.IJsonNode;
import eu.stratosphere.sopremo.type.INumericNode;
import eu.stratosphere.sopremo.type.IObjectNode;
import eu.stratosphere.sopremo.type.IntNode;
import eu.stratosphere.sopremo.type.JsonUtil;
import eu.stratosphere.sopremo.type.LongNode;
import eu.stratosphere.sopremo.type.ObjectNode;
import eu.stratosphere.sopremo.type.TextNode;

@InputCardinality(1)
public class GlobalEnumeration extends ElementaryOperator<GlobalEnumeration> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8552367347318407324L;

	public static final EvaluationExpression CONCATENATION = new ConcatenatingExpression();

	public static final EvaluationExpression LONG_COMBINATION = new LongExpression();

	public final EvaluationExpression AUTO_ENUMERATION = new EvaluationExpression() {
		private static final long serialVersionUID = -5506784974227617703L;

		@Override
		public IJsonNode set(IJsonNode node, IJsonNode value) {
			if (node.isObject()) {
				((IObjectNode) node).put(GlobalEnumeration.this.idFieldName, value);
				return node;
			}
			ObjectNode objectNode = new ObjectNode();
			objectNode.put(GlobalEnumeration.this.idFieldName, value);
			objectNode.put(GlobalEnumeration.this.valueFieldName, node);
			return objectNode;
		}

		/*
		 * (non-Javadoc)
		 * @see eu.stratosphere.sopremo.expressions.EvaluationExpression#createCopy()
		 */
		@Override
		protected EvaluationExpression createCopy() {
			return GlobalEnumeration.this.AUTO_ENUMERATION;
		}

		@Override
		public IJsonNode evaluate(IJsonNode node) {
			return node;
		}
	};

	private EvaluationExpression enumerationExpression = this.AUTO_ENUMERATION;

	private EvaluationExpression idGeneration = CONCATENATION;

	private String idFieldName = "_ID", valueFieldName = "value";

	public EvaluationExpression getEnumerationExpression() {
		return this.enumerationExpression;
	}

	public String getIdFieldName() {
		return this.idFieldName;
	}

	public EvaluationExpression getIdGeneration() {
		return this.idGeneration;
	}

	public EvaluationExpression getIdAccess() {
		return new ObjectAccess(this.idFieldName);
	}

	@Property
	public void setEnumerationExpression(final EvaluationExpression enumerationExpression) {
		if (enumerationExpression == null)
			throw new NullPointerException();

		this.enumerationExpression = enumerationExpression;
	}

	@Property
	public void setIdFieldName(final String enumerationFieldName) {
		if (enumerationFieldName == null)
			throw new NullPointerException();

		this.idFieldName = enumerationFieldName;
	}

	public GlobalEnumeration withIdFieldName(String enumerationFieldName) {
		this.setIdFieldName(enumerationFieldName);
		return this;
	}

	public GlobalEnumeration withValueFieldName(String valueFieldName) {
		this.setValueFieldName(valueFieldName);
		return this;
	}

	public GlobalEnumeration withEnumerationExpression(EvaluationExpression enumerationExpression) {
		this.setEnumerationExpression(enumerationExpression);
		return this;
	}

	public GlobalEnumeration withIdGeneration(EvaluationExpression idGeneration) {
		this.setIdGeneration(idGeneration);
		return this;
	}

	@Property
	public void setIdGeneration(final EvaluationExpression idGeneration) {
		if (idGeneration == null)
			throw new NullPointerException("idGeneration must not be null");

		this.idGeneration = idGeneration;
	}

	public String getValueFieldName() {
		return this.valueFieldName;
	}

	@Property
	public void setValueFieldName(String valueFieldName) {
		if (valueFieldName == null)
			throw new NullPointerException("valueFieldName must not be null");

		this.valueFieldName = valueFieldName;
	}

	/**
	 * @author Arvid Heise
	 */
	private static final class LongExpression extends EvaluationExpression {
		/**
		 * 
		 */
		private static final long serialVersionUID = -9084196126957908547L;

		private final transient LongNode result = new LongNode();

		/*
		 * (non-Javadoc)
		 * @see
		 * eu.stratosphere.sopremo.expressions.EvaluationExpression#evaluate(eu.stratosphere.sopremo.type.IJsonNode,
		 * eu.stratosphere.sopremo.type.IJsonNode, eu.stratosphere.sopremo.EvaluationContext)
		 */
		@Override
		public IJsonNode evaluate(IJsonNode node) {
			final IArrayNode values = (IArrayNode) node;
			this.result.setValue((((INumericNode) values.get(0)).getLongValue() << 48)
				+ ((INumericNode) values.get(1)).getLongValue());
			return this.result;
		}

		/* (non-Javadoc)
		 * @see eu.stratosphere.sopremo.expressions.EvaluationExpression#createCopy()
		 */
		@Override
		protected EvaluationExpression createCopy() {
			return new LongExpression();
		}
	}

	/**
	 * @author Arvid Heise
	 */
	private static final class ConcatenatingExpression extends EvaluationExpression {
		/**
		 * 
		 */
		private static final long serialVersionUID = -3340948936846733311L;

		private final transient StringBuilder builder = new StringBuilder();

		private final transient TextNode result = new TextNode();

		@Override
		public IJsonNode evaluate(final IJsonNode node) {
			final IArrayNode values = (IArrayNode) node;
			this.builder.setLength(0);
			this.builder.append(((INumericNode) values.get(0)).getIntValue());
			this.builder.append('_');
			this.builder.append(((INumericNode) values.get(1)).getIntValue());
			this.result.setValue(this.builder);
			return this.result;
		}

		/* (non-Javadoc)
		 * @see eu.stratosphere.sopremo.expressions.EvaluationExpression#createCopy()
		 */
		@Override
		protected EvaluationExpression createCopy() {
			return new ConcatenatingExpression();
		}
	}

	public static class Implementation extends SopremoMap {
		private EvaluationExpression enumerationExpression;

		private EvaluationExpression idGeneration;

		private LongNode counter;

		private IArrayNode params;

		@Override
		public void open(Configuration parameters) {
			super.open(parameters);
			final IntNode taskId = new IntNode(parameters.getInteger("pact.parallel.task.id", 0));
			this.counter = LongNode.valueOf(0);
			this.params = JsonUtil.asArray(taskId, this.counter);
		}

		@Override
		protected void map(final IJsonNode value, final JsonCollector out) {
			this.counter.setValue(this.counter.getLongValue() + 1);
			final IJsonNode id = this.idGeneration.evaluate(this.params);
			out.collect(this.enumerationExpression.set(value, id));
		}
	}

}
