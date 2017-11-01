package org.hum.pumpkin.config;

public class ServerConfig {

	private Class<?> interfaceType;
	private Object instances;
	
	// TODO timeout
	
	// TODO retry times
	
	// TODO group
	
	// TODO version

	public ServerConfig(Class<?> interfaceType, Object instances) {
		this.interfaceType = interfaceType;
		this.instances = instances;
	}

	public Class<?> getInterfaceType() {
		return interfaceType;
	}

	public Object getInstances() {
		return this.instances;
	}
}
