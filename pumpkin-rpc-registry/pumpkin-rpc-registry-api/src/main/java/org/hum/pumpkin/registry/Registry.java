package org.hum.pumpkin.registry;

import java.util.List;

public interface Registry {
	
	public void registry(Class<?> clazz, EndPoint endPoint);

	public List<EndPoint> discover(String path);
}
