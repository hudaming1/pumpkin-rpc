package org.hum.pumpkin.config;

import org.hum.pumpkin.common.exception.ReferenceException;
import org.hum.pumpkin.protocol.Protocol;
import org.hum.pumpkin.protocol.URL;
import org.hum.pumpkin.protocol.invoker.Invoker;
import org.hum.pumpkin.proxy.ProxyFactory;
import org.hum.pumpkin.serviceloader.ServiceLoaderHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReferenceConfig<T> {

	private transient volatile Invoker<T> invoker;
	private static final ProxyFactory PROXY_FACTORY = ServiceLoaderHolder.loadByCache(ProxyFactory.class);
	private static final Protocol PROTOCOL = ServiceLoaderHolder.loadByCache(Protocol.class);
	private static final Logger logger = LoggerFactory.getLogger(ReferenceConfig.class);
	private String protocol;
	private String address;
	private int port;
	private String className;
	private Class<T> interfaceType;

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
		try {
			invoker = PROTOCOL.refer(interfaceType, url);

			T proxyInstances = PROXY_FACTORY.getProxy(invoker);
			logger.info("proxy " + className + ", use protocol " + protocol + " refer " + address + ":" + port);
			
			return proxyInstances;
		} catch (Exception ce) {
			throw new ReferenceException("proxy " + className + " occured exception, url[" + url + "]", ce);
		}
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
