package org.hum.pumpkin.exchange.client;

import org.hum.pumpkin.exchange.Request;
import org.hum.pumpkin.exchange.Response;

public interface ExchangeClient {

	Response send(Request request);
}
