package org.hum.pumpkin.registry;

import java.util.List;

public interface Registry {
	
	public void connect(String address, int port);

	public void registry(Class<?> clazz, String ip, int port);

	public List<String> discover(String path);
}
