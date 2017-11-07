package org.hum.pumpkin.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.hum.pumpkin.invoker.Invoker;
import org.hum.pumpkin.invoker.RpcInvocation;

public class InvokerInvocationHandler<T> implements InvocationHandler {
	
	private Invoker<T> invoker;
	
	public InvokerInvocationHandler(Invoker<T> invoker) {
		this.invoker = invoker;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		RpcInvocation invocation = new RpcInvocation(method.getName(), method.getParameterTypes(), args);
		return invoker.invoke(invocation).get();
	}
}
