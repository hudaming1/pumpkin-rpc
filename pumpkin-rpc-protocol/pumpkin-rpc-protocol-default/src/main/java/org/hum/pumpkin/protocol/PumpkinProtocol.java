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
 * 南瓜协议：
 * <pre>
 *  1.基于TCP层传输通信 
 *  2.采用Netty传输 
 *  3.保持单一长连接 
 *  4.Kryo序列化方式
 * </pre>
 */
public class PumpkinProtocol implements Protocol {

	private final Registry registry = ServiceLoaderHolder.loadByCache(Registry.class);

	// TODO 后期创建Exporter时，需要传Invoker（Dubbo中采用InJvmInvoker，在扩展Service层Filter时可以形成InvokerChain）
	@Override
	public <T> Exporter<T> export(Class<T> classType, T instances, URL url) {
		
		// TODO 进行必要的url.check
		
		// 如果服务需要对外暴露注册中心协议，则这里需要去连接注册中心注册服务
		exportRegistry(classType, url);
		
		return new DefaultExporter<T>(classType, instances, url);
	}

	private <T> void exportRegistry(Class<T> classType, URL url) {
		if (url.getProtocol() != null && url.getProtocol().equals(Constant.PROTOCOL_REGISTRY)) {
			try {
				RegistryConfig registryConfig = (RegistryConfig) url.getParam(URLConstant.REGISTRY_CONFIG);
				this.registry.connect(registryConfig.getAddress(), registryConfig.getPort());
				this.registry.registry(classType, InetUtils.getLocalAddress(), url.getPort());
			} catch (UnknownHostException e) {
				throw new PumpkinException("registry exception", e);
			}
		}
	}

	@Override
	public <T> Invoker<T> refer(Class<T> classType, URL url) {
		url.buildParam(URLConstant.IS_KEEP_ALIVE, true);
		url.buildParam(URLConstant.IS_SHARE_CONNECTION, true);
		if (url.getProtocol().equals(Constant.PROTOCOL_REGISTRY)) {
			RegistryConfig registryConfig = (RegistryConfig) url.getParam(URLConstant.REGISTRY_CONFIG);
			registry.connect(registryConfig.getAddress(), registryConfig.getPort());
			return new ClusterInvoker<>(registry, registryConfig, classType, url);
		} 
		return new DirectInvoker<>(classType, url);
	}
}
