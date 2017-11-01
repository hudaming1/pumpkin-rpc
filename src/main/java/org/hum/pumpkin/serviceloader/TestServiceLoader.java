package org.hum.pumpkin.serviceloader;

import java.util.HashMap;
import java.util.Map;

import org.hum.pumpkin.invoker.holder.DefaultInvokerHolder;
import org.hum.pumpkin.invoker.holder.InvokerHolder;
import org.hum.pumpkin.serialize.ObjectStreamSerialization;
import org.hum.pumpkin.serialize.Serialization;
import org.hum.pumpkin.threadpool.DefaultThreadPoolFactory;
import org.hum.pumpkin.threadpool.ThreadPoolFactory;
import org.hum.pumpkin.transport.Transporter;
import org.hum.pumpkin.transport.jdk.JdkTransport;

@SuppressWarnings("unchecked")
public class TestServiceLoader implements ServiceLoader {
	
	private Map<Class<?>, Object> instanceMap = new HashMap<>();
	{
		instanceMap.put(Transporter.class, new JdkTransport());
		instanceMap.put(ThreadPoolFactory.class, new DefaultThreadPoolFactory());
		instanceMap.put(Serialization.class, new ObjectStreamSerialization());
		instanceMap.put(InvokerHolder.class, new DefaultInvokerHolder());
	}
	
	@Override
	public <T> T load(Class<T> interfaceType) {
		return (T) instanceMap.get(interfaceType);
	}
}
