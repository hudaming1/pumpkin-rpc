package org.hum.pumpkin.protocol.invoker;

import org.hum.pumpkin.common.serviceloader.support.Adaptive;
import org.hum.pumpkin.common.serviceloader.support.SPI;
import org.hum.pumpkin.common.url.URL;
import org.hum.pumpkin.common.url.URLConstant;

@SPI
public interface InvokerFactory<T> {

	@Adaptive(value = URLConstant.PROTOCOL)
	public Invoker<T> create(Class<T> classType, URL url);
}
