package org.hum.pumpkin.protocol.invoker;

import org.hum.pumpkin.common.url.URL;

public class PumpkinInvokerFactory<T> implements InvokerFactory<T> {

	@Override
	public Invoker<T> create(Class<T> classType, URL url) {
		return new DirectInvoker<>(classType, url);
	}
}
