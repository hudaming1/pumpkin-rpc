package org.hum.pumpkin.config;

public class ReferenceConfig<T> {

	private Class<T> interfaceType;
	
	public ReferenceConfig(Class<T> interfaceType) {
		this.interfaceType = interfaceType;
	}
	
	private String address;
	private int port;
	
	public ReferenceConfig<T> buildUrl(String address, int port) {
		this.address = address;
		this.port = port;
		return this;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public int getPort() {
		return this.port;
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
