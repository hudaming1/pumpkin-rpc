package org.hum.pumpkin.transport.server;

import org.hum.pumpkin.transport.message.Message;
import org.hum.pumpkin.transport.message.MessageBack;

public interface ServerHandler {

	MessageBack received(String host, Message message);
}
