package org.hum.pumpkin.config.spring.bean;

import java.util.ArrayList;

import org.hum.pumpkin.config.ServiceConfig;
import org.hum.pumpkin.registry.RegistryConfig;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class PumpkinServiceBean implements ApplicationListener<ContextRefreshedEvent> {

	private PumpkinRegistryBean registryConfig;
	private ArrayList<String> protocols;
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

	public ArrayList<String> getProtocols() {
		return protocols;
	}

	public void setProtocols(ArrayList<String> protocols) {
		this.protocols = protocols;
	}

	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		try {
			for (String procotolBeanId : protocols) {
				System.out.println(procotolBeanId);
				PumpkinProtocolBean protocolBean = arg0.getApplicationContext().getBean(procotolBeanId, PumpkinProtocolBean.class);
				ServiceConfig serviceConfig = new ServiceConfig();
				serviceConfig.setProtocol(protocolBean.getName());
				serviceConfig.setRegistryConfig(new RegistryConfig(registryConfig.getProtocolName(), registryConfig.getHost(), registryConfig.getPort()));
				serviceConfig.setPort(protocolBean.getPort());
				serviceConfig.setInterfaceType(Class.forName(interfaceType));
				serviceConfig.setRef(ref);
				serviceConfig.export();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "PumpkinServiceBean [registryConfig=" + registryConfig + ", protocols=" + protocols + ", interfaceType="
				+ interfaceType + ", ref=" + ref + ", name=" + name + "]";
	}
}
