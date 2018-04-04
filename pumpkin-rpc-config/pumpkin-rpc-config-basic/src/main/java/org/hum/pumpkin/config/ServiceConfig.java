package org.hum.pumpkin.config;

import java.util.ArrayList;
import java.util.List;

import org.hum.pumpkin.common.exception.RpcException;
import org.hum.pumpkin.common.serviceloader.ExtensionLoader;
import org.hum.pumpkin.common.url.URL;
import org.hum.pumpkin.common.url.URLConstant;
import org.hum.pumpkin.protocol.Protocol;
import org.hum.pumpkin.protocol.exporter.Exporter;
import org.hum.pumpkin.registry.RegistryConfig;

public class ServiceConfig<T> {

	// 服务端使用协议（如果是多协议，则可以为服务指定使用某一协议）
	private List<ProtocolConfig> protocols;
	// 发布地址（目前只是127.0.0.1）
	private String host = "127.0.0.1";
	// 发布服务类型
	private Class<T> interfaceType;
	// 发布服务对象引用，也是实现类实例
	private T ref;
	// 目前仅支持单注册中心，多注册中心太复杂了，先不弄了
	private RegistryConfig registryConfig;
	// TODO 协议后期其实可以同时共存多个，所以这里使用的SPI需要向Dubbo学习改进
	private static final ExtensionLoader<Protocol> ProtocolLoader = ExtensionLoader.getExtensionLoader(Protocol.class);
	// 记录发布的服务，当server.close时需要知道销毁哪些对象
	private static final List<Exporter<?>> EXPORTER_LIST = new ArrayList<Exporter<?>>();

	public Class<T> getInterfaceType() {
		return interfaceType;
	}

	public void setInterfaceType(Class<T> interfaceType) {
		this.interfaceType = interfaceType;
	}

	public List<ProtocolConfig> getProtocols() {
		return protocols;
	}

	public void setProtocols(List<ProtocolConfig> protocols) {
		this.protocols = protocols;
	}

	public T getRef() {
		return ref;
	}

	public void setRef(T ref) {
		this.ref = ref;
	}

	public RegistryConfig getRegistryConfig() {
		return registryConfig;
	}

	public void setRegistryConfig(RegistryConfig registryConfig) {
		this.registryConfig = registryConfig;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void export() {
		if (interfaceType == null) {
			throw new NullPointerException("export interfaceType mustn't be null!");
		}
		try {
			for (ProtocolConfig protocol : protocols) {
				URL url = new URL(protocol.getName(), host, protocol.getPort(), interfaceType.getName());
				
				// TODO 其他服务层的配置信息，需要配置到url.param中
				if (registryConfig != null) {
					url.buildParam(URLConstant.REGISTRY_NAME, registryConfig.getName());
					url.buildParam(URLConstant.REGISTRY_ADDRESS, registryConfig.getAddress());
					url.buildParam(URLConstant.REGISTRY_PORT, registryConfig.getPort());
				}
				
				Exporter<T> exporter = ProtocolLoader.get(protocol.getName()).export(interfaceType, ref, url);
	
				EXPORTER_LIST.add(exporter);
			}
		} catch (Exception e) {
			throw new RpcException("export service[" + interfaceType.getName() + "] fail.", e);
		}
	}
}
