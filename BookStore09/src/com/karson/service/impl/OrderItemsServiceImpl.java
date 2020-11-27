package com.karson.service.impl;

import java.util.List;

import com.karson.bean.OrderItem;
import com.karson.dao.OrderItemDao;
import com.karson.dao.impl.OrderItemDaoImpl;
import com.karson.service.OrderItemService;

public class OrderItemsServiceImpl implements OrderItemService {

	OrderItemDao itemDao = new OrderItemDaoImpl();

	@Override
	public void saveItem(List<OrderItem> orderItem) {
//		for (OrderItem orderItem2 : orderItem) {
//			itemDao.saveOrderItem(orderItem2);
//		}
//		for (OrderItem orderItem2 : orderItem) {
//			System.out.println("OrderItemsServiceImpl"+orderItem2);
//		}
		long id = Thread.currentThread().getId();
		System.out.println("orderItemDao中的线程号" + id);

		itemDao.saveBatch(orderItem);

	}

	@Override
	public List<OrderItem> getOrderItems(String orderId) {
		List<OrderItem> orderItems = itemDao.getOrderItems(orderId);
		return orderItems;
	}

}
