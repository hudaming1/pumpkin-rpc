package org.hum.pumpkin.config;

public class ClientConfig {

	private Class<?> interfaceType;
	
	public ClientConfig(Class<?> interfaceType) {
		this.interfaceType = interfaceType;
	}
	
	// TODO timeout

	// TODO url
	
	// TODO retry times
	
	// TODO group
	
	// TODO version
	
	public Class<?> getInterfaceType() {
		return interfaceType;
	}
}
