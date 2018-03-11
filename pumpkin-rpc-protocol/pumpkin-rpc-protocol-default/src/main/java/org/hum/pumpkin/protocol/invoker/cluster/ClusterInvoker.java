package org.hum.pumpkin.protocol.invoker.cluster;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hum.pumpkin.common.exception.RpcException;
import org.hum.pumpkin.common.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin.common.url.URL;
import org.hum.pumpkin.exchange.Exchanger;
import org.hum.pumpkin.exchange.Request;
import org.hum.pumpkin.exchange.Response;
import org.hum.pumpkin.exchange.client.ExchangeClient;
import org.hum.pumpkin.protocol.ProtocolEnum;
import org.hum.pumpkin.protocol.invoker.Invoker;
import org.hum.pumpkin.protocol.invoker.RpcInvocation;
import org.hum.pumpkin.protocol.invoker.RpcResult;
import org.hum.pumpkin.registry.Registry;
import org.hum.pumpkin.registry.RegistryConfig;

public class ClusterInvoker<T> implements Invoker<T> {

	private final Exchanger exchanger = ServiceLoaderHolder.loadByCache(Exchanger.class);
	private ExchangeClient exchangeClient;
	private RegistryConfig registryConfig;
	private Registry registry;
	private Class<T> classType;
	private URL url;
	// className -> ip:port -> Client
	private Map<String, Map<String, ExchangeClient>> clientMap = new HashMap<>();

	// TODO registryConfig和Registry，作为2个参数传入，有点恶心
	public ClusterInvoker(Registry registry, RegistryConfig registryConfig, Class<T> classType, URL url) {
		this.registryConfig = registryConfig;
		this.registry = registry;
		this.classType = classType;
		this.url = url;
		if (url.getProtocol().equals(ProtocolEnum.Direct.getName())) {
			this.exchangeClient = exchanger.connect(url);
		} else if (url.getProtocol().equals(ProtocolEnum.Registry.getName())) {
			// init client
			// 1.find registiry url TODO 采用自动发现机制
			List<String> urls = registry.discover(classType.getName());
			if (urls == null || urls.isEmpty()) {
				throw new RpcException("no found availabled server! classtype=" + classType.getName());
			}

			// 1.5 init map
			Map<String, ExchangeClient> cmap = clientMap.get(classType.getName());
			if (cmap == null) {
				clientMap.put(classType.getName(), new ConcurrentHashMap<String, ExchangeClient>());
			}
			
			// 2.reg
			for (String _url : urls) {
				String[] urlArr = _url.split(":");
				ExchangeClient exchangeClient = exchanger.connect(new URL(registryConfig.getName(), urlArr[0], Integer.parseInt(urlArr[1]), classType.getName()));
				clientMap.get(classType.getName()).put(_url, exchangeClient);
			}
		}
	}

	@Override
	public Class<T> getType() {
		return classType;
	}

	@Override
	public RpcResult invoke(RpcInvocation invocation) {
		try {
			// TODO 日后完善
			// request.setRetryTimes(url.getInteger(UrlConstant.RETRY_TIMES));
			
			// 1.find registiry url TODO 采用自动发现机制
			List<String> urls = registry.discover(classType.getName());
			if (urls == null || urls.isEmpty()) {
				throw new RpcException("no found availabled server! classtype=" + classType.getName());
			}

			// 2.find router strategy TODO SPI
			Long index = invocation.getInvocationId() % urls.size() ;
			String url = urls.get(index.intValue());
			String[] urlArr = url.split(":");
			Request request = new Request(urlArr[0], Integer.parseInt(urlArr[1]), invocation);
			
			// 3.select url
			Map<String, ExchangeClient> cmap = clientMap.get(classType.getName());
			if (cmap == null) {
				clientMap.put(classType.getName(), new ConcurrentHashMap<String, ExchangeClient>());
			}
			if (cmap != null && cmap.containsKey(url)) {
				Response response = cmap.get(url).send(request);
				if (response.getData() instanceof RpcResult) {
					return (RpcResult) response.getData();
				} else {
					throw new ClassCastException("response data cann't parse, type is " + response.getData().getClass().getName());
				}
			}

			// 4.create client
			ExchangeClient exchangeClient = exchanger.connect(new URL(registryConfig.getName(), urlArr[0], Integer.parseInt(urlArr[1]), classType.getName()));
			clientMap.get(classType.getName()).put(url, exchangeClient);
			Response response = exchangeClient.send(request);
			if (response.getData() instanceof RpcResult) {
				return (RpcResult) response.getData();
			} else {
				throw new ClassCastException("response data cann't parse, type is " + response.getData().getClass().getName());
			}
		} catch (Exception ce) {
			ce.printStackTrace();
			return new RpcResult(null, ce);
		}
	}
}
