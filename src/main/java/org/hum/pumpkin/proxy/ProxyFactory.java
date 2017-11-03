package org.hum.pumpkin.proxy;

import org.hum.pumpkin.invoker.Invoker;

public interface ProxyFactory {

	<T> T getProxy(Invoker<T> invoker);
}
