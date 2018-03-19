package org.hum.pumpkin.test.spring;

import org.hum.pumpkin.test._service.IHelloService;
import org.hum.pumpkin.test._service.IUserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClientTest {

	static ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-rpc-client.xml");
	
	public static void main(String[] args) throws InterruptedException {
		IUserService bean = applicationContext.getBean(org.hum.pumpkin.test._service.IUserService.class);
		IHelloService bean2 = applicationContext.getBean(org.hum.pumpkin.test._service.IHelloService.class);
		Thread.sleep(2000);
		System.out.println(bean.getNameById(1));
		System.out.println(bean.getNameById(2));
		System.out.println(bean.getNameById(3));
		
		System.out.println(bean2.sayHello("huming"));
	}
}
