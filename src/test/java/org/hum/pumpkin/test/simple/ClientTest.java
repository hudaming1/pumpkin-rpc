package org.hum.pumpkin.test.simple;

import org.hum.pumpkin.config.ReferenceConfig;
import org.hum.pumpkin.protocol.ProtocolEnum;
import org.hum.pumpkin.test._service.IHelloService;

public class ClientTest {

	public static void main(String[] args) {
		ReferenceConfig<IHelloService> referenceBean = new ReferenceConfig<>(IHelloService.class);
		referenceBean.setUrl("127.0.0.1", 9080);
		referenceBean.setProtocol(ProtocolEnum.Direct.getName());
		IHelloService helloService = referenceBean.get();
		System.out.println(helloService.sayHello("huming1"));
		System.out.println(helloService.sayHello("huming2"));
		System.out.println(helloService.sayHello("huming3"));
	}
}
