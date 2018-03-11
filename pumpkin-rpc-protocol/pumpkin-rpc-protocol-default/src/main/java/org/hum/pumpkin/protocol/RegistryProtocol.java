package org.hum.pumpkin.protocol;

import org.hum.pumpkin.common.url.URL;
import org.hum.pumpkin.protocol.exporter.Exporter;
import org.hum.pumpkin.protocol.invoker.Invoker;
import org.hum.pumpkin.protocol.invoker.cluster.FailoverClusterInvoker;

public class RegistryProtocol implements Protocol {

	@Override
	public <T> Exporter<T> export(Class<T> classType, T instances, URL url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Invoker<T> refer(Class<T> classType, URL url) {
		return new FailoverClusterInvoker<>();
	}
}
