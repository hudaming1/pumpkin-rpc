package org.hum.pumpkin.transport;

import org.hum.pumpkin.common.exception.PumpkinException;
import org.hum.pumpkin.protocol.url.URL;

public abstract class AbstractServer implements Server {

	private ServerHandler serverHandler;
	private volatile boolean isRun;
	private URL url;
	
	public AbstractServer(URL url, ServerHandler serverHandler) {
		this.url = url;
		this.serverHandler = serverHandler;
	}
	
	@Override
	public void open() {
		// 1.初始化Server对象
		initServer(url);
		
		// 2.启动：打开端口
		openPort();
		
		// 3.状态 - 已运行
		isRun = true;
		
		// 4.等待客户端连接
		Connector connector = acceptConnection();
	}
	
	public abstract void initServer(URL url) throws PumpkinException;
	
	public abstract void openPort();
	
	public abstract Connector acceptConnection();
	
	@Override
	public void close() {
		if (!isRun) {
			return ;
		}
		doClose();
	}
	
	public abstract void doClose();
	
	public static class Connector {
		private String host;
		private String port;
		public Connector(String host, String port) {
			super();
			this.host = host;
			this.port = port;
		}
		public String getHost() {
			return host;
		}
		public String getPort() {
			return port;
		}
	}
}
