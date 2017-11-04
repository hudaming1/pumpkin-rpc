package org.hum.pumpkin.transport.holder;

import org.hum.pumpkin.transport.Request;
import org.hum.pumpkin.transport.Response;
import org.hum.pumpkin.transport.config.TransporterConfig;

/**
 * TODO 我现在该如何保障holder中的连接和注册中心的同步呢？
 */
public interface TransporterHolder {

	public void join(TransporterConfig transporterConfig);
	
	public void remove(String address, int port);
	
	public Response send(Request request) ;
}
