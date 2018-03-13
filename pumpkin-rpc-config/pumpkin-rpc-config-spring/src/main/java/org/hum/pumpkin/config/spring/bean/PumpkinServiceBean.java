package org.hum.pumpkin.config.spring.bean;

import org.hum.pumpkin.config.ServiceConfig;
import org.hum.pumpkin.registry.RegistryConfig;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class PumpkinServiceBean implements ApplicationListener<ContextRefreshedEvent> {

	private PumpkinRegistryBean registryConfig;
	private PumpkinProtocolBean protocol;
	private String interfaceType;
	private Object ref;
	private String name;
	
	public PumpkinServiceBean() {}

	public PumpkinRegistryBean getRegistryConfig() {
		return registryConfig;
	}

	public void setRegistryConfig(PumpkinRegistryBean registryConfig) {
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

	public PumpkinProtocolBean getProtocol() {
		return protocol;
	}

	public void setProtocol(PumpkinProtocolBean protocol) {
		this.protocol = protocol;
	}

	@Override
	public String toString() {
		return "PumpkinServiceBean [registryConfig=" + registryConfig + ", interfaceType=" + interfaceType + ", ref="
				+ ref + "]";
	}

	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		try {
			ServiceConfig serviceConfig = new ServiceConfig();
			serviceConfig.setProtocol(protocol.getName());
			serviceConfig.setRegistryConfig(new RegistryConfig(registryConfig.getProtocolName(), registryConfig.getHost(), registryConfig.getPort()));
			serviceConfig.setPort(protocol.getPort());
			serviceConfig.setInterfaceType(Class.forName(interfaceType));
			serviceConfig.setRef(ref);
			serviceConfig.export();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
