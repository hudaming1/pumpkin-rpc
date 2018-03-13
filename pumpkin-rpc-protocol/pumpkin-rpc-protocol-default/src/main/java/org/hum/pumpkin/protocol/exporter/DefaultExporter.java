package org.hum.pumpkin.protocol.exporter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.hum.pumpkin.common.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin.common.url.URL;
import org.hum.pumpkin.exchange.Exchanger;
import org.hum.pumpkin.exchange.Request;
import org.hum.pumpkin.exchange.Response;
import org.hum.pumpkin.exchange.server.ExchangeServer;
import org.hum.pumpkin.exchange.server.ExchangeServerHandler;
import org.hum.pumpkin.protocol.invoker.RpcInvocation;
import org.hum.pumpkin.protocol.invoker.RpcResult;
import org.hum.pumpkin.threadpool.ThreadPoolFactory;

/**
 * <pre>
 * 	Exporter属于典型的协议层Server，不仅需要取分出服务，还有Group和Version，
 * </pre>
 * 
 * <pre>
 * 	Filter也都在本层做（Filter最终还是决定参照Dubbo使用InvokerChain来做）
 * </pre>
 */
public class DefaultExporter<T> implements Exporter<T>{

	private static final Exchanger EXCHANGER = ServiceLoaderHolder.loadByCache(Exchanger.class);
	private static final ExecutorService EXECUTOR_SERVICE = ServiceLoaderHolder.loadByCache(ThreadPoolFactory.class).create();
	// TODO 这里应该存InvokerMap
	private static final Map<Class<?>, Object> beanMap = new ConcurrentHashMap<>();
	private T ref;
	private ExchangeServer exchangeServer;
	private ExchangeServerHandler serverHandler;
	
	public DefaultExporter(Class<T> classType, T instances, URL url) {
		this.ref = instances;
		if (!beanMap.containsKey(classType)) {
			beanMap.put(classType, instances);
		}
		this.serverHandler = new SimpleExchangeServerHandler();
		this.exchangeServer = EXCHANGER.bind(url, serverHandler);
	}

	@Override
	public void unexport() {
		exchangeServer.close();
	}
	
	private class SimpleExchangeServerHandler implements ExchangeServerHandler {
		@Override
		public Response handler(Request request) {
			try {
				RpcInvocation rpcInvocation = (RpcInvocation) request.getData();
				System.out.println(rpcInvocation);
				Object instance = beanMap.get(rpcInvocation.getInterfaceType());
				Future<RpcResult> future = EXECUTOR_SERVICE.submit(new Tasker((RpcInvocation) request.getData(), instance));
				return new Response(request.getId(), future.get(), null);
			} catch (Exception e) {
				return new Response(request.getId(), null, e);
			}
		}
	}
}
