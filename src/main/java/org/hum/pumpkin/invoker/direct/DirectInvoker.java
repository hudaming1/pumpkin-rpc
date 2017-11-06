package org.hum.pumpkin.invoker.direct;

import org.hum.pumpkin.exchange.Exchanger;
import org.hum.pumpkin.exchange.Request;
import org.hum.pumpkin.exchange.Response;
import org.hum.pumpkin.exchange.client.ExchangeClient;
import org.hum.pumpkin.invoker.RpcInvocation;
import org.hum.pumpkin.invoker.RpcResult;
import org.hum.pumpkin.protocol.URL;
import org.hum.pumpkin.serviceloader.ServiceLoaderHolder;

public class DirectInvoker<T> extends AbstractDirectInvoker<T> {

	private final Exchanger exchanger = ServiceLoaderHolder.loadByCache(Exchanger.class);
	private ExchangeClient client;
	private Class<T> classType;
	private URL url;

	public DirectInvoker(Class<T> classType, URL url) {
		this.classType = classType;
		this.url = url;
		client = exchanger.connect(url);
	}

	@Override
	public Class<T> getType() {
		return classType;
	}

	@Override
	public RpcResult invoke(RpcInvocation invocation) {
		try {
			Request request = new Request(url.getHost(), url.getPort(), invocation);
			Response response = client.send(request);
			return new RpcResult(response.getData(), null);
		} catch (Exception ce) {
			return new RpcResult(null, ce);
		}
	}
}
