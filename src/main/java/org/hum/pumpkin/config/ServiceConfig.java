package org.hum.pumpkin.config;

import java.util.ArrayList;
import java.util.List;

import org.hum.pumpkin.common.exception.RpcException;
import org.hum.pumpkin.common.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin.config.registry.RegistryConfig;
import org.hum.pumpkin.protocol.Protocol;
import org.hum.pumpkin.protocol.URL;
import org.hum.pumpkin.protocol.exporter.Exporter;
import org.hum.pumpkin.util.InetUtils;

public class ServiceConfig<T> {

	private String protocol;
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

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
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
		if (interfaceType == null) {
			throw new NullPointerException("export interfaceType mustn't be null!");
		}
		try {
			URL url = new URL(protocol, InetUtils.getLocalAddress(), port, interfaceType.getName());

			Exporter<T> exporter = PROTOCOL.export(interfaceType, ref, url);

			EXPORTER_LIST.add(exporter);
		} catch (Exception e) {
			throw new RpcException("export service[" + interfaceType.getName() + "] fail.", e);
		}
	}
}
