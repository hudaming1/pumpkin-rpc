package org.hum.pumpkin.protocol;

import org.hum.pumpkin.invoker.Invoker;
import org.hum.pumpkin.server.ServiceConfig;

public interface Protocol {
	
	void export(ServiceConfig<?> serviceConfig);

	<T> Invoker<T> refer(Class<T> classType, URL url);
}
