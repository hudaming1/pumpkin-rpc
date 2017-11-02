package org.hum.pumpkin.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.hum.pumpkin.config.client.ReferenceBean;
import org.hum.pumpkin.config.client.ReferenceConfig;

@SuppressWarnings("unchecked")
public class RpcClient {
	
	private ReferenceConfig clientConfig;
	
	public RpcClient(ReferenceConfig clientConfig) {
		this.clientConfig = clientConfig;
	}

	public <T> T proxy(ReferenceBean<T> referenceBean) {
		return (T) Proxy.newProxyInstance(referenceBean.getInterfaceType().getClassLoader(), new Class[] { referenceBean.getInterfaceType() }, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}
}
