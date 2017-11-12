package org.hum.pumpkin.protocol;

import org.hum.pumpkin.common.UrlConstant;
import org.hum.pumpkin.protocol.exporter.DefaultExporter;
import org.hum.pumpkin.protocol.exporter.Exporter;
import org.hum.pumpkin.protocol.invoker.Invoker;
import org.hum.pumpkin.protocol.invoker.direct.DirectInvoker;

/**
 * 南瓜协议：
 * 	1.基于TCP层传输通信
 * 	2.采用Netty传输
 * 	3.保持单一长连接
 * 	4.Kryo序列化方式
 */
public class PumpkinProtocol implements Protocol {

	@Override
	public <T> Exporter<T> export(Class<T> classType, T instances, URL url) {
		return new DefaultExporter<T>(classType, instances, url);
	}

	@Override
	public <T> Invoker<T> refer(Class<T> classType, URL url) {
		url.buildParam(UrlConstant.IS_KEEP_ALIVE, true);
		url.buildParam(UrlConstant.IS_SHARE_CONNECTION, true);
		return new DirectInvoker<>(classType, url);
	}
}
