package org.hum.pumpkin.config.spring.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegistryBean {

	private static final List<String> registries = new ArrayList<String>();
	private String address;
	
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
	}
}
