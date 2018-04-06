package org.hum.pumpkin.registry;

import java.util.List;

import org.hum.pumpkin.common.serviceloader.support.SPI;

@SPI
public interface Registry {
	
	public void connect(EndPoint endPoint);

	public void registry(Class<?> clazz, EndPoint endPoint);

	public List<EndPoint> discover(String path);
}
