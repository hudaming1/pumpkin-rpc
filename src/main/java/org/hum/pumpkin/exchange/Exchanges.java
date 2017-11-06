package org.hum.pumpkin.exchange;

public class Exchanges {

	public static ExchangeServer bind(int port) {
		return new ExchangeServer();
	}
	
	public static ExchangeClient connect(String url, int port) {
		return new ExchangeClient();
	}
}
