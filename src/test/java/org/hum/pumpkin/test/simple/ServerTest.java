package org.hum.pumpkin.test.simple;

import org.hum.pumpkin.config.server.ServiceBean;
import org.hum.pumpkin.config.server.ServiceConfig;
import org.hum.pumpkin.server.RpcServer;
import org.hum.pumpkin.test._service.HelloServiceImpl;
import org.hum.pumpkin.test._service.IHelloService;

public class ServerTest {

	public static void main(String[] args) {
		
		ServiceConfig serviceConfig = new ServiceConfig();
		// serviceConfig.setTransport("jdk/netty");
		serviceConfig.setPort(9080);
		
		RpcServer rpcServer = new RpcServer(serviceConfig);
		rpcServer.export(new ServiceBean(IHelloService.class, new HelloServiceImpl()));
	}
}
