package org.hum.pumpkin.beanmap;

public interface InstancesMap {

	void put(String name, Object instances);
	
	Object get(String name);
}
