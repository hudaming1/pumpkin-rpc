package org.hum.pumpkin.registry.zookeeper;

import org.hum.pumpkin.registry.AbstractRegistryFactory;
import org.hum.pumpkin.registry.EndPoint;
import org.hum.pumpkin.registry.Registry;

public class ZookeeperRegistryFactory extends AbstractRegistryFactory {

	@Override
	public Registry doGetOrCreate(EndPoint endPoint) {
		return new ZookeeperRegistry(endPoint);
	}
}
