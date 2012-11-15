package eu.stratosphere.sopremo.base;

import java.util.Iterator;

import eu.stratosphere.sopremo.EvaluationException;
import eu.stratosphere.sopremo.expressions.ArrayAccess;
import eu.stratosphere.sopremo.expressions.EvaluationExpression;
import eu.stratosphere.sopremo.operator.ElementaryOperator;
import eu.stratosphere.sopremo.pact.JsonCollector;
import eu.stratosphere.sopremo.pact.SopremoMap;
import eu.stratosphere.sopremo.type.IArrayNode;
import eu.stratosphere.sopremo.type.IJsonNode;
import eu.stratosphere.sopremo.type.IObjectNode;
import eu.stratosphere.sopremo.type.JsonUtil;
import eu.stratosphere.sopremo.type.NullNode;
import eu.stratosphere.sopremo.type.TextNode;

/**
 * Splits an object into multiple outgoing tuples.<br>
 * This operator provides a means to emit more than one tuple in contrast to most other base operators.
 * 
 * @author Arvid Heise
 */
public class ObjectSplit extends ElementaryOperator<ObjectSplit> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2967507260239105002L;

	private EvaluationExpression objectPath = EvaluationExpression.VALUE,
			valueProjection = new ArrayAccess(0);

	public EvaluationExpression getObjectPath() {
		return this.objectPath;
	}

	public EvaluationExpression getValueProjection() {
		return this.valueProjection;
	}

	/**
	 * (element, index/fieldName, array/object, node) -&gt; value
	 * 
	 * @param valueProjection
	 * @return this
	 */
	public ObjectSplit withValueProjection(EvaluationExpression valueProjection) {
		this.setValueProjection(valueProjection);
		return this;
	}

	/**
	 * (element, index/fieldName, array/object, node) -&gt; value
	 * 
	 * @param valueProjection
	 */
	public void setValueProjection(EvaluationExpression valueProjection) {
		this.valueProjection = valueProjection;
	}

	public ObjectSplit setObjectProjection(EvaluationExpression objectPath) {
		this.objectPath = objectPath;
		return this;
	}

	public ObjectSplit withObjectProjection(EvaluationExpression objectProjection) {
		this.setObjectProjection(objectProjection);
		return this;
	}

	public static class Implementation extends SopremoMap {
		private EvaluationExpression objectPath;

		private EvaluationExpression valueProjection;

		@Override
		protected void map(IJsonNode value, JsonCollector out) {
			final IJsonNode targetValue = this.objectPath.evaluate(value);
			if (!targetValue.isObject())
				throw new EvaluationException("Cannot split non-object");
			final IObjectNode object = (IObjectNode) targetValue;

			final Iterator<String> fieldNames = object.getFieldNames();
			final TextNode fieldNode = TextNode.valueOf("");
			IArrayNode contextNode = JsonUtil.asArray(NullNode.getInstance(), fieldNode, object, value);
			while (fieldNames.hasNext()) {
				String field = fieldNames.next();
				fieldNode.setValue(field);
				out.collect(this.valueProjection.evaluate(contextNode));
			}
		}
	}
}
