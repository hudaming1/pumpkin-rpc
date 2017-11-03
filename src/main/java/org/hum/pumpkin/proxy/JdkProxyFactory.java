package org.hum.pumpkin.proxy;

import java.lang.reflect.Proxy;

import org.hum.pumpkin.invoker.Invoker;

public class JdkProxyFactory implements ProxyFactory {

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getProxy(Invoker<T> invoker) {
		return (T) Proxy.newProxyInstance(invoker.getClass().getClassLoader(), new Class[] { invoker.getClass() }, new InvokerInvocationHandler<>(invoker));
	}
}
