package org.hum.pumpkin.test.simple;

import org.hum.pumpkin.config.ServiceConfig;
import org.hum.pumpkin.test._service.HelloServiceImpl;
import org.hum.pumpkin.test._service.IHelloService;
import org.hum.pumpkin.test._service.IUserService;
import org.hum.pumpkin.test._service.UserServiceImpl;

public class ServerTest {

	public static void main(String[] args) {
		ServiceConfig<IHelloService> serviceConfig = new ServiceConfig<>();
		serviceConfig.setProtocol("pumpkin");
//		serviceConfig.setRegistryConfig(new RegistryConfig("zookeeper", "172.16.219.129", 2181));
		serviceConfig.setPort(9080);
		serviceConfig.setInterfaceType(IHelloService.class);
		serviceConfig.setRef(new HelloServiceImpl());
		serviceConfig.export();
		
		System.out.println(11);
		
		ServiceConfig<IUserService> serviceConfig2 = new ServiceConfig<>();
		serviceConfig2.setProtocol("pumpkin");
//		serviceConfig.setRegistryConfig(new RegistryConfig("zookeeper", "172.16.219.129", 2181));
		serviceConfig2.setPort(9080);
		serviceConfig2.setInterfaceType(IUserService.class);
		serviceConfig2.setRef(new UserServiceImpl());
		serviceConfig2.export();
		
		
	}
}
