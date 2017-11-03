package org.hum.pumpkin.test.simple;

import org.hum.pumpkin.test._service.IHelloService;
import org.hum.pumpkin_version1.client.RpcClient;
import org.hum.pumpkin_version1.config.client.ReferenceBean;
import org.hum.pumpkin_version1.config.client.ReferenceConfig;

public class ClientTest {

	public static void main(String[] args) {
		ReferenceConfig referenceConfig = new ReferenceConfig();
		RpcClient client = new RpcClient(referenceConfig);
		IHelloService helloService = client.proxy(new ReferenceBean<IHelloService>(IHelloService.class).buildUrl("127.0.0.1", 9080));
		System.out.println(helloService.sayHello("huming"));
	}
}
