package com.karson.test;

import java.sql.Connection;

import org.junit.Test;

import com.karson.utils.JDBCUtils;

public class JDBCUtilsTest {
	@Test
	public void getConnection() {
		Connection connection = JDBCUtils.getConnection();
		System.out.println(connection);
		JDBCUtils.releaseConnection(connection);
	}
}
