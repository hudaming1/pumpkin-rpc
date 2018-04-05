package org.hum.pumpkin.registry;

public class RegistryConfig {

	private String name;
	private String address;
	private int port;

	public RegistryConfig(String registryString) {
		// TODO 改用正则
		name = registryString.split("://")[0];
		address = registryString.split("://")[1].split(":")[0];
		port = Integer.parseInt(registryString.split("://")[1].split(":")[1]);
	}

	public RegistryConfig(String name, String address, int port) {
		super();
		this.name = name;
		this.address = address;
		this.port = port;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public int getPort() {
		return port;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RegistryConfig [name=").append(name).append(", address=").append(address).append(", port=")
				.append(port).append("]");
		return builder.toString();
	}
}
