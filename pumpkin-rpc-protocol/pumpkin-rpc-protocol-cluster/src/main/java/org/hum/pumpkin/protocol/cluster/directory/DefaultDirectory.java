package org.hum.pumpkin.protocol.cluster.directory;

import java.util.ArrayList;
import java.util.List;

import org.hum.pumpkin.common.serviceloader.ExtensionLoader;
import org.hum.pumpkin.registry.EndPoint;
import org.hum.pumpkin.registry.Registry;
import org.hum.pumpkin.registry.RegistryConfig;

public class DefaultDirectory<T> implements Directory<T> {

	private Class<T> interfaceType;
	private String registryString;
	private List<Registry> registries = new ArrayList<>();
	private static final ExtensionLoader<Registry> REGISTRY_EXTENSIONL_LOADER = ExtensionLoader.getExtensionLoader(Registry.class);

	public DefaultDirectory() {
	}

	public DefaultDirectory(Class<T> interfaceType, String registryString) {
		this.interfaceType = interfaceType;
		this.registryString = registryString;
		this.init();
	}

	private void init() {
		for (String _registryString : registryString.split(";")) {
			RegistryConfig registryConfig = new RegistryConfig(_registryString);
			Registry registry = REGISTRY_EXTENSIONL_LOADER.get(registryConfig.getName());
			registry.connect(new EndPoint(registryConfig.getAddress(), registryConfig.getPort()));
			registries.add(registry);
		}
	}

	@Override
	public Class<T> getInterface() {
		return interfaceType;
	}

	@Override
	public List<EndPoint> list(String key) {
		List<EndPoint> nodes = new ArrayList<>();
		for (Registry registry : registries) {
			nodes.addAll(registry.discover(interfaceType.getName()));
		}
		return nodes;
	}
}
