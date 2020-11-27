package com.karson.test;

import org.junit.Test;

import com.karson.bean.User;
import com.karson.dao.impl.UserDaoImpl;

public class UserDaoTest {
	@Test
	public void getUserByNameAndPassword() {
		UserDaoImpl userDao = new UserDaoImpl();
		User user = new User("Charon","123456");
		User user2 = userDao.getUserByNameAndPassword(user);
		System.out.println(user2);
	}
	
	@Test
	public void registUser() {
		UserDaoImpl userDao = new UserDaoImpl();
		User user = new User("Lemon","Lemon@qq.com","123456");
		boolean b = userDao.registUser(user);
		System.out.println(b);
	}
	
}
