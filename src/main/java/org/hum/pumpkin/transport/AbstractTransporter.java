package org.hum.pumpkin.transport;

import org.hum.pumpkin.beanmap.InstancesMap;
import org.hum.pumpkin.serviceloader.ServiceLoaderHolder;

public abstract class AbstractTransporter implements Transporter {
	
	private final InstancesMap instancesMap = ServiceLoaderHolder.load(InstancesMap.class);

	public void export(String name, Object instances) {
		instancesMap.put(name, instances);
	}
}
