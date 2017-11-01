package org.hum.pumpkin.serviceloader;

public class ServiceLoaderHolder {
	
	private static ServiceLoader serviceLoader;

	public static Object load(Class<?> interfaceType) {
		return serviceLoader.load(interfaceType);
	}
}
