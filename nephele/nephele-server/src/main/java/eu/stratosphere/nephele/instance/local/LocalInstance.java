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

package eu.stratosphere.nephele.instance.local;

import eu.stratosphere.nephele.instance.AbstractInstance;
import eu.stratosphere.nephele.instance.HardwareDescription;
import eu.stratosphere.nephele.instance.InstanceConnectionInfo;
import eu.stratosphere.nephele.instance.InstanceType;
import eu.stratosphere.nephele.rpc.RPCService;
import eu.stratosphere.nephele.topology.NetworkNode;
import eu.stratosphere.nephele.topology.NetworkTopology;

public class LocalInstance extends AbstractInstance {

	public LocalInstance(final InstanceType instanceType, final InstanceConnectionInfo instanceConnectionInfo,
			final RPCService rpcService, final NetworkNode parentNode, final NetworkTopology networkTopology,
			final HardwareDescription hardwareDescription) {
		super(instanceType, instanceConnectionInfo, rpcService, parentNode, networkTopology, hardwareDescription);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {

		return this.getInstanceConnectionInfo().toString();
	}

}
