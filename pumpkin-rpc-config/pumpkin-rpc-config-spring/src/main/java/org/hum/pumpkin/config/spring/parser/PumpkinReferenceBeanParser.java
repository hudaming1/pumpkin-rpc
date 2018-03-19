package org.hum.pumpkin.config.spring.parser;

import java.util.List;

import org.hum.pumpkin.common.exception.ServiceException;
import org.hum.pumpkin.config.spring.bean.ReferenceBean;
import org.hum.pumpkin.config.spring.bean.RegistryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class PumpkinReferenceBeanParser implements BeanDefinitionParser {

	public BeanDefinition parse(Element element, ParserContext parserContext) {
		// TODO check params
		
		String interfaceType = element.getAttribute("interface");
		String id = StringUtils.isEmpty(element.getAttribute("id")) ? interfaceType : element.getAttribute("id");

		RootBeanDefinition beanDefinition = new RootBeanDefinition(ReferenceBean.class);
		beanDefinition.getPropertyValues().addPropertyValue("interfaceType", interfaceType);
		beanDefinition.getPropertyValues().addPropertyValue("protocol", element.getAttribute("protocol"));
		beanDefinition.getPropertyValues().addPropertyValue("registryConfig", getRegistry(id, element));
		beanDefinition.getPropertyValues().addPropertyValue("url", element.getAttribute("url"));
		parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
		return beanDefinition;
	}
	
	private RuntimeBeanReference getRegistry(String id, Element element) {
		// 1.先判断service是否指定了registry
		String registryConfigId = element.getAttribute("registryConfig");
		if (!StringUtils.isEmpty(registryConfigId)) {
			return new RuntimeBeanReference(registryConfigId);
		}
		// 2.如果service没有指定registry，则看看有没有全局registry可用
		List<String> registries = RegistryBean.getRegistries();
		if (registries == null || registries.isEmpty()) {
			return null;
		} else if (registries.size() > 1) {
			throw new ServiceException("parse reference exception, [" + id + "] unclear registry, found registries:" + registries, null);
		}
		return new RuntimeBeanReference(registries.get(0));
	}
}
