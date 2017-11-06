package org.hum.pumpkin.protocol;

import org.hum.pumpkin.invoker.Invoker;
import org.hum.pumpkin.invoker.cluster.FailoverClusterInvoker;
import org.hum.pumpkin.server.ServiceConfig;

public class RegistryProtocol implements Protocol {

	@Override
	public void export(ServiceConfig<?> serviceConfig) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> Invoker<T> refer(Class<T> classType, URL url) {
		return new FailoverClusterInvoker<>();
	}
}
