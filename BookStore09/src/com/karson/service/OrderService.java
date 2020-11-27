package com.karson.service;

import java.util.List;

import com.karson.bean.Cart;
import com.karson.bean.Order;
import com.karson.bean.User;

public interface OrderService {
	/**
	 * 结账操作
	 * 
	 * @param cart
	 * @return 订单号
	 */
	String checkout(Cart cart, User user);

	/**
	 * 修改订单状态
	 * 
	 * @param orderId
	 * @param status
	 */
	void updateStatus(String orderId, String status);

	/**
	 * 获取所有订单，管理员使用
	 * 
	 * @return
	 */
	List<Order> getAllOrder();

	/**
	 * 获取某个用户的所有订单
	 * 
	 * @param userId
	 * @return
	 */
	List<Order> getMyOrder(Integer userId);

}
