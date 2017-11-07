package org.hum.pumpkin.transport;

import org.hum.pumpkin.exchange.Request;
import org.hum.pumpkin.exchange.Response;

public interface ClientHandler {

	Response send(Request request);
}
