package org.hum.pumpkin.transport.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hum.pumpkin.protocol.URL;
import org.hum.pumpkin.transport.Transporter;

public abstract class AbstractTransporterFactory implements TransporterFactory {

	private final static Map<String, Transporter> transpoterCache = new ConcurrentHashMap<>();

	@Override
	public Transporter createTransporter(URL url) {
		String tranportKey = url.getProtocol() + "://" + url.getHost() + ":" + url.getPort() + "/" + url.getPath();
		Transporter transporter = transpoterCache.get(tranportKey);
		if (transporter != null) {
			return transporter;
		}
		// Transporter一定不要重复创建，其内部对象通过JVM GC不一定能够回收，因此一定要保证线程安全
		synchronized (transpoterCache) {
			transporter = transpoterCache.get(tranportKey);
			if (transporter != null) {
				return transporter;
			}
			transporter = doCreate(url);
			transpoterCache.put(tranportKey, transporter);
		}
		return transporter;
	}
	
	protected abstract Transporter doCreate(URL url);
}
