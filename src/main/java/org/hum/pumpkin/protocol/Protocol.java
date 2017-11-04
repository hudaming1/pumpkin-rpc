package org.hum.pumpkin.protocol;

import org.hum.pumpkin.exporter.Exporter;
import org.hum.pumpkin.invoker.Invoker;
import org.hum.pumpkin.server.ServiceConfig;

public interface Protocol {
	
	<T> Exporter<T> export(ServiceConfig<T> serviceConfig);

	<T> Invoker<T> refer(Class<T> classType, URL url);
}
