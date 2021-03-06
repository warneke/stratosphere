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

package eu.stratosphere.nephele.profiling.impl;

import java.io.IOException;
import java.util.List;

import edu.berkeley.icsi.memngt.rpc.RPCProtocol;
import eu.stratosphere.nephele.profiling.impl.types.InternalProfilingData;

public interface ProfilerImplProtocol extends RPCProtocol {

	void reportProfilingData(List<InternalProfilingData> profilingData) throws IOException, InterruptedException;
}
