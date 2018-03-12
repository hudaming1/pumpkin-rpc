package org.hum.pumpkin.protocol.cluster.directory;

import java.util.List;

import org.hum.pumpkin.protocol.invoker.Invoker;
import org.hum.pumpkin.protocol.invoker.RpcInvocation;

public class SimpleDirectory<T> implements Directory<T> {

	@Override
	public Class<T> getInterface() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Invoker<T>> list(RpcInvocation invocation) {
		// TODO Auto-generated method stub
		return null;
	}
}
