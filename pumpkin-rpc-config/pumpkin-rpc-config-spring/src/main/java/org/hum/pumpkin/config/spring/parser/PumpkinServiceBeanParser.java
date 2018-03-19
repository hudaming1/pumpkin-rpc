package org.hum.pumpkin.config.spring.parser;

import java.util.ArrayList;

import org.hum.pumpkin.common.exception.ServiceException;
import org.hum.pumpkin.config.spring.bean.PumpkinProtocolBean;
import org.hum.pumpkin.config.spring.bean.PumpkinRegistryBean;
import org.hum.pumpkin.config.spring.bean.PumpkinServiceBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class PumpkinServiceBeanParser implements BeanDefinitionParser {

	public BeanDefinition parse(Element element, ParserContext parserContext) {

		// TODO check params
		
		String interfaceType = element.getAttribute("interface");
		String id = StringUtils.isEmpty(element.getAttribute("id")) ? interfaceType : element.getAttribute("id");

		RootBeanDefinition beanDefinition = new RootBeanDefinition(PumpkinServiceBean.class);
		beanDefinition.getPropertyValues().addPropertyValue("interfaceType", interfaceType);
		beanDefinition.getPropertyValues().addPropertyValue("ref", new RuntimeBeanReference(element.getAttribute("ref")));
		beanDefinition.getPropertyValues().addPropertyValue("protocols", getProtocols(element));
		beanDefinition.getPropertyValues().addPropertyValue("registryConfig", getRegistry(id, element));
		parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
		return beanDefinition;
	}

	private ArrayList<String> getProtocols(Element element) {
		// 1.先判断用户为service指定了协议
		ArrayList<String> protocols = new ArrayList<String>();
		String protocolString = element.getAttribute("protocol");
		if (!StringUtils.isEmpty(protocolString)) {
			for (String protocol : protocolString.split(",")) {
				if (!StringUtils.isEmpty(protocol)) {
					protocols.add(protocol);
				}
			}
			return protocols;
		}
		// 2.如果用户没有指定协议，则使用默认协议
		for (String protocolId : PumpkinProtocolBean.getProtocls()) {
			protocols.add(protocolId);
		}
		return protocols;
	}
	
	private RuntimeBeanReference getRegistry(String id, Element element) {
		// 1.先判断service是否指定了registry
		String registryConfigId = element.getAttribute("registryConfig");
		if (!StringUtils.isEmpty(registryConfigId)) {
			return new RuntimeBeanReference(registryConfigId);
		}
		// 2.如果service没有指定registry，则看看有没有全局registry可用
		if (PumpkinRegistryBean.getRegistries() == null) {
			return null;
		} else if (PumpkinRegistryBean.getRegistries().size() > 1) {
			throw new ServiceException("parse service exception, [" + id + "] unclear registry, found registries:" + PumpkinRegistryBean.getRegistries(), null);
		}
		return new RuntimeBeanReference(PumpkinRegistryBean.getRegistries().get(0));
	}
}
