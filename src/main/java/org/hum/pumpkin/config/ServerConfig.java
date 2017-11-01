package org.hum.pumpkin.config;

public class ServerConfig {

	private Object instances;
	
	public ServerConfig(Object instances) {
		this.instances = instances;
	}
	
	public Object getInstances() {
		return this.instances;
	}
}
