package org.hum.pumpkin.test.simple;

import org.hum.pumpkin.client.RpcClient;
import org.hum.pumpkin.config.ClientConfig;
import org.hum.pumpkin.test._service.IHelloService;

public class ClientTest {

	public static void main(String[] args) {
		RpcClient client = new RpcClient();
		IHelloService helloService = client.proxy(new ClientConfig<IHelloService>(IHelloService.class).buildUrl("127.0.0.1", 9080));
		System.out.println(helloService.sayHello("huming"));
	}
}
