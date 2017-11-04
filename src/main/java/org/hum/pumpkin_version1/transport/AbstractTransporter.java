package org.hum.pumpkin_version1.transport;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.hum.pumpkin_version1.invoker.Invoker;
import org.hum.pumpkin_version1.invoker.holder.InvokerHolder;
import org.hum.pumpkin_version1.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin_version1.threadpool.ThreadPoolFactory;
import org.hum.pumpkin_version1.transport.bean.RpcInvocation;
import org.hum.pumpkin_version1.transport.bean.RpcResult;

public abstract class AbstractTransporter implements Transporter {

	private ExecutorService executorService = ServiceLoaderHolder.loadByCache(ThreadPoolFactory.class).create();
	
	private InvokerHolder invokerHolder = ServiceLoaderHolder.loadByCache(InvokerHolder.class);
	
	protected volatile boolean isRun = false;
	
	public void open(int port) {
		doOpen(port);
	}
	
	protected abstract void doOpen(int port);

	public void export(Class<?> classType, Object instances) {
		invokerHolder.create(classType, instances);
	}
	
	public RpcResult handler(final RpcInvocation invocation) throws InterruptedException, ExecutionException {
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
		return invokerHolder.getInvoker(serviceKey);
	}
}
