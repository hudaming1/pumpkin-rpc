package org.hum.pumpkin.config;

public class ProtocolConfig {

	private String name;
	private int port;

	public ProtocolConfig() {
	}

	public ProtocolConfig(String name, int port) {
		super();
		this.name = name;
		this.port = port;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "ProtocolConfig [name=" + name + ", port=" + port + "]";
	}
}
