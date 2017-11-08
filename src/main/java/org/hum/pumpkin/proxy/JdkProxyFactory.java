package org.hum.pumpkin.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

import org.hum.pumpkin.common.exception.RpcException;
import org.hum.pumpkin.invoker.Invoker;

public class JdkProxyFactory implements ProxyFactory {

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getProxy(Invoker<T> invoker) {
		return (T) Proxy.newProxyInstance(invoker.getClass().getClassLoader(), new Class[] { invoker.getType() }, new InvokerInvocationHandler<>(invoker));
	}

	@Override
	public Object invoke(Object instances, String methodName, Class<?>[] paramTypes, Object[] params) {
		try {
			return instances.getClass().getMethod(methodName, paramTypes).invoke(instances, params);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			throw new RpcException("reflect invoke [" + instances.getClass().getName() + "." + methodName + "] exception", e);
		}
	}
}
