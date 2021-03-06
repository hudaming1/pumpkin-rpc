package org.hum.pumpkin.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.hum.pumpkin.common.exception.RpcException;
import org.hum.pumpkin.protocol.invoker.Invoker;
import org.hum.pumpkin.protocol.invoker.RpcInvocation;

public class JdkProxyFactory implements ProxyFactory {

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getProxy(final Invoker<T> invoker) {
		return (T) Proxy.newProxyInstance(invoker.getType().getClassLoader(), new Class[] { invoker.getType() }, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				RpcInvocation invocation = new RpcInvocation(invoker.getType(), method.getName(), method.getParameterTypes(), args);
				return invoker.invoke(invocation).get();
			}
		});
	}

	@Override
	public Object invoke(Object instances, String methodName, Class<?>[] paramTypes, Object[] params) {
		try {
			return instances.getClass().getMethod(methodName, paramTypes).invoke(instances, params);
		} catch (Exception e) {
			throw new RpcException("reflect invoke [" + instances.getClass().getName() + "." + methodName + "] exception", e);
		}
	}
}
