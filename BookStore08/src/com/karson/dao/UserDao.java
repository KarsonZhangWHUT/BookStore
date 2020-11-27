package com.karson.dao;

import com.karson.bean.User;

public interface UserDao{
	/**
	 * 按用户名和密码查询详细信息
	 * @param user
	 * @return
	 */
	User getUserByNameAndPassword(User user);
	
	
	/**
	 * 注册，保存用户
	 * @param user
	 * @return
	 */
	boolean registUser(User user);
}
