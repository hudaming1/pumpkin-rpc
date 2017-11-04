package org.hum.pumpkin.server;

import org.hum.pumpkin.protocol.Protocol;
import org.hum.pumpkin.serviceloader.ServiceLoaderHolder;

public class ServiceConfig<T> {

	private int port;
	private Class<T> interfaceType;
	private T ref;
	private static final Protocol PROTOCOL = ServiceLoaderHolder.loadByCache(Protocol.class);

	public Class<T> getInterfaceType() {
		return interfaceType;
	}

	public void setInterfaceType(Class<T> interfaceType) {
		this.interfaceType = interfaceType;
	}

	public T getRef() {
		return ref;
	}

	public void setRef(T ref) {
		this.ref = ref;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void export() {
		
		// 1.
		
		// 2.
		
		PROTOCOL.export(this);
	}
}
