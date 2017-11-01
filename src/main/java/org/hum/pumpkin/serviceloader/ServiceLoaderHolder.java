package org.hum.pumpkin.serviceloader;

public class ServiceLoaderHolder {
	
	private static ServiceLoader serviceLoader;

	public static <T> T load(Class<T> interfaceType) {
		return serviceLoader.load(interfaceType);
	}
}
