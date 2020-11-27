package com.karson.service;

import java.util.List;

import com.karson.bean.OrderItem;

public interface OrderItemService {
	/**
	 * 保存订单项
	 * 
	 * @param orderItem
	 */
	void saveItem(List<OrderItem> orderItem);

	/**
	 * 获取订单的所有订单项
	 * 
	 * @param OrderId
	 * @return
	 */
	List<OrderItem> getOrderItems(String orderId);

}
