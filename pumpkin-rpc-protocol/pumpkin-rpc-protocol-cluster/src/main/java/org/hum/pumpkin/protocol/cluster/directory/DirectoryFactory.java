package org.hum.pumpkin.protocol.cluster.directory;

import org.hum.pumpkin.common.serviceloader.support.SPI;

@SPI("default")
public interface DirectoryFactory<T> {

	public Directory<T> createDirectory(Class<T> interfaceType, String registryString);
}
