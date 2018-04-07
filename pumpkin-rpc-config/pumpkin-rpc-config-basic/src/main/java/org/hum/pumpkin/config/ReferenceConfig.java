package org.hum.pumpkin.config;

import org.hum.pumpkin.common.exception.ReferenceException;
import org.hum.pumpkin.common.serviceloader.ExtensionLoader;
import org.hum.pumpkin.common.url.URL;
import org.hum.pumpkin.common.url.URLConstant;
import org.hum.pumpkin.logger.Logger;
import org.hum.pumpkin.logger.LoggerFactory;
import org.hum.pumpkin.protocol.Protocol;
import org.hum.pumpkin.protocol.invoker.Invoker;
import org.hum.pumpkin.proxy.ProxyFactory;

/**
 * Procotol层需要2类Config，一是Service，另一则是Runtime/Request
 */
public class ReferenceConfig<T> {

	private transient volatile Invoker<T> invoker;
	private static final ProxyFactory PROXY_FACTORY = ExtensionLoader.getExtensionLoader(ProxyFactory.class).getDefault();
	private static final Protocol PROTOCOL = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptive();
	private static final Logger logger = LoggerFactory.getLogger(ReferenceConfig.class);
	private String protocol;
	private String address = "0.0.0.0";
	private int port;
	private String className;
	private Class<T> interfaceType;
	private String registry;

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
			if (registry != null && registry.trim().length() > 0) {
				url.buildParam(URLConstant.REGISTRY, registry);
			}
			invoker = PROTOCOL.refer(interfaceType, url);
			T proxyInstances = PROXY_FACTORY.getProxy(invoker);
			logger.info("proxy " + className + ", use protocol " + protocol);
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

	public String getRegistry() {
		return registry;
	}

	public void setRegistry(String registry) {
		this.registry = registry;
	}
}
