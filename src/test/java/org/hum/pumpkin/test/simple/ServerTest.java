package org.hum.pumpkin.test.simple;

import org.hum.pumpkin.test._service.HelloServiceImpl;
import org.hum.pumpkin.test._service.IHelloService;
import org.hum.pumpkin_version1.config.server.ServiceBean;
import org.hum.pumpkin_version1.config.server.ServiceConfig;
import org.hum.pumpkin_version1.server.RpcServer;

public class ServerTest {

	public static void main(String[] args) {
		
		ServiceConfig serviceConfig = new ServiceConfig();
		serviceConfig.setPort(9080);
		
		RpcServer rpcServer = new RpcServer(serviceConfig);
		rpcServer.export(new ServiceBean(IHelloService.class, new HelloServiceImpl()));
	}
}
