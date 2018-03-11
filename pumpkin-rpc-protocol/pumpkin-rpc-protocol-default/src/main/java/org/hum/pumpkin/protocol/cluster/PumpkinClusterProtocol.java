package org.hum.pumpkin.protocol.cluster;

import java.net.UnknownHostException;

import org.hum.pumpkin.common.exception.PumpkinException;
import org.hum.pumpkin.common.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin.common.url.URL;
import org.hum.pumpkin.common.url.URLConstant;
import org.hum.pumpkin.protocol.Protocol;
import org.hum.pumpkin.protocol.exporter.DefaultExporter;
import org.hum.pumpkin.protocol.exporter.Exporter;
import org.hum.pumpkin.protocol.invoker.Invoker;
import org.hum.pumpkin.protocol.invoker.cluster.ClusterInvoker;
import org.hum.pumpkin.registry.Registry;
import org.hum.pumpkin.registry.RegistryConfig;
import org.hum.pumpkin.util.InetUtils;

public class PumpkinClusterProtocol implements Protocol {

	private final Registry registry = ServiceLoaderHolder.loadByCache(Registry.class);
	private RegistryConfig registryConfig;
	
	public PumpkinClusterProtocol(RegistryConfig registryConfig) {
		this.registryConfig = registryConfig;
		this.registry.connect(registryConfig.getAddress(), registryConfig.getPort());
	}

	@Override
	public <T> Exporter<T> export(Class<T> classType, T instances, URL url) {
		DefaultExporter<T> exporter = new DefaultExporter<T>(classType, instances, url);
		try {
			registry.registry(classType, InetUtils.getLocalAddress(), registryConfig.getPort());
		} catch (UnknownHostException e) {
			throw new PumpkinException("registry directory unknown host", e);
		}
		return exporter;
	}

	@Override
	public <T> Invoker<T> refer(Class<T> classType, URL url) {
		url.buildParam(URLConstant.IS_KEEP_ALIVE, true);
		url.buildParam(URLConstant.IS_SHARE_CONNECTION, true);
		ClusterInvoker<T> invoker = new ClusterInvoker<>(registry, registryConfig, classType, url);
		registry.connect(registryConfig.getAddress(), registryConfig.getPort());
		return invoker;
	}
}
