package org.hum.pumpkin.protocol.cluster.directory;

import java.util.List;

import org.hum.pumpkin.protocol.invoker.Invoker;
import org.hum.pumpkin.protocol.invoker.RpcInvocation;

public interface Directory<T> {

    Class<T> getInterface();
    
    List<Invoker<T>> list(RpcInvocation invocation);
}

