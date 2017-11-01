package org.hum.pumpkin.test.simple;

import org.hum.pumpkin.config.GlobalConfig;
import org.hum.pumpkin.config.ServerConfig;
import org.hum.pumpkin.server.RpcServer;
import org.hum.pumpkin.test._service.HelloServiceImpl;

public class ServerTest {

	public static void main(String[] args) {
		GlobalConfig globalConfig = new GlobalConfig();
		
		RpcServer rpcServer = new RpcServer(globalConfig);
		
		rpcServer.export(new ServerConfig(new HelloServiceImpl()));
	}
}
