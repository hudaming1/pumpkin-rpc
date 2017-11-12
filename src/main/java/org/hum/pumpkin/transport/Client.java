package org.hum.pumpkin.transport;

import org.hum.pumpkin.protocol.URL;
import org.hum.pumpkin.transport.message.Message;
import org.hum.pumpkin.transport.message.MessageBack;

public interface Client {
	
	MessageBack send(Message invocation);
	
	void close();
	
	URL getURL();
}
