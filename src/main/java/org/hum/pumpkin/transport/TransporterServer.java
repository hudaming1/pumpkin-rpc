package org.hum.pumpkin.transport;

public interface TransporterServer {

	// 简化：先用一个port参数
	void open();
	
	void close();
}
