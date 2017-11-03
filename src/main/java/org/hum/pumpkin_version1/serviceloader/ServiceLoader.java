package org.hum.pumpkin_version1.serviceloader;

public interface ServiceLoader {

	public <T> T load(Class<T> interfaceType);
}
