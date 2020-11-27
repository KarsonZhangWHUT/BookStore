package com.karson.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.karson.bean.Book;
import com.karson.bean.Cart;
import com.karson.bean.CartItem;
import com.karson.bean.Order;
import com.karson.bean.OrderItem;
import com.karson.bean.User;
import com.karson.dao.OrderDao;
import com.karson.dao.impl.OrderDaoImpl;
import com.karson.service.BookService;
import com.karson.service.OrderItemService;
import com.karson.service.OrderService;

public class OrderServiceImpl implements OrderService {

	OrderDao orderDao = new OrderDaoImpl();
	OrderItemService orderItemService = new OrderItemsServiceImpl();
	BookService bookService = new BookServiceImpl();

	@Override
	public String checkout(Cart cart, User user) {
		// 结账操作，把购物车里的数据封装并保存
		// 1.封装订单对象
		Order order = new Order();
		order.setCreateDate(new Date());
		// orderId需要使用算法生成
		long millis = System.currentTimeMillis();
		String orderId = millis + "" + user.getId();
		order.setOrderId(orderId);
		order.setTotalMoney(cart.getTotalMoney());
		order.setStatus(0);
		order.setUserId(user.getId());

		// 2.封装订单项对象
		List<CartItem> allItems = cart.getAllItems();
		// 保存所有的订单项
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		for (CartItem cartItem : allItems) {
			OrderItem orderItem = new OrderItem(cartItem.getBook().getTitle(), cartItem.getCount(),
					cartItem.getBook().getPrice(), cartItem.getTotalPrice(), orderId);
			orderItems.add(orderItem);
		}

		// 3.保存订单
		orderDao.saveOrder(order);

		// 4.保存订单项
		orderItemService.saveItem(orderItems);

		// 5.修改相应库存
		for (CartItem cartItem : allItems) {
			// 获取详细信息,图书信息应该从数据库得到
			Book book = cartItem.getBook();
			Book one = bookService.getOne(book);
			// 修改库存和销量
			int count = cartItem.getCount();
			one.setStock(one.getStock() - count);
			one.setSales(one.getSales() + count);

			bookService.update(one);
		}

		// 6.清空购物车
		cart.clear();

		return orderId;
	}

	@Override
	public void updateStatus(String orderId, String status) {

		Order order = new Order();
		order.setOrderId(orderId);
		int parseInt = 0;
		try {
			parseInt = Integer.parseInt(status);
		} catch (Exception e) {
		}
		order.setStatus(parseInt);

		orderDao.updateStatus(order);

	}

	@Override
	public List<Order> getAllOrder() {
		return orderDao.getOrderList();
	}

	@Override
	public List<Order> getMyOrder(Integer userId) {
		return orderDao.getOrderByUserId(userId);
	}

}
