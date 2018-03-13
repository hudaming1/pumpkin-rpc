package org.hum.pumpkin.config.spring.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PumpkinProtocolBean {

	private static final List<String> protocols = new ArrayList<String>();
	private String name;
	private int port;
	
	public static void addProtocol(String protocolBeanId) {
		protocols.add(protocolBeanId);
	}
	
	public static List<String> getProtocls() {
		return Collections.unmodifiableList(protocols);
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
}
