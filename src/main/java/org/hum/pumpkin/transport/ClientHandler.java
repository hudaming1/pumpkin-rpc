package org.hum.pumpkin.transport;

import org.hum.pumpkin.transport.message.Message;
import org.hum.pumpkin.transport.message.MessageBack;

public interface ClientHandler {

	MessageBack send(Message request);
}
