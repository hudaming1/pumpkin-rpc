package org.hum.pumpkin.protocol;

import org.hum.pumpkin.exporter.Exporter;
import org.hum.pumpkin.invoker.Invoker;

public interface Protocol {
	
	<T> Exporter<T> export(Class<T> classType);

	<T> Invoker<T> refer(Class<T> classType, URL url);
}
