package org.hum.pumpkin.config.spring.parser;

import java.util.concurrent.atomic.AtomicInteger;

import org.hum.pumpkin.config.spring.bean.PumpkinProtocolBean;
import org.hum.pumpkin.config.spring.common.Constant;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class PumpkinProtocolBeanParser implements BeanDefinitionParser {

	private static final AtomicInteger idGenerator = new AtomicInteger(0);
	
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		// TODO check
		
		String id = Constant.PROTOCOL_NAME + idGenerator.incrementAndGet();
		
		RootBeanDefinition beanDefinition = new RootBeanDefinition(PumpkinProtocolBean.class);
		beanDefinition.getPropertyValues().addPropertyValue("name", element.getAttribute("name"));
		beanDefinition.getPropertyValues().addPropertyValue("port", element.getAttribute("port"));
		parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
		PumpkinProtocolBean.addProtocol(id);
		return beanDefinition;
	}
}
