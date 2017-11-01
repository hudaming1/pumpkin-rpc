package org.hum.pumpkin.serviceloader;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("unchecked")
public class ServiceLoaderHolder {
	
	private static ServiceLoader serviceLoader = new TestServiceLoader();
	
	private static final Map<Class<?>, Object> instancesMap = new ConcurrentHashMap<>();
	
	private static final Object loadLock = new Object();

	public static <T> T loadByCache(Class<T> interfaceType) {
		Object instances = instancesMap.get(interfaceType);
		if (instances != null) {
			return (T) instances;
		}
		synchronized (loadLock) {
			instances = instancesMap.get(interfaceType);
			if (instances == null) {
				instances = serviceLoader.load(interfaceType);
				if (instances == null) {
					return (T) instances;
				}
				instancesMap.put(interfaceType, instances);
			}
		}
		return (T) instances;
	}
}
