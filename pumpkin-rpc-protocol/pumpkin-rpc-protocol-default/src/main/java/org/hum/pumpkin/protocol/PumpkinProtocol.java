package org.hum.pumpkin.protocol;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.hum.pumpkin.common.exception.PumpkinException;
import org.hum.pumpkin.common.serviceloader.ExtensionLoader;
import org.hum.pumpkin.common.url.URL;
import org.hum.pumpkin.common.url.URLConstant;
import org.hum.pumpkin.logger.Logger;
import org.hum.pumpkin.logger.LoggerFactory;
import org.hum.pumpkin.protocol.cluster.invoker.ClusterInvoker;
import org.hum.pumpkin.protocol.exporter.DefaultExporter;
import org.hum.pumpkin.protocol.exporter.Exporter;
import org.hum.pumpkin.protocol.invoker.DirectInvoker;
import org.hum.pumpkin.protocol.invoker.Invoker;
import org.hum.pumpkin.registry.Registry;
import org.hum.pumpkin.registry.RegistryConfig;
import org.hum.pumpkin.util.InetUtils;
import org.hum.pumpkin.util.StringUtils;

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

	private static final Logger logger = LoggerFactory.getLogger(PumpkinProtocol.class);
	private static final List<Exporter<?>> EXPORTER_LIST = new ArrayList<>();
	private volatile Registry registry = null;

	// TODO 后期创建Exporter时，需要传Invoker（Dubbo中采用InJvmInvoker，在扩展Service层Filter时可以形成InvokerChain）
	@Override
	public <T> Exporter<T> export(Class<T> classType, T instances, URL url) {
		
		// TODO 进行必要的url.check

		// pumpkin协议规定使用netty-transporter
		if (url.getParam(URLConstant.TRANSPORT_KEY) == null)
			url.buildParam(URLConstant.TRANSPORT_KEY, "netty");
		// pumpkin协议规定使用kryo
		if (url.getParam(URLConstant.SERIALIZATION) == null)
			url.buildParam(URLConstant.SERIALIZATION, "kryo");
		
		// 如果服务需要对外暴露注册中心协议，则这里需要去连接注册中心注册服务
		exportRegistry(classType, url);
		
		DefaultExporter<T> exporter = new DefaultExporter<T>(classType, instances, url);
		EXPORTER_LIST.add(exporter);
		
		logger.info("export service[" + classType.getName() + "] successfully");
		
		return exporter;
	}

	private <T> void exportRegistry(Class<T> classType, URL url) {
		String registryString = url.getString(URLConstant.REGISTRY);
		if (StringUtils.isNotEmpty(registryString)) {
			try {
				for (String registryStr : registryString.split(";")) {
					RegistryConfig registryConfig = new RegistryConfig(registryStr);
					registry = ExtensionLoader.getExtensionLoader(Registry.class).get(registryConfig.getName());
					registry.connect(registryConfig.getAddress(), registryConfig.getPort());
					registry.registry(classType, InetUtils.getLocalAddress(), url.getPort());
				}
			} catch (UnknownHostException e) {
				throw new PumpkinException("registry exception, registryString=" + registryString, e);
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
		String registryString = url.getString(URLConstant.REGISTRY);
		if (StringUtils.isNotEmpty(registryString)) {
			RegistryConfig registryConfig = new RegistryConfig(registryString);
			registry = ExtensionLoader.getExtensionLoader(Registry.class).get(registryConfig.getName());
			registry.connect(registryConfig.getAddress(), registryConfig.getPort());
			return new ClusterInvoker<>(registry, classType, url);
		} 
		return new DirectInvoker<>(classType, url);
	}
}
