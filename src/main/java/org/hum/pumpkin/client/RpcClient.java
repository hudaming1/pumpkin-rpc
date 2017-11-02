package org.hum.pumpkin.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.hum.pumpkin.config.client.ReferenceBean;
import org.hum.pumpkin.config.client.ReferenceConfig;
import org.hum.pumpkin.proxy.Proxy;
import org.hum.pumpkin.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin.transport.Transporter2;
import org.hum.pumpkin.transport.bean.RpcInvocation;

public class RpcClient {
	
	private ReferenceConfig clientConfig;

	private final Proxy proxier = ServiceLoaderHolder.loadByCache(Proxy.class);
	
	private final Transporter2 transporter = ServiceLoaderHolder.loadByCache(Transporter2.class);
	
	public RpcClient(ReferenceConfig clientConfig) {
		this.clientConfig = clientConfig;
	}

	public <T> T proxy(final ReferenceBean<T> referenceBean) {
		return proxier.proxy(referenceBean.getInterfaceType().getClassLoader(), referenceBean.getInterfaceType(), new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				
				RpcInvocation invocation = new RpcInvocation(referenceBean.getInterfaceType(), method.getName(), method.getParameterTypes(), args);
				
				return transporter.invoke(invocation).getResult();
			}
		});
	}
}
