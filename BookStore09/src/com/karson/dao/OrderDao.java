package com.karson.dao;

import java.util.List;

import com.karson.bean.Order;

/**
 * 操作订单的Dao
 * @author Karson
 *
 */
public interface OrderDao {
	/**
	 * 保存订单
	 * @param order
	 * @return
	 */
	int saveOrder(Order order);
	/**
	 * 修改订单状态
	 * @param order
	 * @return
	 */
	int updateStatus(Order order);
	/**
	 * 获取所有订单(管理员)
	 * @return
	 */
	List<Order> getOrderList();
	/**
	 * 获取某个用户的所有订单
	 * @param userId
	 * @return
	 */
	List<Order> getOrderByUserId(Integer userId);
	
}
