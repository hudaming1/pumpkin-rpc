package org.hum.pumpkin.server;

import java.util.ArrayList;
import java.util.List;

import org.hum.pumpkin.exporter.Exporter;
import org.hum.pumpkin.protocol.Protocol;
import org.hum.pumpkin.registry.conf.RegistryConfig;
import org.hum.pumpkin.serviceloader.ServiceLoaderHolder;

public class ServiceConfig<T> {

	private int port;
	private RegistryConfig registryConfig;
	private Class<T> interfaceType;
	private T ref;
	private static final Protocol PROTOCOL = ServiceLoaderHolder.loadByCache(Protocol.class);
	// 记录发布的服务，当server.close时需要知道销毁哪些对象
	private static final List<Exporter<?>> EXPORTER_LIST = new ArrayList<>();

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
		
		Exporter<T> exporter = PROTOCOL.export(this);
		EXPORTER_LIST.add(exporter);
	}
}
