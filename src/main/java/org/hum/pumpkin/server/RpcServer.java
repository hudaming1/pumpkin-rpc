package org.hum.pumpkin.server;

import org.hum.pumpkin.config.GlobalConfig;
import org.hum.pumpkin.config.ServerConfig;

public class RpcServer {
	
	private GlobalConfig globalConfig;

	public RpcServer(GlobalConfig globalConfig) {
		this.globalConfig = globalConfig;
	}
	
	public void export(ServerConfig serverConfig) {
		
	}
}
