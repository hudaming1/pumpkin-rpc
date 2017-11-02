package org.hum.pumpkin.config;

import org.hum.pumpkin.config.plugin.RegistryConfig;

/**
 * 全局配置 TODO 看看有没有可能将GlobalConfig与 ServerConfig/ClientConfig 合并
 */
public class ApplicationConfig {

	public ApplicationConfig() {
	}

	// how to transport
	
	// how to serialize

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
