package org.hum.pumpkin.protocol.invoker;

import org.hum.pumpkin.common.serviceloader.ExtensionLoader;
import org.hum.pumpkin.common.url.URL;
import org.hum.pumpkin.exchange.Exchanger;
import org.hum.pumpkin.exchange.Request;
import org.hum.pumpkin.exchange.Response;
import org.hum.pumpkin.exchange.client.ExchangeClient;
import org.hum.pumpkin.protocol.invoker.RpcInvocation;
import org.hum.pumpkin.protocol.invoker.RpcResult;

public class DirectInvoker<T> extends AbstractDirectInvoker<T> {

	private final Exchanger exchanger = ExtensionLoader.getExtensionLoader(Exchanger.class).getDefault();
	private ExchangeClient exchangeClient;
	private Class<T> classType;
	private URL url;

	public DirectInvoker(Class<T> classType, URL url) {
		this.classType = classType;
		this.url = url;
		exchangeClient = exchanger.connect(url);
	}

	@Override
	public Class<T> getType() {
		return classType;
	}

	@Override
	public RpcResult invoke(RpcInvocation invocation) {
		try {
			Request request = new Request(url.getHost(), url.getPort(), invocation);
			// TODO 日后完善
			// request.setRetryTimes(url.getInteger(UrlConstant.RETRY_TIMES));
			Response response = exchangeClient.send(request);
			if (response.getData() instanceof RpcResult) {
				return (RpcResult) response.getData();
			} else {
				throw new ClassCastException("response data cann't parse, type is " + response.getData().getClass().getName());
			}
		} catch (Exception ce) {
			return new RpcResult(null, ce);
		}
	}
}
