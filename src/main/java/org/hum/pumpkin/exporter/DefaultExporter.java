package org.hum.pumpkin.exporter;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.hum.pumpkin.exchange.Exchanger;
import org.hum.pumpkin.exchange.Request;
import org.hum.pumpkin.exchange.Response;
import org.hum.pumpkin.exchange.server.ExchangeServer;
import org.hum.pumpkin.invoker.RpcInvocation;
import org.hum.pumpkin.invoker.RpcResult;
import org.hum.pumpkin.protocol.URL;
import org.hum.pumpkin.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin.threadpool.ThreadPoolFactory;
import org.hum.pumpkin.transport.ServerHandler;

public class DefaultExporter<T> implements Exporter<T>{

	private static final ExecutorService EXECUTOR_SERVICE = ServiceLoaderHolder.loadByCache(ThreadPoolFactory.class).create();
	private static final Exchanger EXCHANGER = ServiceLoaderHolder.loadByCache(Exchanger.class);
	private T ref;
	private ExchangeServer exchangeServer;
	
	
	public DefaultExporter(Class<T> classType, T instances, URL url) {
		this.ref = instances;
		this.exchangeServer = EXCHANGER.bind(url, serverHandler);
	}

	@Override
	public void unexport() {
		exchangeServer.close();
	}
}
