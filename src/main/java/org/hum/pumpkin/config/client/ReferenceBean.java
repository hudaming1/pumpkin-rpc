package org.hum.pumpkin.config.client;

public class ReferenceBean<T> extends ReferenceConfig {

	private Class<T> interfaceType;
	
	public ReferenceBean(Class<T> interfaceType) {
		this.interfaceType = interfaceType;
	}
	
	private String address;
	private int port;
	
	public ReferenceBean<T> buildUrl(String address, int port) {
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
