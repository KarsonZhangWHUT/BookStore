package com.karson.dao.impl;

import com.karson.bean.User;
import com.karson.dao.BaseDao;
import com.karson.dao.UserDao;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

	@Override
	public User getUserByNameAndPassword(User user) {
		String sql = "select * from bs_user where username=? and password=?";
		return getBean(sql,user.getUsername(),user.getPassword());
	}

	@Override
	public boolean registUser(User user) {
		String sql = "insert into bs_user(username,password,email)values(?,?,?)";
		return update(sql,user.getUsername(),user.getPassword(),user.getEmail()) > 0;
	}
}
