package org.hum.pumpkin.test.registry;

import org.hum.pumpkin.config.ReferenceConfig;
import org.hum.pumpkin.test._service.IHelloService;
import org.hum.pumpkin.test._service.IUserService;

public class ClientTest {

	public static void main(String[] args) throws InterruptedException {
		ReferenceConfig<IHelloService> referenceBean = new ReferenceConfig<>(IHelloService.class);
		referenceBean.setProtocol("pumpkin");
		referenceBean.setRegistry("zookeeper://172.16.219.129:2181");
		IHelloService helloService = referenceBean.get();

		ReferenceConfig<IUserService> referenceBean2 = new ReferenceConfig<>(IUserService.class);
		referenceBean2.setProtocol("pumpkin");
		referenceBean2.setRegistry("zookeeper://172.16.219.129:2181");
		IUserService userService = referenceBean2.get();

		Thread.sleep(2000);

		for (int i = 0; i < 10; i++) {
			System.out.println(helloService.sayHello("huming" + i));
			System.out.println(userService.getNameById(i));
		}
	}
}
