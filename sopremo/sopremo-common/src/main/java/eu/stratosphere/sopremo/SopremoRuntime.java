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
package eu.stratosphere.sopremo;

/**
 * @author Arvid Heise
 */
public class SopremoRuntime {
	/**
	 * 
	 */
	private static final SopremoRuntime INSTANCE = new SopremoRuntime();

	private ThreadLocal<EvaluationContext> currentEvaluationContext = new ThreadLocal<EvaluationContext>();

	public static SopremoRuntime getInstance() {
		return INSTANCE;
	}

	/**
	 * Returns the currentEvaluationContext.
	 * 
	 * @return the currentEvaluationContext
	 */
	public EvaluationContext getCurrentEvaluationContext() {
		return this.currentEvaluationContext.get();
	}

	/**
	 * Sets the currentEvaluationContext to the specified value.
	 * 
	 * @param currentEvaluationContext
	 *        the currentEvaluationContext to set
	 */
	public void setCurrentEvaluationContext(EvaluationContext currentEvaluationContext) {
		if (currentEvaluationContext == null)
			throw new NullPointerException("currentEvaluationContext must not be null");

		this.currentEvaluationContext.set(currentEvaluationContext);
	}

	public void mockRuntime() {
		this.setCurrentEvaluationContext(new EvaluationContext());
	}

}
