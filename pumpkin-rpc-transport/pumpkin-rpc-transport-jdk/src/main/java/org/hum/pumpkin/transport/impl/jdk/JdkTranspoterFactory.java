package org.hum.pumpkin.transport.impl.jdk;

import java.io.IOException;

import org.hum.pumpkin.common.exception.RpcException;
import org.hum.pumpkin.common.url.URL;
import org.hum.pumpkin.common.url.URLConstant;
import org.hum.pumpkin.transport.client.Client;
import org.hum.pumpkin.transport.factory.AbstractTransporterFactory;
import org.hum.pumpkin.transport.server.Server;
import org.hum.pumpkin.transport.server.ServerHandler;

public class JdkTranspoterFactory extends AbstractTransporterFactory {

	@Override
	protected Client doCreate(URL url) {
		try {
			// TODO 长短连接应该在每次请求动态控制（客户端配置优先；如果客户端没有配置，则采用服务端配置）
			Boolean isKeepAlive = url.getBoolean(URLConstant.IS_KEEP_ALIVE);
			if (isKeepAlive != null && isKeepAlive) {
				return new JdkKeepAliveClient(url);
			} else {
				return new JdkShortClient(url);
			}
		} catch (IOException e) {
			throw new RpcException("create jdk transporter[" + url.getHost() + ":" + url.getPort() + "] error", e);
		}
	}

	@Override
	public Server createServer(URL url, ServerHandler serverHandler) {
		return new JdkServer(url, serverHandler);
	}
}
