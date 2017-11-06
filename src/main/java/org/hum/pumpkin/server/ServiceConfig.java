package org.hum.pumpkin.server;

import org.hum.pumpkin.protocol.Protocol;
import org.hum.pumpkin.registry.conf.RegistryConfig;
import org.hum.pumpkin.serviceloader.ServiceLoaderHolder;

public class ServiceConfig<T> {

	private int port;
	private RegistryConfig registryConfig;
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

	public RegistryConfig getRegistryConfig() {
		return registryConfig;
	}

	public void setRegistryConfig(RegistryConfig registryConfig) {
		this.registryConfig = registryConfig;
	}

	public void export() {
		
		PROTOCOL.export(this);
	}
}
