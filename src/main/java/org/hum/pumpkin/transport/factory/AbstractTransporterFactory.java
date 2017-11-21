package org.hum.pumpkin.transport.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hum.pumpkin.protocol.url.URL;
import org.hum.pumpkin.transport.Client;

public abstract class AbstractTransporterFactory implements TransporterFactory {

	private final static Map<String, Client> clientCache = new ConcurrentHashMap<>();

	@Override
	public Client createClient(URL url) {
		String tranportKey = url.getProtocol() + "://" + url.getHost() + ":" + url.getPort() + "/" + url.getPath();
		Client client = clientCache.get(tranportKey);
		if (client != null) {
			return client;
		}
		// Transporter一定不要重复创建，其内部对象通过JVM GC不一定能够回收，因此一定要保证线程安全
		synchronized (clientCache) {
			client = clientCache.get(tranportKey);
			if (client != null) {
				return client;
			}
			client = doCreate(url);
			clientCache.put(tranportKey, client);
		}
		return client;
	}
	
	protected abstract Client doCreate(URL url);
}
