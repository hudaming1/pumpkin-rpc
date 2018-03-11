package org.hum.pumpkin.transport.server;

import org.hum.pumpkin.common.exception.PumpkinException;
import org.hum.pumpkin.common.url.URL;
import org.hum.pumpkin.transport.event.ServerEventHandler;

public abstract class AbstractServer implements Server {

	private ServerHandler serverHandler;
	private volatile boolean isRun;
	private URL url;
	private ServerEventHandler serverEvent;
	
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
		
		// 3.发布事件
		serverEvent.fireOpen(serverHandler);
		
		// 4.状态 - 已运行
		isRun = true;
	}
	
	public abstract void initServer(URL url) throws PumpkinException;
	
	public abstract void openPort();
	
	@Override
	public void close() {
		if (!isRun) {
			return ;
		}
		doClose();
	}
	
	public abstract void doClose();
}
