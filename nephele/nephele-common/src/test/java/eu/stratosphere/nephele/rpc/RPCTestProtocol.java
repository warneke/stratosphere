/***********************************************************************************************************************
 *
 * Copyright (C) 2010-2012 by the Stratosphere project (http://stratosphere.eu)
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

package eu.stratosphere.nephele.rpc;

import java.io.IOException;
import java.util.List;
import java.util.TooManyListenersException;

/**
 * This interface represents a test protocol used as part of the RPC unit tests.
 * 
 * @author warneke
 */
public interface RPCTestProtocol extends RPCProtocol {

	int testMethod(boolean par1, int par2, List<String> par3) throws IOException, InterruptedException;

	void methodWithRegisteredThrowable() throws IOException, InterruptedException;

	void methodWithUnregisteredThrowable() throws IOException, InterruptedException, TooManyListenersException;
}
