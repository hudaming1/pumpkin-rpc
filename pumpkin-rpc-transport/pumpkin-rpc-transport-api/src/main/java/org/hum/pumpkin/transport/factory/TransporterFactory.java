package org.hum.pumpkin.transport.factory;

import org.hum.pumpkin.common.url.URL;
import org.hum.pumpkin.transport.client.Client;
import org.hum.pumpkin.transport.server.Server;
import org.hum.pumpkin.transport.server.ServerHandler;

public interface TransporterFactory {

	Client createClient(URL url);
	
	Server createServer(URL url, ServerHandler serverHandler);
}
