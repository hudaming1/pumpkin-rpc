package org.hum.pumpkin.exporter;

import org.hum.pumpkin.exchange.Exchanger;
import org.hum.pumpkin.exchange.server.ExchangeServer;
import org.hum.pumpkin.protocol.URL;
import org.hum.pumpkin.serviceloader.ServiceLoaderHolder;

public class DefaultExporter<T> implements Exporter<T>{

	private final Exchanger exchanger = ServiceLoaderHolder.loadByCache(Exchanger.class);
	private Class<T> interfaceType;
	private T ref;
	private URL url;
	private ExchangeServer exchangeServer;
	
	public DefaultExporter(Class<T> classType, T instances, URL url) {
		this.interfaceType = classType;
		this.ref = instances;
		this.url = url;
		this.exchangeServer = exchanger.bind(url);
	}

	@Override
	public void unexport() {
		exchangeServer.close();
	}
}
