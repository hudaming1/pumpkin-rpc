package org.hum.pumpkin_version1.invoker;

import org.hum.pumpkin_version1.transport.bean.RpcInvocation;
import org.hum.pumpkin_version1.transport.bean.RpcResult;

public interface Invoker {

	RpcResult invoke(RpcInvocation invocation);
}
