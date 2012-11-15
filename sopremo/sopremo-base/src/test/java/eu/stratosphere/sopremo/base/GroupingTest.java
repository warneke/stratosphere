package eu.stratosphere.sopremo.base;

import static eu.stratosphere.sopremo.expressions.ExpressionUtil.makePath;
import static eu.stratosphere.sopremo.type.JsonUtil.createPath;

import org.junit.Test;

import eu.stratosphere.sopremo.CoreFunctions;
import eu.stratosphere.sopremo.EvaluationContext;
import eu.stratosphere.sopremo.SopremoTest;
import eu.stratosphere.sopremo.expressions.ArrayAccess;
import eu.stratosphere.sopremo.expressions.ArrayProjection;
import eu.stratosphere.sopremo.expressions.ConstantExpression;
import eu.stratosphere.sopremo.expressions.FunctionCall;
import eu.stratosphere.sopremo.expressions.InputSelection;
import eu.stratosphere.sopremo.expressions.ObjectAccess;
import eu.stratosphere.sopremo.expressions.ObjectCreation;
import eu.stratosphere.sopremo.testing.SopremoTestPlan;

public class GroupingTest extends SopremoTest<Grouping> {
	@Override
	protected Grouping createDefaultInstance(final int index) {
		final Grouping aggregation = new Grouping().
			withResultProjection(new ConstantExpression(index));
		return aggregation;
	}

	// @Test
	// public void shouldGroupThreeSources() {
	// final SopremoTestPlan sopremoPlan = new SopremoTestPlan(3, 1);
	// sopremoPlan.getEvaluationContext().getFunctionRegistry().put(DefaultFunctions.class);
	//
	// final BatchAggregationExpression batch = new BatchAggregationExpression();
	//
	// final ObjectCreation transformation = new ObjectCreation();
	// transformation.addMapping("dept",
	// PathExpression.wrapIfNecessary(new InputSelection(0), batch.add(DefaultFunctions.FIRST), new
	// ObjectAccess("dept")));
	// transformation.addMapping("deptName", createPath("1", "[0]", "name"));
	// transformation.addMapping("emps",
	// PathExpression.wrapIfNecessary(new InputSelection(0), batch.add(DefaultFunctions.SORT, new ObjectAccess("id"))));
	// transformation.addMapping("numEmps",
	// PathExpression.wrapIfNecessary(new InputSelection(0), batch.add(DefaultFunctions.COUNT)));
	// transformation.addMapping("expenses",
	// PathExpression.wrapIfNecessary(new InputSelection(2),
	// new ArrayProjection(new ArithmeticExpression(new ObjectAccess("costPerItem"),
	// ArithmeticOperator.MULTIPLICATION, new ObjectAccess("count"))),
	// new AggregationExpression(DefaultFunctions.SUM)));
	//
	// final Grouping aggregation = new Grouping().withResultProjection(transformation);
	// aggregation.setInputs(sopremoPlan.getInputOperators(0, 3));
	// aggregation.setGroupingKey(0, createPath("dept"));
	// aggregation.setGroupingKey(1, createPath("did"));
	// aggregation.setGroupingKey(2, createPath("dept_id"));
	//
	// sopremoPlan.getOutputOperator(0).setInputs(aggregation);
	// sopremoPlan.getInput(0).
	// addObject("id", 1, "dept", 1, "income", 12000).
	// addObject("id", 2, "dept", 1, "income", 13000).
	// addObject("id", 3, "dept", 2, "income", 15000).
	// addObject("id", 4, "dept", 1, "income", 10000).
	// addObject("id", 5, "dept", 3, "income", 8000).
	// addObject("id", 6, "dept", 2, "income", 5000).
	// addObject("id", 7, "dept", 1, "income", 24000);
	// sopremoPlan.getInput(1).
	// addObject("did", 1, "name", "development").
	// addObject("did", 2, "name", "marketing").
	// addObject("did", 3, "name", "sales");
	// sopremoPlan.getInput(2).
	// addObject("item", "copy paper", "count", 100, "costPerItem", 1, "dept_id", 1).
	// addObject("item", "copy paper", "count", 10000, "costPerItem", 2, "dept_id", 2).
	// addObject("item", "copy paper", "count", 1000, "costPerItem", 1, "dept_id", 3).
	// addObject("item", "poster", "count", 100, "costPerItem", 500, "dept_id", 2).
	// addObject("item", "poster", "count", 10, "costPerItem", 300, "dept_id", 3);
	// sopremoPlan.getExpectedOutput(0).
	// addObject("dept", 1, "deptName", "development", "emps", new int[] { 1, 2, 4, 7 }, "numEmps", 4, "expenses",
	// 100).
	// addObject("dept", 2, "deptName", "marketing", "emps", new int[] { 3, 6 }, "numEmps", 2, "expenses",
	// 20000 + 50000).
	// addObject("dept", 3, "deptName", "sales", "emps", new int[] { 5 }, "numEmps", 1, "expenses", 1000 + 3000);
	//
	// sopremoPlan.run();
	// }

