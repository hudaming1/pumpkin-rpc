package org.hum.pumpkin.serviceloader;

public interface ServiceLoadable<T> {

	public Class<T> getType();
}
