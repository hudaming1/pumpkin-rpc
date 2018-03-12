package org.hum.pumpkin.config.spring.bean;

import org.hum.pumpkin.registry.RegistryConfig;

public class PumpkinServiceBean {

	private RegistryConfig registryConfig;
	private String interfaceType;
	private Object ref;
	private String name;
	
	public PumpkinServiceBean() {}

	public PumpkinServiceBean(RegistryConfig registryConfig, String interfaceType, Object ref) {
		this.registryConfig = registryConfig;
		this.interfaceType = interfaceType;
		this.ref = ref;
	}

	public RegistryConfig getRegistryConfig() {
		return registryConfig;
	}

	public void setRegistryConfig(RegistryConfig registryConfig) {
		this.registryConfig = registryConfig;
	}

	public String getInterfaceType() {
		return interfaceType;
	}

	public void setInterfaceType(String interfaceType) {
		this.interfaceType = interfaceType;
	}

	public Object getRef() {
		return ref;
	}

	public void setRef(Object ref) {
		this.ref = ref;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "PumpkinServiceBean [registryConfig=" + registryConfig + ", interfaceType=" + interfaceType + ", ref="
				+ ref + "]";
	}
}
