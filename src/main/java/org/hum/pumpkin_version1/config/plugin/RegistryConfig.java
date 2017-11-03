package org.hum.pumpkin_version1.config.plugin;

import org.hum.pumpkin_version1.registry.Registry;
import org.hum.pumpkin_version1.serviceloader.ServiceLoadable;

public class RegistryConfig implements ServiceLoadable<Registry> {

	private String name;
	private String address;
	private int port;

	public RegistryConfig(String name, String address, int port) {
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
	public Class<Registry> getType() {
		return Registry.class;
	}
}