	@Test
	public void shouldGroupTwoSources() {
		final SopremoTestPlan sopremoPlan = new SopremoTestPlan(2, 1);
		final EvaluationContext context = sopremoPlan.getEvaluationContext();
		context.getFunctionRegistry().put(CoreFunctions.class);

		final ObjectCreation transformation = new ObjectCreation();
		transformation.addMapping("dept",
			makePath(new InputSelection(0), new ArrayAccess(0), new ObjectAccess("dept")));
		transformation.addMapping("deptName",
			makePath(new InputSelection(1), new ArrayAccess(0), new ObjectAccess("name")));
		transformation.addMapping("emps", new FunctionCall("sort", context,
			makePath(new InputSelection(0), new ArrayProjection(new ObjectAccess("id")))));
		transformation.addMapping("numEmps", new FunctionCall("count", context, new InputSelection(0)));

		final Grouping aggregation = new Grouping().withResultProjection(transformation);
		aggregation.setInputs(sopremoPlan.getInputOperators(0, 2));
		aggregation.setGroupingKey(0, createPath("dept"));
		aggregation.setGroupingKey(1, createPath("did"));

		sopremoPlan.getOutputOperator(0).setInputs(aggregation);
		sopremoPlan.getInput(0).
			addObject("id", 1, "dept", 1, "income", 12000).
			addObject("id", 2, "dept", 1, "income", 13000).
			addObject("id", 3, "dept", 2, "income", 15000).
			addObject("id", 4, "dept", 1, "income", 10000).
			addObject("id", 5, "dept", 3, "income", 8000).
			addObject("id", 6, "dept", 2, "income", 5000).
			addObject("id", 7, "dept", 1, "income", 24000);
		sopremoPlan.getInput(1).
			addObject("did", 1, "name", "development").
			addObject("did", 2, "name", "marketing").
			addObject("did", 3, "name", "sales");
		sopremoPlan.getExpectedOutput(0).
			addObject("dept", 1, "deptName", "development", "emps", new int[] { 1, 2, 4, 7 }, "numEmps", 4).
			addObject("dept", 2, "deptName", "marketing", "emps", new int[] { 3, 6 }, "numEmps", 2).
			addObject("dept", 3, "deptName", "sales", "emps", new int[] { 5 }, "numEmps", 1);

		sopremoPlan.run();
	}

	@Test
	public void shouldGroupTwoSourcesWithInputSelection() {
		final SopremoTestPlan sopremoPlan = new SopremoTestPlan(2, 1);
		final EvaluationContext context = sopremoPlan.getEvaluationContext();
		context.getFunctionRegistry().put(CoreFunctions.class);

		final ObjectCreation transformation = new ObjectCreation();
		transformation.addMapping("dept",
			makePath(new InputSelection(0), new ArrayAccess(0), new ObjectAccess("dept")));
		transformation.addMapping("deptName",
			makePath(new InputSelection(1), new ArrayAccess(0), new ObjectAccess("name")));
		transformation.addMapping("emps", new FunctionCall("sort", context,
			makePath(new InputSelection(0), new ArrayProjection(new ObjectAccess("id")))));
		transformation.addMapping("numEmps", new FunctionCall("count", context, new InputSelection(0)));

		final Grouping aggregation = new Grouping().withResultProjection(transformation);
		aggregation.setInputs(sopremoPlan.getInputOperators(0, 2));
		aggregation.setGroupingKey(0, createPath("0", "dept"));
		aggregation.setGroupingKey(1, createPath("1", "did"));

		sopremoPlan.getOutputOperator(0).setInputs(aggregation);
		sopremoPlan.getInput(0).
			addObject("id", 1, "dept", 1, "income", 12000).
			addObject("id", 2, "dept", 1, "income", 13000).
			addObject("id", 3, "dept", 2, "income", 15000).
			addObject("id", 4, "dept", 1, "income", 10000).
			addObject("id", 5, "dept", 3, "income", 8000).
			addObject("id", 6, "dept", 2, "income", 5000).
			addObject("id", 7, "dept", 1, "income", 24000);
		sopremoPlan.getInput(1).
			addObject("did", 1, "name", "development").
			addObject("did", 2, "name", "marketing").
			addObject("did", 3, "name", "sales");
		sopremoPlan.getExpectedOutput(0).
			addObject("dept", 1, "deptName", "development", "emps", new int[] { 1, 2, 4, 7 }, "numEmps", 4).
			addObject("dept", 2, "deptName", "marketing", "emps", new int[] { 3, 6 }, "numEmps", 2).
			addObject("dept", 3, "deptName", "sales", "emps", new int[] { 5 }, "numEmps", 1);

		sopremoPlan.run();
	}

