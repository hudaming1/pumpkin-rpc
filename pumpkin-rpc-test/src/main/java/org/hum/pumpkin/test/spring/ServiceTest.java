package org.hum.pumpkin.test.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceTest {

	static ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-rpc-service.xml");
	
	public static void main(String[] args) {
		System.out.println("success");
	}
}