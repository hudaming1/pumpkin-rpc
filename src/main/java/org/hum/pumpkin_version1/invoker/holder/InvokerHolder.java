package org.hum.pumpkin_version1.invoker.holder;

import org.hum.pumpkin_version1.invoker.Invoker;
import org.hum.pumpkin_version1.transport.ServiceKey;

public interface InvokerHolder {

	public void create(Class<?> classType, Object instances);

	public Invoker getInvoker(ServiceKey serviceKey);
}
