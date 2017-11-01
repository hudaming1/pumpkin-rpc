package org.hum.pumpkin.serviceloader;

public interface ServiceLoader {

	public <T> T load(Class<T> interfaceType);
}
