package org.hum.pumpkin.config.spring.parser;

import org.hum.pumpkin.config.spring.bean.PumpkinProtocolBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class PumpkinProtocolBeanParser implements BeanDefinitionParser {

	public BeanDefinition parse(Element element, ParserContext parserContext) {
		// TODO check
		String id = element.getAttribute("id");
		String name = element.getAttribute("name");
		String port = element.getAttribute("port");
		
		RootBeanDefinition beanDefinition = new RootBeanDefinition();
		beanDefinition.setBeanClass(PumpkinProtocolBean.class);
		beanDefinition.getPropertyValues().addPropertyValue("name", name);
		beanDefinition.getPropertyValues().addPropertyValue("port", port);
		parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
		return beanDefinition;
	}
}
