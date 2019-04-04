package org.hum.pumpkin.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractRegistryFactory implements RegistryFactory {

	private static final Map<EndPoint, Registry> REGISTRY_MAP = new ConcurrentHashMap<>();
	
	@Override
	public Registry getOrCreate(EndPoint endPoint) {
		// 确保一个[IP:Port]只创建一个注册中心对象
		Registry registry = REGISTRY_MAP.get(endPoint);
		if (registry == null) {
			REGISTRY_MAP.putIfAbsent(endPoint, doGetOrCreate(endPoint));
			registry = REGISTRY_MAP.get(endPoint);
		}
		return registry;
	}
	
	public abstract Registry doGetOrCreate(EndPoint endPoint);
}
