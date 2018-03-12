package org.hum.pumpkin.protocol.invoker.cluster;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hum.pumpkin.common.exception.RpcException;
import org.hum.pumpkin.common.url.URL;
import org.hum.pumpkin.protocol.ProtocolEnum;
import org.hum.pumpkin.protocol.invoker.Invoker;
import org.hum.pumpkin.protocol.invoker.RpcInvocation;
import org.hum.pumpkin.protocol.invoker.RpcResult;
import org.hum.pumpkin.protocol.invoker.direct.DirectInvoker;
import org.hum.pumpkin.registry.Registry;
import org.hum.pumpkin.registry.RegistryConfig;

public class ClusterInvoker<T> implements Invoker<T> {

	private RegistryConfig registryConfig;
	private Registry registry;
	private Class<T> classType;
	private URL url;
	// className -> ip:port -> Client
	private Map<String, Map<String, DirectInvoker<T>>> clientMap = new HashMap<>();

	// TODO registryConfig和Registry，作为2个参数传入，有点恶心
	public ClusterInvoker(Registry registry, RegistryConfig registryConfig, Class<T> classType, URL url) {
		this.registryConfig = registryConfig;
		this.registry = registry;
		this.classType = classType;
		this.url = url;
		// init client
		// 1.find registiry url TODO 采用自动发现机制
		List<String> urls = registry.discover(classType.getName());
		if (urls == null || urls.isEmpty()) {
			throw new RpcException("no found availabled server! classtype=" + classType.getName());
		}

		// 1.5 init map
		Map<String, DirectInvoker<T>> cmap = clientMap.get(classType.getName());
		if (cmap == null) {
			clientMap.put(classType.getName(), new ConcurrentHashMap<String, DirectInvoker<T>>());
		}
		
		// 2.reg
		for (String _url : urls) {
			String[] urlArr = _url.split(":");
			URL directUrl = new URL(ProtocolEnum.Direct.getName(), urlArr[0], Integer.parseInt(urlArr[1]), classType.getName());
			DirectInvoker<T> invoker = new DirectInvoker<>(classType, directUrl);
			clientMap.get(classType.getName()).put(_url, invoker);
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
			
			// 3.select url
			Map<String, DirectInvoker<T>> cmap = clientMap.get(classType.getName());
			if (cmap == null) {
				clientMap.put(classType.getName(), new ConcurrentHashMap<String, DirectInvoker<T>>());
			}
			if (cmap != null && cmap.containsKey(url)) {
				return cmap.get(url).invoke(invocation);
			}

			// 4.create client
			URL directUrl = new URL(ProtocolEnum.Direct.getName(), urlArr[0], Integer.parseInt(urlArr[1]), classType.getName());
			DirectInvoker<T> invoker = new DirectInvoker<>(classType, directUrl);
			clientMap.get(classType.getName()).put(url, invoker);
			return invoker.invoke(invocation);
		} catch (Exception ce) {
			ce.printStackTrace();
			return new RpcResult(null, ce);
		}
	}
}
