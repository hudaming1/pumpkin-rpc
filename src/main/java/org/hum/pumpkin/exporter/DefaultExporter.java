package org.hum.pumpkin.exporter;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.hum.pumpkin.exchange.Exchanger;
import org.hum.pumpkin.exchange.Request;
import org.hum.pumpkin.exchange.Response;
import org.hum.pumpkin.exchange.server.ExchangeServer;
import org.hum.pumpkin.invoker.RpcInvocation;
import org.hum.pumpkin.protocol.URL;
import org.hum.pumpkin.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin.threadpool.ThreadPoolFactory;
import org.hum.pumpkin.transport.ServerHandler;

public class DefaultExporter<T> implements Exporter<T>{

	private static final ExecutorService EXECUTOR_SERVICE = ServiceLoaderHolder.loadByCache(ThreadPoolFactory.class).create();
	private static final Exchanger EXCHANGER = ServiceLoaderHolder.loadByCache(Exchanger.class);
	private T ref;
	private ExchangeServer exchangeServer;
	private ServerHandler serverHandler = new ServerHandler() {
		@Override
		public Response received(final Request request) {
			Future<Response> future = EXECUTOR_SERVICE.submit(new Callable<Response>() {
				@Override
				public Response call() throws Exception {
					if (!(request.getData() instanceof RpcInvocation)) {
						// TODO 思考异常怎么处理，是否要增加caughtException方法
						return new Response(request.getId(), null, null);
					}
					RpcInvocation invocation = (RpcInvocation) request.getData();
					try {
						Object result = ref.getClass().getMethod(invocation.getMethod(), invocation.getParamTypes()).invoke(ref, invocation.getParams());
						return new Response(request.getId(), result, null);
					} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						return new Response(request.getId(), null, e);
					} catch (Exception ce) {
						return new Response(request.getId(), null, ce);
					}
				}
			});
			try {
				return future.get();
			} catch (InterruptedException | ExecutionException e) {
				return new Response(request.getId(), null, e);
			}
		}
	};
	
	public DefaultExporter(Class<T> classType, T instances, URL url) {
		this.ref = instances;
		this.exchangeServer = EXCHANGER.bind(url, serverHandler);
	}

	@Override
	public void unexport() {
		exchangeServer.close();
	}
}
