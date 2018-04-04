package org.hum.pumpkin.config;

import org.hum.pumpkin.common.exception.ReferenceException;
import org.hum.pumpkin.common.serviceloader.ExtensionLoader;
import org.hum.pumpkin.common.url.URL;
import org.hum.pumpkin.common.url.URLConstant;
import org.hum.pumpkin.protocol.Protocol;
import org.hum.pumpkin.protocol.invoker.Invoker;
import org.hum.pumpkin.proxy.ProxyFactory;
import org.hum.pumpkin.registry.RegistryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Procotol层需要2类Config，一是Service，另一则是Runtime/Request
 */
public class ReferenceConfig<T> {

	private transient volatile Invoker<T> invoker;
	private static final ProxyFactory PROXY_FACTORY = ExtensionLoader.getExtensionLoader(ProxyFactory.class).get();
	// TODO 目前这种扩展机制不支持加载多实现类
	private static final Protocol PROTOCOL = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptive();
	private static final Logger logger = LoggerFactory.getLogger(ReferenceConfig.class);
	private String protocol;
	private String address = "0.0.0.0";
	private int port;
	private String className;
	private Class<T> interfaceType;
	private RegistryConfig registryConfig;

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

			if (registryConfig != null) {
				url.buildParam(URLConstant.REGISTRY_NAME, registryConfig.getName());
				url.buildParam(URLConstant.REGISTRY_ADDRESS, registryConfig.getAddress());
				url.buildParam(URLConstant.REGISTRY_PORT, registryConfig.getPort());
			}
			
			// TODO 其他服务相关配置，可以增加到url对象里
//			url.buildParam(key, value)
			
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

	public void setRegistryConfig(RegistryConfig registryConfig) {
		this.registryConfig = registryConfig;
	}
	
	public RegistryConfig getRegistryConfig() {
		return registryConfig;
	}
}
