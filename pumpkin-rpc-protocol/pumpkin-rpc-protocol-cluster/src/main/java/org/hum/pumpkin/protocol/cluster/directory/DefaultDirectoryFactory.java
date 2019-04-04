package org.hum.pumpkin.protocol.cluster.directory;

public class DefaultDirectoryFactory<T> implements DirectoryFactory<T> {

	@Override
	public Directory<T> createDirectory(Class<T> interfaceType, String registryString) {
		return new DefaultDirectory<>(interfaceType, registryString);
	}
}
