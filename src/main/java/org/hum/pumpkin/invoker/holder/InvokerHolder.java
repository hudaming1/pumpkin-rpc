package org.hum.pumpkin.invoker.holder;

import org.hum.pumpkin.invoker.Invoker;
import org.hum.pumpkin.transport.ServiceKey;

public interface InvokerHolder {

	public void create(Class<?> classType, Object instances);

	public Invoker getInvoker(ServiceKey serviceKey);
}
