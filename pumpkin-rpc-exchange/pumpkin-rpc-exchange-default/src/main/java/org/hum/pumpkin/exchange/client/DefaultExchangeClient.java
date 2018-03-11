package org.hum.pumpkin.exchange.client;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.hum.pumpkin.common.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin.exchange.Request;
import org.hum.pumpkin.exchange.Response;
import org.hum.pumpkin.threadpool.ThreadPoolFactory;
import org.hum.pumpkin.transport.client.Client;
import org.hum.pumpkin.transport.message.Header;
import org.hum.pumpkin.transport.message.Message;
import org.hum.pumpkin.transport.message.MessageBack;
import org.hum.pumpkin.transport.message.MessageTypeEnum;

/**
 * Exchange层默认实现，DefaultExchange需要实现
 * 	重试、超时、异步等功能
 */
public class DefaultExchangeClient implements ExchangeClient {

	private Client client;
	private static final ExecutorService executorService = ServiceLoaderHolder.loadByCache(ThreadPoolFactory.class).create();

	public DefaultExchangeClient(Client client) {
		this.client = client;
	}

	@Override
	public Response send(final Request request) {
		try {
			// 重试、超时等通信层逻辑都在Exchange类处理，然Transporter专心处理传输实现
			// TODO 考虑这里需要异步处理吗？
			Future<Response> future = executorService.submit(new Callable<Response>() {
				@Override
				public Response call() throws Exception {
					Message message = new Message(new Header(request.getId(), MessageTypeEnum.Business.getCode()), request);
					MessageBack messageBack = client.send(message);
					if (messageBack.getHeader().getType() == MessageTypeEnum.Business.getCode()) {
						return (Response) messageBack.getBody();
					} else {
						// TODO other wise
						return null;
					}
				}
			});
			
			return future.get();
		} catch (Exception ce) {
			return new Response(request.getId(), null, ce);
		}
	}
}
