package org.hum.pumpkin.registry;

import org.hum.pumpkin.common.serviceloader.support.SPI;

@SPI
public interface RegistryFactory {

	public Registry getOrCreate(EndPoint endPoint);
}
