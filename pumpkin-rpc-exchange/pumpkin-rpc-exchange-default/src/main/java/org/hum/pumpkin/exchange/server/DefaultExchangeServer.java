package org.hum.pumpkin.exchange.server;

import org.hum.pumpkin.transport.server.Server;

public class DefaultExchangeServer implements ExchangeServer {

	// TODO 虽然Exchanger是单例的，但这里的TransporterServer也要保证单例模式
	private static volatile Server server;

	public DefaultExchangeServer(Server server) {
		if (DefaultExchangeServer.server != null) {
			return ;
		}
		DefaultExchangeServer.server = server;
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("start server");
				DefaultExchangeServer.server.open();
			}
		}).start();
	}
	
	@Override
	public void close() {
		server.close();
	}
}
