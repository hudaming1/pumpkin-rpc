package org.hum.pumpkin.config.spring;

import org.hum.pumpkin.config.spring.parser.*;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class PumpkinNameSpaceHandler extends NamespaceHandlerSupport {

	public void init() {
		// 1.Service中自动加载protocol；2.支持多Protocol
		registerBeanDefinitionParser("protocol", new PumpkinProtocolBeanParser());
		registerBeanDefinitionParser("registry", new PumpkinRegistryBeanParser());
		registerBeanDefinitionParser("service", new PumpkinServiceBeanParser());
		registerBeanDefinitionParser("reference", new PumpkinReferenceBeanParser());
	}
}
