package org.hum.pumpkin.test.simple;

import org.hum.pumpkin.server.ServiceConfig;
import org.hum.pumpkin.test._service.HelloServiceImpl;
import org.hum.pumpkin.test._service.IHelloService;

public class ServerTest {

	public static void main(String[] args) {
		ServiceConfig<IHelloService> serviceConfig = new ServiceConfig<>();
		serviceConfig.setPort(9080);
		serviceConfig.setInterfaceType(IHelloService.class);
		serviceConfig.setRef(new HelloServiceImpl());
		serviceConfig.export();
	}
}