	@Test
	public void shouldGroupWithSingleSource() {
		final SopremoTestPlan sopremoPlan = new SopremoTestPlan(1, 1);
		final EvaluationContext context = sopremoPlan.getEvaluationContext();
		context.getFunctionRegistry().put(CoreFunctions.class);

		final ObjectCreation transformation = new ObjectCreation();
		transformation.addMapping("d", makePath(new InputSelection(0), new ArrayAccess(0), new ObjectAccess("dept")));
		transformation.addMapping("total", new FunctionCall("sum", context,
			makePath(new InputSelection(0), new ArrayProjection(new ObjectAccess("income")))));

		final Grouping aggregation = new Grouping().withResultProjection(transformation);
		aggregation.setInputs(sopremoPlan.getInputOperator(0));
		aggregation.setGroupingKey(0, createPath("dept"));

		sopremoPlan.getOutputOperator(0).setInputs(aggregation);
		sopremoPlan.getInput(0).
			addObject("id", 1, "dept", 1, "income", 12000).
			addObject("id", 2, "dept", 1, "income", 13000).
			addObject("id", 3, "dept", 2, "income", 15000).
			addObject("id", 4, "dept", 1, "income", 10000).
			addObject("id", 5, "dept", 3, "income", 8000).
			addObject("id", 6, "dept", 2, "income", 5000).
			addObject("id", 7, "dept", 1, "income", 24000);
		sopremoPlan.getExpectedOutput(0).
			addObject("d", 1, "total", 59000).
			addObject("d", 2, "total", 20000).
			addObject("d", 3, "total", 8000);

		sopremoPlan.run();
	}

	@Test
	public void shouldGroupWithSingleSourceWithInputSelection() {
		final SopremoTestPlan sopremoPlan = new SopremoTestPlan(1, 1);
		final EvaluationContext context = sopremoPlan.getEvaluationContext();
		context.getFunctionRegistry().put(CoreFunctions.class);

		final ObjectCreation transformation = new ObjectCreation();
		transformation.addMapping("d", 
			makePath(new InputSelection(0), new ArrayAccess(0), new ObjectAccess("dept")));
		transformation.addMapping("total", new FunctionCall("sum", context,
			makePath(new InputSelection(0), new ArrayProjection(new ObjectAccess("income")))));

		final Grouping aggregation = new Grouping().withResultProjection(transformation);
		aggregation.setInputs(sopremoPlan.getInputOperator(0));
		aggregation.setGroupingKey(0, createPath("0", "dept"));

		sopremoPlan.getOutputOperator(0).setInputs(aggregation);
		sopremoPlan.getInput(0).
			addObject("id", 1, "dept", 1, "income", 12000).
			addObject("id", 2, "dept", 1, "income", 13000).
			addObject("id", 3, "dept", 2, "income", 15000).
			addObject("id", 4, "dept", 1, "income", 10000).
			addObject("id", 5, "dept", 3, "income", 8000).
			addObject("id", 6, "dept", 2, "income", 5000).
			addObject("id", 7, "dept", 1, "income", 24000);
		sopremoPlan.getExpectedOutput(0).
			addObject("d", 1, "total", 59000).
			addObject("d", 2, "total", 20000).
			addObject("d", 3, "total", 8000);

		sopremoPlan.run();
	}

	@Test
	public void shouldPerformSimpleGroupBy() {
		final SopremoTestPlan sopremoPlan = new SopremoTestPlan(1, 1);
		sopremoPlan.getEvaluationContext().getFunctionRegistry().put(CoreFunctions.class);

		final Grouping aggregation = new Grouping().withResultProjection(CoreFunctions.COUNT.asExpression());
		aggregation.setInputs(sopremoPlan.getInputOperator(0));
		sopremoPlan.getOutputOperator(0).setInputs(aggregation);
		sopremoPlan.getInput(0).
			addObject("id", 1, "dept", 1, "income", 12000).
			addObject("id", 2, "dept", 1, "income", 13000).
			addObject("id", 3, "dept", 2, "income", 15000).
			addObject("id", 4, "dept", 1, "income", 10000).
			addObject("id", 5, "dept", 3, "income", 8000).
			addObject("id", 6, "dept", 2, "income", 5000).
			addObject("id", 7, "dept", 1, "income", 24000);
		sopremoPlan.getExpectedOutput(0).
			addValue(7);

		sopremoPlan.run();
	}
}
