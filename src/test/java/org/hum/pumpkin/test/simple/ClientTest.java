package org.hum.pumpkin.test.simple;

import org.hum.pumpkin.client.ReferneceConfig;
import org.hum.pumpkin.test._service.IHelloService;

public class ClientTest {

	public static void main(String[] args) {
		ReferneceConfig<IHelloService> referenceBean = new ReferneceConfig<>(IHelloService.class);
		IHelloService helloService = referenceBean.get();
		System.out.println(helloService.sayHello("huming"));
	}
}
