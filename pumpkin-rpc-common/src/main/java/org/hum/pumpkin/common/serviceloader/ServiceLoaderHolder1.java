package org.hum.pumpkin.common.serviceloader;

import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 */
public class ServiceLoaderHolder1 {
	
	private final static Map<Class<?>, Object> INSTANCE_MAP = new ConcurrentHashMap<>();
	
	@SuppressWarnings("unchecked")
	public static <T> T loadByCache(Class<T> interfaceType) {
		if (interfaceType == null) {
			throw new IllegalArgumentException("type mustn't be null");
		}
		
		Object instances = INSTANCE_MAP.get(interfaceType);
		if (instances != null) {
			return (T) instances;
		}
		synchronized (INSTANCE_MAP) {
			instances = INSTANCE_MAP.get(interfaceType);
			if (instances != null) {
				return (T) instances;
			}
			ServiceLoader<T> serviceLoader = ServiceLoader.load(interfaceType);
			if (serviceLoader == null || serviceLoader.iterator() == null) {
				throw new IllegalArgumentException("cann't find implements for type [" + interfaceType.getName() + "]");
			}
			instances = serviceLoader.iterator().next();
			if (instances == null) {
				throw new IllegalArgumentException("cann't find implements for type [" + interfaceType.getName() + "]");
			}
			INSTANCE_MAP.put(interfaceType, instances);
		}
		return (T) instances;
	}
}
