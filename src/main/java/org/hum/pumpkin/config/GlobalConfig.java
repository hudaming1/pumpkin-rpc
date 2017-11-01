package org.hum.pumpkin.config;

/**
 * 全局配置
 * TODO 看看有没有可能将GlobalConfig与 ServerConfig/ClientConfig 合并
 */
public class GlobalConfig {
	
	private static volatile GlobalConfig instances ;
	private static Object singleLock = new Object();
	
	private GlobalConfig() {
	}
	
	public static GlobalConfig getInstances() {
		if (instances != null) {
			return instances;
		}
		synchronized (singleLock) {
			if (instances != null) {
				return instances;
			}
			instances = new GlobalConfig();
		}
		return instances;
	}

	// how to transport
	
	// how to serialize
	
	// how to registry
	private RegistryConfig registryConfig;
	
	public GlobalConfig buildRegistry(RegistryConfig registryConfig) {
		this.registryConfig = registryConfig;
		return this;
	}

	public RegistryConfig getRegistryConfig() {
		return registryConfig;
	}
	
	// how to proxy
	
	// executor
	
	public void validate() {
		
	}
}
