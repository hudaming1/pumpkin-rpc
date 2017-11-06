package org.hum.pumpkin.proxy;

import org.hum.pumpkin.invoker.Invoker;
import org.hum.pumpkin.protocol.URL;

public interface ProxyFactory {

	<T> T getProxy(Invoker<T> invoker);

	// TODO 目前没用上
    <T> Invoker<T> getInvoker(T proxy, Class<T> type, URL url) ;
}
