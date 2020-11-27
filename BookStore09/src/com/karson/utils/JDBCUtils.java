package com.karson.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 获取和释放数据库连接
 * 
 * @author Karson
 *
 */
public class JDBCUtils {
	private static DataSource dataSource = new ComboPooledDataSource("webDataSource");
	private static Map<Long, Connection> conns = new HashMap<>();

	public static Connection getConnection() {
		long id = Thread.currentThread().getId();
		System.out.println("JDBCUtils中的线程号：" + id);
		// 获取当前线程的连接
		Connection connection = conns.get(Thread.currentThread().getId());
		if (connection == null) {
			try {
				connection = dataSource.getConnection();
				// 把连接保存在map中,当第一次要连接的时候，map中没有，就新建一个
				// 并保存到map中，以后任何地方只要拿当前线程号获取，都获取的时当前线程对应的连接
				conns.put(id, connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return connection;
	}

	public static void releaseConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
				conns.remove(Thread.currentThread().getId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
