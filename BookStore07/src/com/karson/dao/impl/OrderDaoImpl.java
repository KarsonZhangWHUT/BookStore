package com.karson.dao.impl;

import java.util.List;

import com.karson.bean.Order;
import com.karson.dao.BaseDao;
import com.karson.dao.OrderDao;

public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {
	@Override
	public int saveOrder(Order order) {
		String sql = "insert into bs_order(order_id,create_date,total_money,status,user_id)values(?,?,?,?,?)";
		return update(sql, order.getOrderId(), order.getCreateDate(), order.getTotalMoney(), order.getStatus(),
				order.getUserId());
	}

	@Override
	public int updateStatus(Order order) {
		String sql = "update bs_order set status=? where order_id=?";
		return update(sql, order.getStatus(), order.getOrderId());
	}

	@Override
	public List<Order> getOrderList() {
		String sql = "select order_id orderId,create_date createDate,total_money totalMoney,status status,user_id userId from bs_order";
		return getBeanList(sql);
	}

	@Override
	public List<Order> getOrderByUserId(Integer userId) {
		String sql = "select order_id orderId,create_date createDate,total_money totalMoney,status status,user_id userId from bs_order where user_id=?";
		return getBeanList(sql, userId);

	}

}
