package org.hum.pumpkin.registry;

import java.util.List;

import org.hum.pumpkin.common.serviceloader.support.SPI;

@SPI
public interface Registry {
	
	public void connect(String address, int port);

	public void registry(Class<?> clazz, String ip, int port);

	public List<String> discover(String path);
}
