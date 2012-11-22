/***********************************************************************************************************************
 *
 * Copyright (C) 2010 by the Stratosphere project (http://stratosphere.eu)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 **********************************************************************************************************************/
package eu.stratosphere.sopremo.query;

import java.beans.IndexedPropertyDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import eu.stratosphere.sopremo.AbstractSopremoType;
import eu.stratosphere.sopremo.ISerializableSopremoType;
import eu.stratosphere.sopremo.operator.Operator;
import eu.stratosphere.sopremo.packages.DefaultRegistry;
import eu.stratosphere.sopremo.packages.IRegistry;
import eu.stratosphere.sopremo.packages.NameChooser;
import eu.stratosphere.util.reflect.ReflectUtil;

public class OperatorInfo<Op extends Operator<Op>> extends AbstractSopremoType implements ISerializableSopremoType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4513611163697385746L;

	public static class InputPropertyInfo extends PropertyInfo {

		/**
		 * 
		 */
		private static final long serialVersionUID = 5380394239325476067L;

		/**
		 * Initializes OperatorPropertyInfo.
		 * 
		 * @param name
		 * @param descriptor
		 */
		protected InputPropertyInfo(final String name, final IndexedPropertyDescriptor descriptor) {
			super(name, descriptor);
		}

		@Override
		public IndexedPropertyDescriptor getDescriptor() {
			return (IndexedPropertyDescriptor) super.getDescriptor();
		}

		public Object getValue(final Operator<?> operator, final int index) {
			try {
				return this.getDescriptor().getIndexedReadMethod().invoke(operator, new Object[] { index });
			} catch (final Exception e) {
				throw new RuntimeException(
					String.format("Could not get property %s of %s", this.getName(), operator), e);
			}
		}

		/*
		 * (non-Javadoc)
		 * @see eu.stratosphere.sopremo.AbstractSopremoType#createCopy()
		 */
		@Override
		protected AbstractSopremoType createCopy() {
			return new InputPropertyInfo(getName(), getDescriptor());
		}

		public void setValue(final Operator<?> operator, final int index, final Object value) {
			try {
				this.getDescriptor().getIndexedWriteMethod().invoke(operator, index, value);
			} catch (final Exception e) {
				throw new RuntimeException(
					String.format("Could not set property %s of %s to %s", this.getName(), operator, value), e);
			}
		}

	}

	public static class OperatorPropertyInfo extends PropertyInfo {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1973407943532893076L;

		/**
		 * Initializes OperatorPropertyInfo.
		 * 
		 * @param name
		 * @param descriptor
		 */
		public OperatorPropertyInfo(final String name, final PropertyDescriptor descriptor) {
			super(name, descriptor);
		}

		public Object getValue(final Operator<?> operator) {
			try {
				return this.getDescriptor().getReadMethod().invoke(operator, new Object[0]);
			} catch (final Exception e) {
				throw new RuntimeException(
					String.format("Could not get property %s of %s", this.getName(), operator), e);
			}
		}

		/*
		 * (non-Javadoc)
		 * @see eu.stratosphere.sopremo.AbstractSopremoType#createCopy()
		 */
		@Override
		protected AbstractSopremoType createCopy() {
			return new OperatorPropertyInfo(getName(), getDescriptor());
		}

		public void setValue(final Operator<?> operator, final Object value) {
			try {
				this.getDescriptor().getWriteMethod().invoke(operator, value);
			} catch (final Exception e) {
				throw new RuntimeException(
					String.format("Could not set property %s of %s to %s", this.getName(), operator, value), e);
			}
		}

	}

	public abstract static class PropertyInfo extends AbstractSopremoType implements ISerializableSopremoType {
		/**
		 * 
		 */
		private static final long serialVersionUID = 4684255642695196446L;

		private final String name;

		private final PropertyDescriptor descriptor;

		public PropertyInfo(final String name, final PropertyDescriptor descriptor) {
			this.name = name;
			this.descriptor = descriptor;
		}

		/**
		 * Returns the descriptor.
		 * 
		 * @return the descriptor
		 */
		public PropertyDescriptor getDescriptor() {
			return this.descriptor;
		}

		public boolean isFlag() {
			return this.getType() == Boolean.TYPE;
		}

		/**
		 * Returns the name.
		 * 
		 * @return the name
		 */
		public String getName() {
			return this.name;
		}

		/**
		 * @return
		 */
		public Class<?> getType() {
			return this.descriptor.getPropertyType();
		}

		/*
		 * (non-Javadoc)
		 * @see eu.stratosphere.sopremo.ISopremoType#appendAsString(java.lang.Appendable)
		 */
		@Override
		public void appendAsString(Appendable appendable) throws IOException {
			appendable.append(this.name);
		}
	}

	private Class<Op> operatorClass;

	private final String name;

	private final NameChooser propertyNameChooser;

	private final IRegistry<OperatorPropertyInfo> operatorPropertyRegistry =
		new DefaultRegistry<OperatorPropertyInfo>();

	private final IRegistry<InputPropertyInfo> inputPropertyRegistry = new DefaultRegistry<InputPropertyInfo>();

	public OperatorInfo(final Class<Op> operatorClass, final String opName,
			final NameChooser propertyNameChooser) {
		this.operatorClass = operatorClass;
		this.name = opName;
		this.propertyNameChooser = propertyNameChooser;
	}

	private void initProperties() {
		try {
			final PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(this.operatorClass)
				.getPropertyDescriptors();
			for (final PropertyDescriptor propertyDescriptor : propertyDescriptors)
				if (propertyDescriptor.getWriteMethod() != null
					|| propertyDescriptor instanceof IndexedPropertyDescriptor &&
					((IndexedPropertyDescriptor) propertyDescriptor).getIndexedWriteMethod() != null) {
					String name = this.propertyNameChooser.choose(
						(String[]) propertyDescriptor.getValue(Operator.Info.NAME_NOUNS),
						(String[]) propertyDescriptor.getValue(Operator.Info.NAME_VERB),
						(String[]) propertyDescriptor.getValue(Operator.Info.NAME_ADJECTIVE),
						(String[]) propertyDescriptor.getValue(Operator.Info.NAME_PREPOSITION));
					if (name == null)
						name = propertyDescriptor.getName();

					if (propertyDescriptor.getValue(Operator.Info.INPUT) == Boolean.TRUE)
						this.inputPropertyRegistry.put(name, new InputPropertyInfo(name,
							(IndexedPropertyDescriptor) propertyDescriptor));
					else
						this.operatorPropertyRegistry.put(name, new OperatorPropertyInfo(name, propertyDescriptor));
				}
		} catch (final IntrospectionException e) {
			e.printStackTrace();
		}
	}

	private AtomicBoolean needsInitialization = new AtomicBoolean(true);

	protected boolean needsInitialization() {
		return this.needsInitialization.getAndSet(false);
	}

	public IRegistry<InputPropertyInfo> getInputPropertyRegistry() {
		if (this.needsInitialization())
			this.initProperties();
		return this.inputPropertyRegistry;
	}

	public String getName() {
		return this.name;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.AbstractSopremoType#createCopy()
	 */
	@Override
	protected AbstractSopremoType createCopy() {
		return new OperatorInfo<Op>(this.operatorClass, this.name, this.propertyNameChooser);
	}

	public IRegistry<OperatorPropertyInfo> getOperatorPropertyRegistry() {
		if (this.needsInitialization())
			this.initProperties();
		return this.operatorPropertyRegistry;
	}

	public Operator<Op> newInstance() {
		return ReflectUtil.newInstance(this.operatorClass);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.ISopremoType#appendAsString(java.lang.Appendable)
	 */
	@Override
	public void appendAsString(Appendable appendable) throws IOException {
		appendable.append(this.name);
	}
}