package org.hum.pumpkin.test.spring;

import org.hum.pumpkin.test._service.IUserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClientTest {

	static ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-rpc-client.xml");
	
	public static void main(String[] args) throws InterruptedException {
		IUserService bean = applicationContext.getBean(org.hum.pumpkin.test._service.IUserService.class);
		Thread.sleep(2000);
		System.out.println(bean);
		System.out.println(bean.getNameById(1));
	}
}
