package com.karson.dao;

import java.util.List;

import com.karson.bean.OrderItem;

/**
 * 操作订单项的dao
 * 
 * @author Karson
 *
 */
public interface OrderItemDao {
	/**
	 * 获取某个订单的所有订单项
	 * 
	 * @return
	 */
	List<OrderItem> getOrderItems(String orderId);

	/**
	 * 保存某个订单项
	 * 
	 * @return
	 */
	int saveOrderItem(OrderItem item);
	
	/**
	 * 执行批量保存
	 * 
	 * @param params
	 * @return
	 */
	public void saveBatch(List<OrderItem> params);
}
