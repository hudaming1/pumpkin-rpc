package org.hum.pumpkin.server;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.hum.pumpkin.beanmap.InstancesMap;
import org.hum.pumpkin.config.GlobalConfig;
import org.hum.pumpkin.config.ServerConfig;
import org.hum.pumpkin.proxy.RpcProxy;
import org.hum.pumpkin.registry.Registry;
import org.hum.pumpkin.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin.transport.Transporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RpcServer {
	
	private static final Logger logger = LoggerFactory.getLogger(RpcServer.class);
	
	private GlobalConfig globalConfig;
	
	private RpcProxy rpcProxy = ServiceLoaderHolder.load(RpcProxy.class);
	
	private Transporter transport = ServiceLoaderHolder.load(Transporter.class);
	
	private InstancesMap instancesMap = ServiceLoaderHolder.load(InstancesMap.class);
	
	private Registry registry = ServiceLoaderHolder.load(Registry.class);
	
	public RpcServer(GlobalConfig globalConfig) {
		this.globalConfig = globalConfig;
		
		// 1.check global config
		this.globalConfig.validate();
		
		// TODO 
		if (globalConfig.getRegistryConfig() != null) {
			registry.connect(globalConfig.getRegistryConfig().getAddress(), globalConfig.getRegistryConfig().getPort());
		}
	}
	
	public void export(ServerConfig serverConfig) {
		// 1.export service
		transport.export(serverConfig.getInterfaceType().getName(), serverConfig.getInstances());
		
		// 2.registry service
		try {
			registry.registry(serverConfig.getInterfaceType().getName(), InetAddress.getLocalHost().getHostAddress(), globalConfig.getPort());
		} catch (UnknownHostException e) {
			logger.error("regist node fail, unnkown host", e);
		}
	}
}
