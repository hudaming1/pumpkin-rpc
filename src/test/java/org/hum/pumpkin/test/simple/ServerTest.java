package org.hum.pumpkin.test.simple;

import org.hum.pumpkin.config.GlobalConfig;
import org.hum.pumpkin.config.ServerConfig;
import org.hum.pumpkin.server.RpcServer;
import org.hum.pumpkin.test._service.HelloServiceImpl;
import org.hum.pumpkin.test._service.IHelloService;

public class ServerTest {

	public static void main(String[] args) {
		// TODO 这个port应该放在哪？
		GlobalConfig globalConfig = GlobalConfig.getInstances().setPort(9080);
		
		RpcServer rpcServer = new RpcServer(globalConfig);
		
		rpcServer.export(new ServerConfig(IHelloService.class, new HelloServiceImpl()));
	}
}
