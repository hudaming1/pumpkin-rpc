package org.hum.pumpkin.test.simple;

import org.hum.pumpkin.config.ReferenceConfig;
import org.hum.pumpkin.test._service.IHelloService;
import org.hum.pumpkin.test._service.IUserService;

public class ClientTest {

	public static void main(String[] args) throws InterruptedException {
		ReferenceConfig<IHelloService> referenceBean = new ReferenceConfig<>(IHelloService.class);
		referenceBean.setProtocol("pumpkin");
		referenceBean.setUrl("127.0.0.1", 9080);

		IHelloService helloService = referenceBean.get();

		ReferenceConfig<IUserService> referenceBean2 = new ReferenceConfig<>(IUserService.class);
		referenceBean2.setProtocol("pumpkin");
		referenceBean2.setUrl("127.0.0.1", 9080);
		IUserService userService = referenceBean2.get();

//		Thread.sleep(2000);

		for (int i = 0; i < 10; i++) {
			System.out.println(helloService.sayHello("huming" + i));
			System.out.println(userService.getNameById(i));
		}
	}
}
