package org.hum.pumpkin.protocol;

import java.net.UnknownHostException;

import org.hum.pumpkin.common.Constant;
import org.hum.pumpkin.common.exception.PumpkinException;
import org.hum.pumpkin.common.serviceloader.ExtensionLoader;
import org.hum.pumpkin.common.url.URL;
import org.hum.pumpkin.common.url.URLConstant;
import org.hum.pumpkin.protocol.cluster.invoker.ClusterInvoker;
import org.hum.pumpkin.protocol.exporter.DefaultExporter;
import org.hum.pumpkin.protocol.exporter.Exporter;
import org.hum.pumpkin.protocol.invoker.Invoker;
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

	private volatile Registry registry = null;

	// TODO 后期创建Exporter时，需要传Invoker（Dubbo中采用InJvmInvoker，在扩展Service层Filter时可以形成InvokerChain）
	@Override
	public <T> Exporter<T> export(Class<T> classType, T instances, URL url) {
		
		// TODO 进行必要的url.check

		// pumpkin协议规定使用netty-transporter
		url.buildParam(URLConstant.TRANSPORT_KEY, "netty");
		// pumpkin协议规定使用kryo
		url.buildParam(URLConstant.SERIALIZATION, "kryo");
		
		// 如果服务需要对外暴露注册中心协议，则这里需要去连接注册中心注册服务
		exportRegistry(classType, url);
		
		return new DefaultExporter<T>(classType, instances, url);
	}

	private <T> void exportRegistry(Class<T> classType, URL url) {
		if (url.getParam(URLConstant.REGISTRY_CONFIG) != null) {
			try {
				// TODO URL怎么设计的？怎么还能存对象了？必须改
				RegistryConfig registryConfig = (RegistryConfig) url.getParam(URLConstant.REGISTRY_CONFIG);
				this.registry = ExtensionLoader.getExtensionLoader(Registry.class).get(registryConfig.getName());
				// TODO 为以后多注册中心做准备（但需要ServiceLoader支持）
				// for (RegistryConfig registryConfig : registryConfigs) {
				// Registry registry = ServiceLoaderHolder.getExtensionByName(registryConfig.getName());
				this.registry.connect(registryConfig.getAddress(), registryConfig.getPort());
				this.registry.registry(classType, InetUtils.getLocalAddress(), url.getPort());
				// }
			} catch (UnknownHostException e) {
				throw new PumpkinException("registry exception", e);
			}
		}
	}

	@Override
	public <T> Invoker<T> refer(Class<T> classType, URL url) {
		url.buildParam(URLConstant.IS_KEEP_ALIVE, true);
		url.buildParam(URLConstant.IS_SHARE_CONNECTION, true);
		// pumpkin协议规定使用netty-transporter
		url.buildParam(URLConstant.TRANSPORT_KEY, "netty");
		// pumpkin协议规定使用kryo
		url.buildParam(URLConstant.SERIALIZATION, "kryo");
		if (url.getProtocol().equals(Constant.PROTOCOL_REGISTRY)) {
			// TODO URL不能存对象，必须改
			RegistryConfig registryConfig = (RegistryConfig) url.getParam(URLConstant.REGISTRY_CONFIG);
			this.registry = ExtensionLoader.getExtensionLoader(Registry.class).get(registryConfig.getName());
			registry.connect(registryConfig.getAddress(), registryConfig.getPort());
			return new ClusterInvoker<>(registry, registryConfig, classType, url);
		} 
		return new DirectInvoker<>(classType, url);
	}
}
