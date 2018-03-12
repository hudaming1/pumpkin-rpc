package org.hum.pumpkin.common.url;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class URL {

	private String protocol;
	private String host;
	private int port;
	private String path;
	private Map<String, Object> params = new HashMap<>();

	public URL(String protocol, String host, int port, String path) {
		this.protocol = protocol;
		this.host = host;
		this.port = port;
		this.path = path;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public void setPort(int port) {
		this.port = port;
	}

	public Object getParam(String key) {
		return params.get(key);
	}

	public URL buildParam(String key, Object value) {
		params.put(key, value);
		return this;
	}
	
	public Integer getInteger(String key) {
		return (Integer) params.get(key);
	}
	
	public Boolean getBoolean(String key) {
		return (Boolean) params.get(key);
	}

	public String getProtocol() {
		return protocol;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getPath() {
		return path;
	}
	
	public String getParamString() {
		StringBuilder sbuilder = new StringBuilder();
		
		for (Entry<String, Object> entry : params.entrySet()) {
			sbuilder.append("&").append(entry.getKey()).append("=").append(entry.getValue());
		}
		
		return sbuilder.toString().replaceFirst("&", "?");
	}
	
	@Override
	public String toString() {
		return protocol + "://" + host + ":" + port + getParamString();
	}
}