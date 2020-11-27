package com.karson.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 获取和释放数据库连接
 * @author Karson
 *
 */
public class JDBCUtils {
	private static DataSource dataSource = new ComboPooledDataSource("webDataSource");
	
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void releaseConnection(Connection connection) {
		try {
			if(connection!=null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
