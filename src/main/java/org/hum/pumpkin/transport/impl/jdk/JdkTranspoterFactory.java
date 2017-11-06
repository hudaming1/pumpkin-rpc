package org.hum.pumpkin.transport.impl.jdk;

import java.io.IOException;

import org.hum.pumpkin.common.RpcException;
import org.hum.pumpkin.common.UrlConstant;
import org.hum.pumpkin.protocol.URL;
import org.hum.pumpkin.transport.TransporterServer;
import org.hum.pumpkin.transport.ServerHandler;
import org.hum.pumpkin.transport.Transporter;
import org.hum.pumpkin.transport.factory.AbstractTransporterFactory;

public class JdkTranspoterFactory extends AbstractTransporterFactory {

	@Override
	protected Transporter doCreate(URL url) {
		try {
			// TODO 长短连接应该在每次请求动态控制（客户端配置优先；如果客户端没有配置，则采用服务端配置）
			Boolean isKeepAlive = url.getBoolean(UrlConstant.IS_KEEP_ALIVE);
			if (isKeepAlive != null && isKeepAlive) {
				return new JdkKeeyAliveTranporter(url);
			} else {
				return new JdkShortTransporter(url);
			}
		} catch (IOException e) {
			throw new RpcException("create jdk transporter[" + url.getHost() + ":" + url.getPort() + "] error", e);
		}
	}

	@Override
	public TransporterServer createServer(URL url, ServerHandler serverHandler) {
		return new JdkTransporterServer(url, serverHandler);
	}
}
