package org.hum.pumpkin.invoker;

import org.hum.pumpkin.transport.bean.RpcInvocation;
import org.hum.pumpkin.transport.bean.RpcResult;

public interface Invoker {

	RpcResult invoke(RpcInvocation invocation);
}
