package org.hum.pumpkin_version1.server;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.hum.pumpkin_version1.config.server.ServiceBean;
import org.hum.pumpkin_version1.config.server.ServiceConfig;
import org.hum.pumpkin_version1.registry.Registry;
import org.hum.pumpkin_version1.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin_version1.transport.Transporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RpcServer {
	
	private static final Logger logger = LoggerFactory.getLogger(RpcServer.class);
	
	private Transporter transport = ServiceLoaderHolder.loadByCache(Transporter.class);
	
	private ServiceConfig serviceConfig;
	
	private Registry registry = ServiceLoaderHolder.loadByCache(Registry.class);
	
	public RpcServer(ServiceConfig serverConfig) {
		this.serviceConfig = serverConfig;
		
		// 1.check global config
		this.serviceConfig.validate();
		
		// 2.load transport (if null default jdk transporter TODO)
		this.transport.open(serverConfig.getPort());
		
		// TODO 
		if (serverConfig.getRegistryConfig() != null) {
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
