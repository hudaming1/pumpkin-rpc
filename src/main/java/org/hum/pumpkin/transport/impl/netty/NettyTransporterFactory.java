package org.hum.pumpkin.transport.impl.netty;

import org.hum.pumpkin.protocol.URL;
import org.hum.pumpkin.transport.ServerHandler;
import org.hum.pumpkin.transport.Client;
import org.hum.pumpkin.transport.Server;
import org.hum.pumpkin.transport.factory.AbstractTransporterFactory;

public class NettyTransporterFactory extends AbstractTransporterFactory {

	@Override
	public Server createServer(URL url, ServerHandler serverHandler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Client doCreate(URL url) {
		// TODO Auto-generated method stub
		return null;
	}
}