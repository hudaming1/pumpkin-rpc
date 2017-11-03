package org.hum.pumpkin_version1.serviceloader;

public interface ServiceLoadable<T> {

	public Class<T> getType();
}
