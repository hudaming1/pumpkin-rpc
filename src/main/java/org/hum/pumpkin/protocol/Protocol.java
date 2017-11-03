package org.hum.pumpkin.protocol;

import org.hum.pumpkin.invoker.Invoker;

public interface Protocol {

	<T> Invoker<T> refer(Class<T> classType, URL url);
}
