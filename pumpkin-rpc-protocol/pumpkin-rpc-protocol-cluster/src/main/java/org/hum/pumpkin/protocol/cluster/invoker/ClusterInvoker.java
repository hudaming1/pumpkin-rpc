package org.hum.pumpkin.protocol.cluster.invoker;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hum.pumpkin.common.exception.RpcException;
import org.hum.pumpkin.common.serviceloader.ExtensionLoader;
import org.hum.pumpkin.common.url.URL;
import org.hum.pumpkin.protocol.cluster.directory.Directory;
import org.hum.pumpkin.protocol.cluster.directory.DirectoryFactory;
import org.hum.pumpkin.protocol.invoker.Invoker;
import org.hum.pumpkin.protocol.invoker.InvokerFactory;
import org.hum.pumpkin.protocol.invoker.RpcInvocation;
import org.hum.pumpkin.protocol.invoker.RpcResult;
import org.hum.pumpkin.registry.EndPoint;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ClusterInvoker<T> implements Invoker<T> {

	private static final InvokerFactory INVOKER_FACTORY = ExtensionLoader.getExtensionLoader(InvokerFactory.class).getAdaptive();
	private static final DirectoryFactory DIRECOTRY_FACTORY = ExtensionLoader.getExtensionLoader(DirectoryFactory.class).getDefault();
	// ip:port -> Invoker
	private final Map<EndPoint, Invoker<T>> clientMap = new ConcurrentHashMap<>();
	private final Map<EndPoint, Object> INVOKER_LOCK_MAP = new ConcurrentHashMap<>();
	private Directory<T> directory;
	private String registryString;
	private Class<T> classType;
	private URL url;

	public ClusterInvoker(String registryString, Class<T> classType, URL url) {
		this.registryString = registryString;
		this.classType = classType;
		this.url = url;
		this.directory = DIRECOTRY_FACTORY.createDirectory(classType, this.registryString);
		this.initInvokers();
	}

	private void initInvokers() {
		List<EndPoint> endPoints = this.directory.list(classType.getName());
		if (endPoints == null || endPoints.isEmpty()) {
			throw new RpcException("no found availabled server! classtype=" + classType.getName());
		}

		for (EndPoint endPoint : endPoints) {
			if (!clientMap.containsKey(endPoint)) {
				clientMap.put(endPoint, createInvoker(endPoint));
			}
		}
	}
	
	/**
	 * TODO
	 * 1.线程安全
	 * 2.确保创建完Transporter后，在返回Invoker（现在因为是异步创建，导致新产生的Invoker会调用失败）
	 * @param endPoint
	 * @return
	 */
	private Invoker<T> createInvoker(EndPoint endPoint) {
		synchronized (getCreateInvokerLock(endPoint)) {
			if (clientMap.get(endPoint) == null) {
				// 根据不同的协议，创建不同的Invoker
				URL _url = new URL(url.getProtocol(), endPoint.getAddress(), endPoint.getPort(), classType.getName(), url);
				clientMap.putIfAbsent(endPoint, INVOKER_FACTORY.create(classType, _url));
			}
			return clientMap.get(endPoint);
		}
	}
	
	private Object getCreateInvokerLock(EndPoint endPoint) {
		Object lock = INVOKER_LOCK_MAP.get(endPoint);
		if (lock == null) {
			INVOKER_LOCK_MAP.putIfAbsent(endPoint, new Object());
			lock = INVOKER_LOCK_MAP.get(endPoint);
		}
		return lock;
	}

	@Override
	public Class<T> getType() {
		return classType;
	}

	@Override
	public RpcResult invoke(RpcInvocation invocation) {
		try {
			// 1.find registiry url
			List<EndPoint> urls = this.directory.list(classType.getName());
			if (urls == null || urls.isEmpty()) {
				throw new RpcException("no found availabled server! classtype=" + classType.getName());
			}

			// 2.find router strategy TODO 需要扩展支持
			Long index = invocation.getInvocationId() % urls.size();
			EndPoint endPoint = urls.get(index.intValue());

			// 3.select url
			if (clientMap.containsKey(endPoint)) {
				return clientMap.get(endPoint).invoke(invocation);
			}

			// 4.create client
			Invoker<T> invoker = createInvoker(endPoint);
			return invoker.invoke(invocation);
		} catch (Exception ce) {
			throw new RpcException("invoke failed!", ce);
		}
	}
}
