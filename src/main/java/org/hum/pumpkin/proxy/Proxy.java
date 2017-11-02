package org.hum.pumpkin.proxy;

import java.lang.reflect.InvocationHandler;

public interface Proxy {

	public <T> T proxy(ClassLoader classLoader, Class<T> type, InvocationHandler invocationHandler);
}
