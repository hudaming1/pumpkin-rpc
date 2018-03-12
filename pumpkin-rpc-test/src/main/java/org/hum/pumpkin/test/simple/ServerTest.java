package org.hum.pumpkin.test.simple;

import org.hum.pumpkin.config.ServiceConfig;
import org.hum.pumpkin.registry.RegistryConfig;
import org.hum.pumpkin.test._service.HelloServiceImpl;
import org.hum.pumpkin.test._service.IHelloService;

public class ServerTest {

	public static void main(String[] args) {
		ServiceConfig<IHelloService> serviceConfig = new ServiceConfig<>();
		serviceConfig.setProtocol("registry");
		serviceConfig.setRegistryConfig(new RegistryConfig("zookeeper", "172.16.219.129", 2181));
		serviceConfig.setPort(9080);
		serviceConfig.setInterfaceType(IHelloService.class);
		serviceConfig.setRef(new HelloServiceImpl());
		serviceConfig.export();
	}
}
