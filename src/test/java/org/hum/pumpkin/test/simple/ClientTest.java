package org.hum.pumpkin.test.simple;

import org.hum.pumpkin.client.RpcClient;
import org.hum.pumpkin.config.client.ReferenceBean;
import org.hum.pumpkin.config.client.ReferenceConfig;
import org.hum.pumpkin.test._service.IHelloService;

public class ClientTest {

	public static void main(String[] args) {
		ReferenceConfig referenceConfig = new ReferenceConfig();
		RpcClient client = new RpcClient(referenceConfig);
		IHelloService helloService = client.proxy(new ReferenceBean<IHelloService>(IHelloService.class).buildUrl("127.0.0.1", 9080));
		System.out.println(helloService.sayHello("huming"));
	}
}
