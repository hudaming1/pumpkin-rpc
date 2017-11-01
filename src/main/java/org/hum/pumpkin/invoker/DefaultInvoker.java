package org.hum.pumpkin.invoker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.hum.pumpkin.transport.bean.RpcInvocation;
import org.hum.pumpkin.transport.bean.RpcResult;

public class DefaultInvoker implements Invoker {
	
	private Class<?> classType;
	private Object instances;
	
	public DefaultInvoker(Class<?> classType, Object instances) {
		this.classType = classType;
		this.instances = instances;
	}

	@Override
	public RpcResult invoke(RpcInvocation invocation) {
		try {
			Method method = classType.getMethod(invocation.getMethod(), invocation.getParamTypes());
			Object result = method.invoke(instances, invocation.getParams());
			return new RpcResult(invocation.getId(), result, null);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception ce) {
			
		}
		return null;
	}
}
