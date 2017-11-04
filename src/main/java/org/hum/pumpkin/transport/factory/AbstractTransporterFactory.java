package org.hum.pumpkin.transport.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hum.pumpkin.transport.Transporter;
import org.hum.pumpkin.transport.config.TransporterConfig;

public abstract class AbstractTransporterFactory implements TransporterFactory {

	private final static Map<String, Transporter> transpoterCache = new ConcurrentHashMap<>();
	private final Object joinLock = new Object();

	@Override
	public Transporter create(TransporterConfig config) {
		String tranportKey = config.getAddress() + ":" + config.getPort();
		Transporter transporter = transpoterCache.get(tranportKey);
		if (transporter != null) {
			return transporter;
		}
		// Transporter一定不要重复创建，其内部对象通过JVM GC不一定能够回收，因此一定要保证线程安全
		synchronized (joinLock) {
			transporter = transpoterCache.get(tranportKey);
			if (transporter != null) {
				return transporter;
			}
			transporter = doCreate(config);
			transpoterCache.put(tranportKey, transporter);
		}
		return transporter;
	}
	
	protected abstract Transporter doCreate(TransporterConfig config);
}
