package org.hum.pumpkin.test.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	static ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-rpc.xml");
	
	public static void main(String[] args) {
		System.out.println("success");
	}
}