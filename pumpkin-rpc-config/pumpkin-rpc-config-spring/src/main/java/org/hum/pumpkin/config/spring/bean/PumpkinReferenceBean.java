package org.hum.pumpkin.config.spring.bean;

import org.hum.pumpkin.config.ReferenceConfig;
import org.hum.pumpkin.registry.RegistryConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.StringUtils;

public class PumpkinReferenceBean<T> implements FactoryBean<T>{

	private PumpkinRegistryBean registryConfig;
	private String protocol;
	private Class<T> interfaceType;
	private String url;

	public PumpkinRegistryBean getRegistryConfig() {
		return registryConfig;
	}

	public void setRegistryConfig(PumpkinRegistryBean registryConfig) {
		this.registryConfig = registryConfig;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public Class<T> getInterfaceType() {
		return interfaceType;
	}

	public void setInterfaceType(Class<T> interfaceType) {
		this.interfaceType = interfaceType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public T getObject() throws Exception {
		ReferenceConfig<T> referenceBean = new ReferenceConfig<T>(interfaceType);
		if (!StringUtils.isEmpty(url)) {
			// TODO 稍后用正则修改
			String host = url.split(":")[0];
			String port = url.split(":")[1];
			referenceBean.setUrl(host, Integer.parseInt(port));
		}
		referenceBean.setProtocol(protocol);
		referenceBean.setRegistryConfig(new RegistryConfig(registryConfig.getProtocolName(), registryConfig.getHost(), registryConfig.getPort()));
		return referenceBean.get();
	}
	
	public Class<T> getObjectType() {
		return interfaceType;
	}

	public boolean isSingleton() {
		return false;
	}

	@Override
	public String toString() {
		return "PumpkinReferenceBean [registryConfig=" + registryConfig + ", protocol=" + protocol + ", interfaceType="
				+ interfaceType + ", url=" + url + "]";
	}
}
