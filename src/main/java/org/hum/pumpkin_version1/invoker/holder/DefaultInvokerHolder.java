package org.hum.pumpkin_version1.invoker.holder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hum.pumpkin_version1.invoker.DefaultInvoker;
import org.hum.pumpkin_version1.invoker.Invoker;
import org.hum.pumpkin_version1.transport.ServiceKey;

public class DefaultInvokerHolder implements InvokerHolder {
	
	private final Map<ServiceKey, Invoker> invokerMap = new ConcurrentHashMap<>(); 

	public void create(Class<?> classType, Object instances) {
		invokerMap.put(new ServiceKey(classType), new DefaultInvoker(classType, instances));
	}

	public Invoker getInvoker(ServiceKey serviceKey) {
		return invokerMap.get(serviceKey);
	}
}
