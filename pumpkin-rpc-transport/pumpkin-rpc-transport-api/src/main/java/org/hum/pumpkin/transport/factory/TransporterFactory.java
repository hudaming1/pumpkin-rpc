package org.hum.pumpkin.transport.factory;

import org.hum.pumpkin.transport.Server;
import org.hum.pumpkin.transport.ServerHandler;
import org.hum.pumpkin.common.url.URL;
import org.hum.pumpkin.transport.Client;

public interface TransporterFactory {

	Client createClient(URL url);
	
	Server createServer(URL url, ServerHandler serverHandler);
}
