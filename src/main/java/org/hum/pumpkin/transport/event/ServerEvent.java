package org.hum.pumpkin.transport.event;

import org.hum.pumpkin.transport.ServerHandler;

public interface ServerEvent {

	// 打开端口
	public void open(ServerHandler serverHandler);
	
	// 启动服务
	public void started(ServerHandler serverHandler);
	
	// 关闭服务
	public void close(ServerHandler serverHandler);
	
	// 销毁服务
	public void destroy(ServerHandler serverHandler);
}
