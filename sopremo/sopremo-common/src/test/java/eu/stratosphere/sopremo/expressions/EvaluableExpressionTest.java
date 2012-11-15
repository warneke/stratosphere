package eu.stratosphere.sopremo.expressions;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import junit.framework.Assert;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import eu.stratosphere.sopremo.EvaluationContext;
import eu.stratosphere.sopremo.ISopremoType;
import eu.stratosphere.sopremo.Singleton;
import eu.stratosphere.sopremo.SopremoTest;
import eu.stratosphere.sopremo.cache.ISopremoCache;
import eu.stratosphere.util.reflect.ReflectUtil;

@Ignore
public abstract class EvaluableExpressionTest<T extends EvaluationExpression> extends SopremoTest<T> {
	protected EvaluationContext context;

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.SopremoTest#shouldComplyEqualsContract()
	 */
	@Override
	@Test
	public void shouldComplyEqualsContract() {
		super.shouldComplyEqualsContract();
	}

	@Before
	public void initContext() {
		this.context = new EvaluationContext();
	}

	@Override
	protected void initVerifier(final EqualsVerifier<T> equalVerifier) {
		super.initVerifier(equalVerifier);
		equalVerifier.suppress(Warning.TRANSIENT_FIELDS);
	}

	@Test
	public void testToString() throws IOException {
		final StringBuilder builder = new StringBuilder();
		this.first.appendAsString(builder);
		Assert.assertFalse(
			"builder did not write anything - override this test if it is indeed the desired behavior",
			builder.length() == 0);
	}

	@Test
	public void testClone() throws IllegalAccessException {
		final T original = this.first;
		final EvaluationExpression clone = original.clone();

		testPropertyClone(this.first.getClass(), original, clone);
	}

	protected void testPropertyClone(Class<?> type, Object original, Object clone) throws IllegalAccessException {
		for (Field field : type.getDeclaredFields()) {
			final Class<?> propertyType = field.getType();
			if ((field.getModifiers() & Modifier.STATIC) != 0)
				continue;
			field.setAccessible(true);
			if (ISopremoCache.class.isAssignableFrom(propertyType) || ISopremoType.class.isAssignableFrom(propertyType)) {
				final Object originalValue = field.get(original);
				if (originalValue == null)
					continue;
				final Object clonedValue = field.get(clone);
				if (ReflectUtil.getAnnotation(originalValue.getClass(), Singleton.class) != null)
					Assert.assertSame(String.format("Singleton field %s is cloned improperly", field.getName()),
						clonedValue, originalValue);
				else
					Assert.assertNotSame(String.format("Field %s is not cloned properly", field.getName()),
						clonedValue, originalValue);
			}
		}
		if (type.getSuperclass() != null)
			testPropertyClone(type.getSuperclass(), original, clone);
	}
}
