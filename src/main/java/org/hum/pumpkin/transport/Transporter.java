package org.hum.pumpkin.transport;

import org.hum.pumpkin.exchange.Request;
import org.hum.pumpkin.exchange.Response;

public interface Transporter {
	
	Response send(Request invocation);
	
	void close();
}
