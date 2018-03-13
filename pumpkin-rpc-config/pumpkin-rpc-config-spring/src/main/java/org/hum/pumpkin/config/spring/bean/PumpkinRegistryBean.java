package org.hum.pumpkin.config.spring.bean;

public class PumpkinRegistryBean {

	private String address;
	private String protocolName;
	private String host;
	private int port;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
		// TODO 这里用正则
		this.protocolName = address.split("://")[0];
		this.host = address.split("://")[1].split(":")[0];
		this.port = Integer.parseInt(address.split("://")[1].split(":")[1]);
	}

	public String getProtocolName() {
		return protocolName;
	}

	public void setProtocolName(String protocolName) {
		this.protocolName = protocolName;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
