package org.hum.pumpkin.config.spring.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegistryBean {

	private static final List<String> registries = new ArrayList<String>();
	private String address;
	private String protocolName;
	private String host;
	private int port;
	
	public static void addRegistry(String registryBeanId) {
		registries.add(registryBeanId);
	}
	
	public static List<String> getRegistries() {
		return (List<String>) Collections.unmodifiableList(registries);
	}

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
