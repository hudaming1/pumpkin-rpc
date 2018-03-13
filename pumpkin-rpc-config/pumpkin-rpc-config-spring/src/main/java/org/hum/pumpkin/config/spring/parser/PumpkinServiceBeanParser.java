package org.hum.pumpkin.config.spring.parser;

import java.util.ArrayList;

import org.hum.pumpkin.config.spring.bean.PumpkinProtocolBean;
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
		// TODO check
		String interfaceType = element.getAttribute("interface");
		String id = element.getAttribute("id") == null ? interfaceType : element.getAttribute("id");
		String refId = element.getAttribute("ref");
		String registryConfigId = element.getAttribute("registryConfig");
		
		RootBeanDefinition beanDefinition = new RootBeanDefinition();
		beanDefinition.setBeanClass(PumpkinServiceBean.class);
		beanDefinition.getPropertyValues().addPropertyValue("interfaceType", interfaceType);
		beanDefinition.getPropertyValues().addPropertyValue("ref", new RuntimeBeanReference(refId));
		ArrayList<String> protocols = new ArrayList<String>();
		for (String protocolId : PumpkinProtocolBean.getProtocls()) {
			protocols.add(protocolId);
		}
		beanDefinition.getPropertyValues().addPropertyValue("protocols", protocols);
		if (!StringUtils.isEmpty(registryConfigId)) {
			beanDefinition.getPropertyValues().addPropertyValue("registryConfig", new RuntimeBeanReference(registryConfigId));
		}
		parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
		return beanDefinition;
	}
}
