package org.hum.pumpkin_version1.proxy;

import java.lang.reflect.InvocationHandler;

@SuppressWarnings("unchecked")
public class DefaultProxy implements Proxy {

	@Override
	public <T> T proxy(ClassLoader classLoader, Class<T> type, InvocationHandler invocationHandler) {
		return (T) java.lang.reflect.Proxy.newProxyInstance(classLoader, new Class[] { type }, invocationHandler);
	}
}
