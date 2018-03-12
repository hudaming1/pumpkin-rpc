package org.hum.pumpkin.test.simple;

import org.hum.pumpkin.config.ReferenceConfig;
import org.hum.pumpkin.protocol.ProtocolEnum;
import org.hum.pumpkin.registry.RegistryConfig;
import org.hum.pumpkin.test._service.IHelloService;

public class ClientTest {

	public static void main(String[] args) throws InterruptedException {
		ReferenceConfig<IHelloService> referenceBean = new ReferenceConfig<>(IHelloService.class);
		 referenceBean.setUrl("127.0.0.1", 9080);
		 referenceBean.setProtocol(ProtocolEnum.Direct.getName());

//		referenceBean.setProtocol("registry");
//		referenceBean.setRegistryConfig(new RegistryConfig("zookeeper", "172.16.219.129", 2181));

		IHelloService helloService = referenceBean.get();

		// TODO 需要等待client连接成功后才能发起调用，这里需要优化
		Thread.sleep(2000);

		for (int i = 0; i < 10; i++) {
			System.out.println(helloService.sayHello("huming" + i));
		}
	}
}
