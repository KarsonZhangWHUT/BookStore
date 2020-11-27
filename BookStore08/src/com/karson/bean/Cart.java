package com.karson.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Cart implements Serializable {
	// 保存的所有购物项
	private Map<Integer, CartItem> items = new LinkedHashMap<Integer, CartItem>();
	// 所有书的总金额

	public int getTotalCount() {

		// 获取购物车中所有的商品
		List<CartItem> list = getAllItems();
		int count = 0;
		for (CartItem cartItem : list) {
			count += cartItem.getCount();
		}
		return count;

	}

	public double getTotalMoney() {
		// 获取购物车中所有的商品的总价
		List<CartItem> list = getAllItems();
		BigDecimal money = new BigDecimal("0.0");
		for (CartItem cartItem : list) {
			BigDecimal totalPrice = new BigDecimal(cartItem.getTotalPrice() + "");
			money = money.add(totalPrice);
		}
		return money.doubleValue();

	}

	/**
	 * 返回所有的购物项
	 * 
	 * @return
	 */
	public List<CartItem> getAllItems() {
		// 返回map中的所有值
		Collection<CartItem> values = items.values();
		return new ArrayList<CartItem>(values);
	}

	// 定义操作购物车的其他方法
	public void addBook2Cart(Book book) {
//		CartItem cartItem = new CartItem(book,1,book.getPrice());
//		items.put(book.getId(), book);

		CartItem item = items.get(book.getId());
		if (item == null) {
			// 没有当前的购物项，是第一次添加
			CartItem cartItem = new CartItem();
			cartItem.setBook(book);
			cartItem.setCount(1);
			// 将项目加入购物车
			items.put(book.getId(), cartItem);
		} else {
			item.setCount(item.getCount() + 1);
		}

	}

	/**
	 * 从购物车中删除某一项
	 */
	public void deleteItem(String bookId) {
		int id = Integer.parseInt(bookId);
		items.remove(id);
	}

	/**
	 * 修改图书的数量
	 * 
	 * @param bookId 要修改的图书条目
	 * @param count  修改后的数量
	 */
	public void updateCount(String bookId, String count) {
		int id = 0;
		try {
			id = Integer.parseInt(bookId);
		} catch (Exception e) {
		}
		int c = 1;
		try {
			c = Integer.parseInt(count);
		} catch (Exception e) {
		}

		CartItem cartItem = items.get(id);
		if (cartItem != null)
			cartItem.setCount(c);
	}

	/**
	 * 清空购物车
	 */
	public void clear() {
		items.clear();
	}

}
