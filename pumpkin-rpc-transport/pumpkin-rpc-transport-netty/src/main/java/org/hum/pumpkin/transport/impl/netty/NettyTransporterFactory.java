package org.hum.pumpkin.transport.impl.netty;

import org.hum.pumpkin.common.url.URL;
import org.hum.pumpkin.transport.client.Client;
import org.hum.pumpkin.transport.factory.AbstractTransporterFactory;
import org.hum.pumpkin.transport.impl.netty.client.NettyClient;
import org.hum.pumpkin.transport.impl.netty.server.NettyServer;
import org.hum.pumpkin.transport.server.Server;
import org.hum.pumpkin.transport.server.ServerHandler;

public class NettyTransporterFactory extends AbstractTransporterFactory {

	@Override
	public Server createServer(URL url, ServerHandler serverHandler) {
		return new NettyServer(url, serverHandler);
	}

	@Override
	protected Client doCreate(URL url) {
		return new NettyClient(url);
	}
}
