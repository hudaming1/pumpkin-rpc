package org.hum.pumpkin.config.spring;

import org.hum.pumpkin.config.spring.parser.PumpkinProtocolBeanParser;
import org.hum.pumpkin.config.spring.parser.PumpkinRegistryBeanParser;
import org.hum.pumpkin.config.spring.parser.PumpkinServiceBeanParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class PumpkinNameSpaceHandler extends NamespaceHandlerSupport {

	public void init() {
		// 1.Service中自动加载protocol；2.支持多Protocol
		registerBeanDefinitionParser("service", new PumpkinServiceBeanParser());
		registerBeanDefinitionParser("protocol", new PumpkinProtocolBeanParser());
		registerBeanDefinitionParser("registry", new PumpkinRegistryBeanParser());
	}
}
