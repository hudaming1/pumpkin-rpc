package org.hum.pumpkin.config.spring.bean;

import org.hum.pumpkin.config.ReferenceConfig;
import org.hum.pumpkin.protocol.Protocol;
import org.hum.pumpkin.registry.RegistryConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

public class PumpkinReferenceBean<T> implements FactoryBean<T>, ApplicationContextAware {

	private transient ApplicationContext applicationContext;
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
	
//	private String getProtocol() {
//		// String[] beanNamesForType = applicationContext.getBeanNamesForType(Protocol.class);
//		return "";
//	}

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

	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.applicationContext = arg0;
	}
}
