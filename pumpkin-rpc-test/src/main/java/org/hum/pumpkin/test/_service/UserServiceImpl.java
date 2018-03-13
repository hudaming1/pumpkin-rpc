package org.hum.pumpkin.test._service;

public class UserServiceImpl implements IUserService {

	@Override
	public String getNameById(Integer id) {
		System.out.println("userService receive request");
		return "user" + id;
	}
}
