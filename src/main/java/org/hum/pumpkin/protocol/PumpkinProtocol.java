package org.hum.pumpkin.protocol;

import org.hum.pumpkin.invoker.Invoker;
import org.hum.pumpkin.invoker.cluster.FailoverClusterInvoker;
import org.hum.pumpkin.invoker.direct.DirectInvoker;

public class PumpkinProtocol implements Protocol {

	@Override
	public <T> Invoker<T> refer(Class<T> classType, URL url) {
		
		// TODO 这里扩展性不好，如果客户端增加一种协议，这里需要再改
		if (ProtocolEnum.Registry.getName().equals(url.getProtocol())) {
			return new FailoverClusterInvoker<>();
		} else if (ProtocolEnum.Pumpkin.getName().equals(url.getProtocol())) {
			return new DirectInvoker<>(classType, url);
		} else {
			
		}
		return null;
	}
}
