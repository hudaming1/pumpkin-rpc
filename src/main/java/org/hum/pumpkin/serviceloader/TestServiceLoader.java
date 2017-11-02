package org.hum.pumpkin.serviceloader;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hum.pumpkin.invoker.holder.DefaultInvokerHolder;
import org.hum.pumpkin.invoker.holder.InvokerHolder;
import org.hum.pumpkin.serialize.ObjectStreamSerialization;
import org.hum.pumpkin.serialize.Serialization;
import org.hum.pumpkin.threadpool.DefaultThreadPoolFactory;
import org.hum.pumpkin.threadpool.ThreadPoolFactory;
import org.hum.pumpkin.transport.Transporter2;
import org.hum.pumpkin.transport.jdk.JdkTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unchecked")
public class TestServiceLoader implements ServiceLoader {
	
	private static final Logger logger = LoggerFactory.getLogger(TestServiceLoader.class);
	
	private Map<Class<?>, Class<?>> classTypeMap = new ConcurrentHashMap<>();
	private Map<Class<?>, Object> instancesMap = new ConcurrentHashMap<>();
	{
		classTypeMap.put(ThreadPoolFactory.class, DefaultThreadPoolFactory.class);
		classTypeMap.put(Serialization.class, ObjectStreamSerialization.class);
		classTypeMap.put(InvokerHolder.class, DefaultInvokerHolder.class);
		classTypeMap.put(Transporter2.class, JdkTransport.class);
	}
	
	@Override
	public <T> T load(Class<T> interfaceType) {
		Object instances = instancesMap.get(interfaceType);
		if (instances != null) {
			return (T) instances;
		}
		Class<?> implementsClassType = classTypeMap.get(interfaceType);
		if (implementsClassType == null) {
			return null;
		}
		try {
			instances = implementsClassType.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			logger.error("cann't instance class [" + implementsClassType.getName() + "] exception");
			return null;
		}
		instancesMap.put(interfaceType, instances);
		return (T) instances;
	}
}
