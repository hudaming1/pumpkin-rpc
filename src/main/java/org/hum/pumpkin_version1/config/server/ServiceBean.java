package org.hum.pumpkin_version1.config.server;

public class ServiceBean extends ServiceConfig {

	private Class<?> interfaceType;
	private Object instances;
	
	// TODO timeout
	
	// TODO retry times
	
	// TODO group
	
	// TODO version

	public ServiceBean(Class<?> interfaceType, Object instances) {
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
