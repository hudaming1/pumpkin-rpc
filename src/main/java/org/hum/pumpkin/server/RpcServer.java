package org.hum.pumpkin.server;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.hum.pumpkin.config.server.ServiceBean;
import org.hum.pumpkin.config.server.ServiceConfig;
import org.hum.pumpkin.registry.Registry;
import org.hum.pumpkin.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin.transport.Transporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RpcServer {
	
	private static final Logger logger = LoggerFactory.getLogger(RpcServer.class);
	
	private Transporter transport;
	
	private ServiceConfig serviceConfig;
	
	private Registry registry;
	
	public RpcServer(ServiceConfig serverConfig) {
		this.serviceConfig = serverConfig;
		
		// 1.check global config
		this.serviceConfig.validate();
		
		// 2.load transport (if null default jdk transporter TODO)
		this.transport = ServiceLoaderHolder.loadByCache(serverConfig.getTransportConfig().getType());
		this.transport.open(serverConfig.getPort());
		
		// TODO 
		if (serverConfig.getRegistryConfig() != null) {
			registry = ServiceLoaderHolder.loadByCache(serverConfig.getRegistryConfig().getType());
			registry.connect(serverConfig.getRegistryConfig().getAddress(), serverConfig.getRegistryConfig().getPort());
		}
	}
	
	public void export(ServiceBean serviceBean) {
		// 1.export service
		transport.export(serviceBean.getInterfaceType(), serviceBean.getInstances());
		
		// 2.registry service
		try {
			if (registry != null) {
				registry.registry(serviceBean.getInterfaceType().getName(), InetAddress.getLocalHost().getHostAddress(), serviceConfig.getPort());
			}
		} catch (UnknownHostException e) {
			logger.error("regist node fail, unnkown host", e);
		}
	}
}
