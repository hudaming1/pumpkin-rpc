package org.hum.pumpkin.config.spring.parser;

import java.util.concurrent.atomic.AtomicInteger;

import org.hum.pumpkin.config.spring.bean.RegistryBean;
import org.hum.pumpkin.config.spring.common.Constant;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class PumpkinRegistryBeanParser implements BeanDefinitionParser {

	private static final AtomicInteger idGenerator = new AtomicInteger(0);
	
	public BeanDefinition parse(Element element, ParserContext parserContext) {

		// TODO check
		String id = StringUtils.isEmpty(element.getAttribute("id")) ? Constant.PROTOCOL_NAME + idGenerator.incrementAndGet() : element.getAttribute("id");
		String address = element.getAttribute("address");
		
		RootBeanDefinition beanDefinition = new RootBeanDefinition(RegistryBean.class);
		beanDefinition.getPropertyValues().addPropertyValue("address", address);
		parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
		RegistryBean.addRegistry(id);
		return beanDefinition;
	}
}
