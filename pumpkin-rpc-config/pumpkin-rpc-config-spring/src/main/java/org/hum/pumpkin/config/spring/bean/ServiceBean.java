package org.hum.pumpkin.config.spring.bean;

import java.util.ArrayList;
import java.util.List;

import org.hum.pumpkin.config.ProtocolConfig;
import org.hum.pumpkin.config.ServiceConfig;
import org.hum.pumpkin.registry.RegistryConfig;
import org.hum.pumpkin.util.InetUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class ServiceBean implements ApplicationListener<ContextRefreshedEvent> {

	private RegistryBean registryConfig;
	private ArrayList<String> protocols;
	private String interfaceType;
	private Object ref;
	
	public ServiceBean() {}

	public RegistryBean getRegistryConfig() {
		return registryConfig;
	}

	public void setRegistryConfig(RegistryBean registryConfig) {
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

	public ArrayList<String> getProtocols() {
		return protocols;
	}

	public void setProtocols(ArrayList<String> protocols) {
		this.protocols = protocols;
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		try {
			List<ProtocolConfig> protocolList = new ArrayList<>();
			for (String procotolBeanId : protocols) {
				ProtocolBean protocolBean = arg0.getApplicationContext().getBean(procotolBeanId, ProtocolBean.class);
				ProtocolConfig config = new ProtocolConfig(protocolBean.getName(), protocolBean.getPort());
				protocolList.add(config);
			}
			ServiceConfig serviceConfig = new ServiceConfig();
			serviceConfig.setRegistryConfig(new RegistryConfig(registryConfig.getProtocolName(),registryConfig.getHost(), registryConfig.getPort()));
			serviceConfig.setProtocols(protocolList);
			serviceConfig.setHost(InetUtils.getLocalAddress());
			serviceConfig.setInterfaceType(Class.forName(interfaceType));
			serviceConfig.setRef(ref);
			serviceConfig.export();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "PumpkinServiceBean [registryConfig=" + registryConfig + ", protocols=" + protocols + ", interfaceType="
				+ interfaceType + ", ref=" + ref + "]";
	}
}
