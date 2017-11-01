package org.hum.pumpkin.transport;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.hum.pumpkin.beanmap.InstancesMap;
import org.hum.pumpkin.invoker.Invoker;
import org.hum.pumpkin.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin.threadpool.ThreadPoolFactory;
import org.hum.pumpkin.transport.bean.RpcInvocation;
import org.hum.pumpkin.transport.bean.RpcResult;

public abstract class AbstractTransporter implements Transporter {
	
	private final InstancesMap instancesMap = ServiceLoaderHolder.loadByCache(InstancesMap.class);
	
	private final Map<ServiceKey, Invoker> invokerMap = new ConcurrentHashMap<>(); 
	
	private ExecutorService executorService = ServiceLoaderHolder.loadByCache(ThreadPoolFactory.class).create();
	
	protected volatile boolean isRun = false;
	
	public void open(int port) {
		// 1.check port
		
		// 2.start server
		doOpen(port);
	}
	
	protected abstract void doOpen(int port);

	public void export(String name, Object instances) {
		instancesMap.put(name, instances);
	}
	
	protected RpcResult handler(RpcInvocation invocation) throws InterruptedException, ExecutionException {
		final Invoker invoker = getInvoker(new ServiceKey(invocation.getClazz()));
		
		Future<RpcResult> future = executorService.submit(new Callable<RpcResult>() {
			@Override
			public RpcResult call() throws Exception {
				return invoker.invoke(invocation);
			}
		});
		
		return future.get();
	}
	
	private Invoker getInvoker(ServiceKey serviceKey) {
		return invokerMap.get(serviceKey);
	}
}
