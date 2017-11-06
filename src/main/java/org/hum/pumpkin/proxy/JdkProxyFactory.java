package org.hum.pumpkin.proxy;

import java.lang.reflect.Proxy;

import org.hum.pumpkin.invoker.Invoker;
import org.hum.pumpkin.protocol.URL;

public class JdkProxyFactory implements ProxyFactory {

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getProxy(Invoker<T> invoker) {
		return (T) Proxy.newProxyInstance(invoker.getClass().getClassLoader(), new Class[] { invoker.getType() }, new InvokerInvocationHandler<>(invoker));
	}

	@Override
	public <T> Invoker<T> getInvoker(T proxy, Class<T> type, URL url) {
		// TODO Auto-generated method stub
		return null;
	}
}
