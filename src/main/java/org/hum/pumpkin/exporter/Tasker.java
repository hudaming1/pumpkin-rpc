package org.hum.pumpkin.exporter;

import java.util.concurrent.Callable;

import org.hum.pumpkin.invoker.RpcInvocation;
import org.hum.pumpkin.invoker.RpcResult;
import org.hum.pumpkin.proxy.ProxyFactory;
import org.hum.pumpkin.serviceloader.ServiceLoaderHolder;

public class Tasker implements Callable<RpcResult> {
	
	private final static ProxyFactory proxyFactory = ServiceLoaderHolder.loadByCache(ProxyFactory.class);
	private RpcInvocation invocation;
	private Object ref;
	
	public Tasker(RpcInvocation invocation, Object instance) {
		this.invocation = invocation;
		this.ref = instance;
	}
	
	@Override
	public RpcResult call() throws Exception {
		try {
			Object result = proxyFactory.invoke(ref, invocation.getMethod(), invocation.getParamTypes(), invocation.getParams());
			return new RpcResult(result, null);
		} catch (Exception ce) {
			return new RpcResult(null, ce);
		}
	}
}
