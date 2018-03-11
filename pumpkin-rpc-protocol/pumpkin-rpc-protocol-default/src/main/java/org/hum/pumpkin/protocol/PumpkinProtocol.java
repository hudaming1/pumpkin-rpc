package org.hum.pumpkin.protocol;

import java.net.UnknownHostException;

import org.hum.pumpkin.common.Constant;
import org.hum.pumpkin.common.exception.PumpkinException;
import org.hum.pumpkin.common.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin.common.url.URL;
import org.hum.pumpkin.common.url.URLConstant;
import org.hum.pumpkin.protocol.exporter.DefaultExporter;
import org.hum.pumpkin.protocol.exporter.Exporter;
import org.hum.pumpkin.protocol.invoker.Invoker;
import org.hum.pumpkin.protocol.invoker.cluster.ClusterInvoker;
import org.hum.pumpkin.protocol.invoker.direct.DirectInvoker;
import org.hum.pumpkin.registry.Registry;
import org.hum.pumpkin.registry.RegistryConfig;
import org.hum.pumpkin.util.InetUtils;

/**
 * 南瓜协议： 1.基于TCP层传输通信 2.采用Netty传输 3.保持单一长连接 4.Kryo序列化方式
 */
public class PumpkinProtocol implements Protocol {

	private final Registry registry = ServiceLoaderHolder.loadByCache(Registry.class);

	@Override
	public <T> Exporter<T> export(Class<T> classType, T instances, URL url) {
		if (url.getProtocol() != null && url.getProtocol().equals(Constant.PROTOCOL_REGISTRY)) {
			RegistryConfig registryConfig = (RegistryConfig) url.getParam("registryConfig");
			this.registry.connect(registryConfig.getAddress(), registryConfig.getPort());
			try {
				this.registry.registry(classType, InetUtils.getLocalAddress(), url.getPort());
			} catch (UnknownHostException e) {
				throw new PumpkinException("registry exception", e);
			}
		}
		return new DefaultExporter<T>(classType, instances, url);
	}

	@Override
	public <T> Invoker<T> refer(Class<T> classType, URL url) {
		url.buildParam(URLConstant.IS_KEEP_ALIVE, true);
		url.buildParam(URLConstant.IS_SHARE_CONNECTION, true);
		if (url.getProtocol().equals(Constant.PROTOCOL_REGISTRY)) {
			RegistryConfig registryConfig = (RegistryConfig) url.getParam("registryConfig");
			registry.connect(registryConfig.getAddress(), registryConfig.getPort());
			return new ClusterInvoker<>(registry, registryConfig, classType, url);
		} 
		return new DirectInvoker<>(classType, url);
	}
}
