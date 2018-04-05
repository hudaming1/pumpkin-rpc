package org.hum.pumpkin.service;

import org.hum.pumpkin.api.UserServiceApi;

public class UserExtServiceApi implements UserServiceApi {

	@Override
	public String sayHello(String name) {
		return "hi " + name;
	}
}
