package org.hum.pumpkin.transport;

import org.hum.pumpkin.exchange.Request;
import org.hum.pumpkin.exchange.Response;

public interface Client {
	
	Response send(Request invocation);
	
	void close();
}
