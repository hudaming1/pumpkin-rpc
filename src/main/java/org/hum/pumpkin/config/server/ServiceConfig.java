package org.hum.pumpkin.config.server;

import org.hum.pumpkin.config.ApplicationConfig;

public class ServiceConfig extends ApplicationConfig {

	private int port;

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
