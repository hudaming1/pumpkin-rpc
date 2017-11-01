package org.hum.pumpkin.proxy;

import org.hum.pumpkin.config.ServerConfig;

public interface RpcProxy {

	Object proxy(ServerConfig config);
}
