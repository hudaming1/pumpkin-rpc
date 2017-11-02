package org.hum.pumpkin.config;

import org.hum.pumpkin.config.plugin.RegistryConfig;
import org.hum.pumpkin.config.plugin.TranportConfig;

/**
 * 全局配置 TODO 看看有没有可能将GlobalConfig与 ServerConfig/ClientConfig 合并
 */
public class ApplicationConfig {

	public ApplicationConfig() {
	}

	// how to transport
	private TranportConfig transportConfig;
	
	// how to serialize

	public TranportConfig getTransportConfig() {
		return transportConfig;
	}

	public void setTransportConfig(TranportConfig transportConfig) {
		this.transportConfig = transportConfig;
	}

	// how to registry
	private RegistryConfig registryConfig;

	// how to proxy

	// executor

	public RegistryConfig getRegistryConfig() {
		return registryConfig;
	}

	public void setRegistryConfig(RegistryConfig registryConfig) {
		this.registryConfig = registryConfig;
	}

	public void validate() {

	}
}
