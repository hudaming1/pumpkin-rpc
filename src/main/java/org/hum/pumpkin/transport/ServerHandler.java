package org.hum.pumpkin.transport;

import org.hum.pumpkin.transport.message.Message;
import org.hum.pumpkin.transport.message.MessageBack;

public interface ServerHandler {

	boolean acceptConnection(String host, int port, Message message);
	
	MessageBack received(Message message);
}
