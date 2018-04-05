package org.hum.pumpkin.test.registry;

import java.util.Arrays;

import org.hum.pumpkin.config.ProtocolConfig;
import org.hum.pumpkin.config.ServiceConfig;
import org.hum.pumpkin.test._service.IHelloService;
import org.hum.pumpkin.test._service.IUserService;
import org.hum.pumpkin.test._service.impl.HelloServiceImpl;
import org.hum.pumpkin.test._service.impl.UserServiceImpl;

public class ServerTest {

	public static void main(String[] args) {
		ServiceConfig<IHelloService> serviceConfig = new ServiceConfig<>();
		serviceConfig.setProtocols(Arrays.asList(new ProtocolConfig[]{new ProtocolConfig("pumpkin", 9080)}));
		serviceConfig.setRegistry("zookeeper://172.16.219.129:2181");
		serviceConfig.setInterfaceType(IHelloService.class);
		serviceConfig.setRef(new HelloServiceImpl());
		serviceConfig.export();
		
		System.out.println(11);
		
		ServiceConfig<IUserService> serviceConfig2 = new ServiceConfig<>();
		serviceConfig2.setProtocols(Arrays.asList(new ProtocolConfig[]{new ProtocolConfig("pumpkin", 9080)}));
		serviceConfig2.setRegistry("zookeeper://172.16.219.129:2181");
		serviceConfig2.setInterfaceType(IUserService.class);
		serviceConfig2.setRef(new UserServiceImpl());
		serviceConfig2.export();
	}
}
