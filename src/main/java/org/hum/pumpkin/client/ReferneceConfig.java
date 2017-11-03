package org.hum.pumpkin.client;

import org.hum.pumpkin.invoker.Invoker;
import org.hum.pumpkin.protocol.Protocol;
import org.hum.pumpkin.protocol.URL;
import org.hum.pumpkin.proxy.ProxyFactory;
import org.hum.pumpkin.serviceloader.ServiceLoaderHolder;

public class ReferneceConfig<T> {
	
	private String className;
	private Class<T> interfaceType;
	private transient volatile Invoker<T> invoker;
	private static final ProxyFactory proxyFactory = ServiceLoaderHolder.loadByCache(ProxyFactory.class);
	private static final Protocol protocol = ServiceLoaderHolder.loadByCache(Protocol.class);
	
	public ReferneceConfig(Class<T> interfaceType) {
		this.interfaceType = interfaceType;
		this.className = interfaceType.getName();
	}

	public String getClassName() {
		return className;
	}

	public Class<T> getInterfaceType() {
		return interfaceType;
	}
	
	public T get() {
		
		URL url = null;
		
		invoker = protocol.refer(interfaceType, url);
		
		return proxyFactory.getProxy(invoker);
	}
}
