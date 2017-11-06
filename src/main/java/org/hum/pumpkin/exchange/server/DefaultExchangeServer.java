package org.hum.pumpkin.exchange.server;

import java.util.concurrent.ExecutorService;

import org.hum.pumpkin.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin.threadpool.ThreadPoolFactory;
import org.hum.pumpkin.transport.TransporterServer;

public class DefaultExchangeServer implements ExchangeServer {

	private TransporterServer transpoterServer;
	private static final ExecutorService executorService = ServiceLoaderHolder.loadByCache(ThreadPoolFactory.class).create();

	public DefaultExchangeServer(TransporterServer transpoterServer) {
		this.transpoterServer = transpoterServer;
	}
	
	@Override
	public void close() {
		transpoterServer.close();
	}
}
