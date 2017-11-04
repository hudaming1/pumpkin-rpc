package org.hum.pumpkin.client;

import org.hum.pumpkin.invoker.Invoker;
import org.hum.pumpkin.protocol.Protocol;
import org.hum.pumpkin.protocol.URL;
import org.hum.pumpkin.proxy.ProxyFactory;
import org.hum.pumpkin.serviceloader.ServiceLoaderHolder;

public class ReferenceConfig<T> {
	
	private String protocol;
	private String address;
	private int port;
	private String className;
	private Class<T> interfaceType;
	private transient volatile Invoker<T> invoker;
	private static final ProxyFactory PROXY_FACTORY = ServiceLoaderHolder.loadByCache(ProxyFactory.class);
	private static final Protocol PROTOCOL = ServiceLoaderHolder.loadByCache(Protocol.class);
	
	public ReferenceConfig(Class<T> interfaceType) {
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
		
		URL url = new URL(protocol, address, port, className);
		
		invoker = PROTOCOL.refer(interfaceType, url);
		
		return PROXY_FACTORY.getProxy(invoker);
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getAddress() {
		return address;
	}

	public int getPort() {
		return port;
	}

	public void setUrl(String address, int port) {
		this.address = address;
		this.port = port;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}
}
