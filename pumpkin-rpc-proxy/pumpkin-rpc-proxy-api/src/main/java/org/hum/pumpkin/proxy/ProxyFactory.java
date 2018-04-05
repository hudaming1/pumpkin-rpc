package org.hum.pumpkin.proxy;

import org.hum.pumpkin.common.serviceloader.support.SPI;
import org.hum.pumpkin.protocol.invoker.Invoker;


@SPI("jdk")
public interface ProxyFactory {

	<T> T getProxy(Invoker<T> invoker);

    Object invoke(Object instances, String methodName, Class<?>[] paramTypes, Object[] params) ;
}
