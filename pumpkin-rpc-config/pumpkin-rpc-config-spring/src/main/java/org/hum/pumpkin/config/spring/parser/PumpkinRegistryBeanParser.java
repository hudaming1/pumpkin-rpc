package org.hum.pumpkin.config.spring.parser;

import org.hum.pumpkin.config.spring.bean.PumpkinRegistryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class PumpkinRegistryBeanParser implements BeanDefinitionParser {

	public BeanDefinition parse(Element element, ParserContext parserContext) {

		// TODO check
		String id = element.getAttribute("id") == null ? "pumpkin-registry" : element.getAttribute("id");
		String address = element.getAttribute("address");
		RootBeanDefinition beanDefinition = new RootBeanDefinition();
		beanDefinition.setBeanClass(PumpkinRegistryBean.class);
		beanDefinition.getPropertyValues().addPropertyValue("address", address);
		
		// TODO create id strangy
		parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
		return beanDefinition;
	}
}
