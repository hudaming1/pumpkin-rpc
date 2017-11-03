package org.hum.pumpkin.protocol;

import java.util.HashMap;
import java.util.Map;

public class URL {

	private String protocol;
	private String address;
	private int port;
	private String path;
	private Map<String, String> params = new HashMap<>();

	public URL(String protocol, String address, int port, String path) {
		this.protocol = protocol;
		this.address = address;
		this.port = port;
		this.path = path;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public URL buildParam(String key, String value) {
		params.put(key, value);
		return this;
	}

	public String getProtocol() {
		return protocol;
	}

	public String getAddress() {
		return address;
	}

	public int getPort() {
		return port;
	}

	public String getPath() {
		return path;
	}
}
