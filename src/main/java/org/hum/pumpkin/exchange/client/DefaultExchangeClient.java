package org.hum.pumpkin.exchange.client;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.hum.pumpkin.exchange.Request;
import org.hum.pumpkin.exchange.Response;
import org.hum.pumpkin.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin.threadpool.ThreadPoolFactory;
import org.hum.pumpkin.transport.Transporter;

public class DefaultExchangeClient implements ExchangeClient {

	private Transporter transpoter;
	private static final ExecutorService executorService = ServiceLoaderHolder.loadByCache(ThreadPoolFactory.class).create();

	public DefaultExchangeClient(Transporter transpoter) {
		this.transpoter = transpoter;
	}

	@Override
	public Response send(Request request) {
		try {
			// 重试、超时等通信层逻辑都在Exchange类处理，然Transporter专心处理传输实现
			request.getTimeout();
			request.getRetryTimes();
			
			Future<Response> future = executorService.submit(new Callable<Response>() {
				@Override
				public Response call() throws Exception {
					return new Response(transpoter.send(request.getData()), null);
				}
			});
			
			return future.get();
		} catch (Exception ce) {
			return new Response(null, ce);
		}
	}
}
