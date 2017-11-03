package org.hum.pumpkin_version1.serviceloader;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hum.pumpkin_version1.invoker.holder.DefaultInvokerHolder;
import org.hum.pumpkin_version1.invoker.holder.InvokerHolder;
import org.hum.pumpkin_version1.serialize.ObjectStreamSerialization;
import org.hum.pumpkin_version1.serialize.Serialization;
import org.hum.pumpkin_version1.threadpool.DefaultThreadPoolFactory;
import org.hum.pumpkin_version1.threadpool.ThreadPoolFactory;
import org.hum.pumpkin_version1.transport.Transporter;
import org.hum.pumpkin_version1.transport.jdk.JdkTransport;
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
		classTypeMap.put(Transporter.class, JdkTransport.class);
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
