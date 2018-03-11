package org.hum.pumpkin.transport.event;

import org.hum.pumpkin.transport.server.ServerHandler;

public interface ServerEventHandler {

	// 打开端口
	void fireOpen(ServerHandler serverHandler);
	
	// 关闭服务
	void fireClose(ServerHandler serverHandler);
	
	// 接收数据 
	void fireReceive(ServerHandler serverHandler);
}
