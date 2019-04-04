package org.hum.pumpkin.config.spring.bean;

import org.hum.pumpkin.config.ReferenceConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.StringUtils;

public class ReferenceBean<T> implements FactoryBean<T>{

	private RegistryBean registryConfig;
	private String protocol;
	private Class<T> interfaceType;
	private String url;

	public RegistryBean getRegistryConfig() {
		return registryConfig;
	}

	public void setRegistryConfig(RegistryBean registryConfig) {
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
		referenceBean.setProtocol(protocol);
		// 1.优先使用内部url
		if (!StringUtils.isEmpty(url)) {
			// TODO check url
			// TODO 稍后用正则修改
			String host = url.split(":")[0];
			String port = url.split(":")[1];
			referenceBean.setUrl(host, Integer.parseInt(port));
		} 
		// 2.如果内部没有定义url，则判断有无注册中心
		else if (registryConfig != null) {
			referenceBean.setRegistry(registryConfig.getAddress());
		} 
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
