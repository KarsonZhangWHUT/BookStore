package com.karson.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.karson.utils.JDBCUtils;

@SuppressWarnings("unchecked")
public abstract class BaseDao<T> {
	private QueryRunner runner = new QueryRunner();
	//获取实际的type
	private Class<T> type;
	
	public BaseDao(){
		//获取父类的泛型
		Type genericSuperclass = this.getClass().getGenericSuperclass();
		ParameterizedType superclass = (ParameterizedType) genericSuperclass;
		type = (Class<T>) superclass.getActualTypeArguments()[0];
	}
	
	
	public T getBean(String sql,Object...params) {
		Connection connection = JDBCUtils.getConnection();
		T query = null;
		try {
			query = runner.query(connection, sql, new BeanHandler<T>(type), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.releaseConnection(connection);
		}
		return query;
	}
	
	
	public List<T> getBeanList(String sql,Object...params){
		Connection connection = JDBCUtils.getConnection();
		List<T> query = null;
		try {
			query = runner.query(connection, sql, new BeanListHandler<T>(type), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.releaseConnection(connection);
		}
		
		return query;
	}
	
	
	public int update(String sql, Object... params) {
		int count=0;
		Connection connection = JDBCUtils.getConnection();
		try {
			count = runner.update(connection, sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.releaseConnection(connection);
		}
		return count;
	}
}
