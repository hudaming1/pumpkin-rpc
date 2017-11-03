package org.hum.pumpkin_version1.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.hum.pumpkin_version1.config.client.ReferenceBean;
import org.hum.pumpkin_version1.config.client.ReferenceConfig;
import org.hum.pumpkin_version1.proxy.Proxy;
import org.hum.pumpkin_version1.serviceloader.ServiceLoaderHolder;
import org.hum.pumpkin_version1.transport.Transporter;
import org.hum.pumpkin_version1.transport.bean.RpcInvocation;

public class RpcClient {
	
	@SuppressWarnings("unused")
	private ReferenceConfig clientConfig;

	private final Proxy proxier = ServiceLoaderHolder.loadByCache(Proxy.class);
	
	private final Transporter transporter = ServiceLoaderHolder.loadByCache(Transporter.class);
	
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
