package com.karson.test;

import org.junit.Test;

import com.karson.bean.Book;
import com.karson.bean.Cart;
import com.karson.bean.User;
import com.karson.service.BookService;
import com.karson.service.OrderService;
import com.karson.service.impl.BookServiceImpl;
import com.karson.service.impl.OrderServiceImpl;

public class OrderServiceTest {
	BookService bookService = new BookServiceImpl();
	OrderService orderService = new OrderServiceImpl();
	
	@Test
	public void test() {
		Book book = new Book();
		book.setId(52);
		Book one = bookService.getOne(book);
		Cart cart = new Cart();
		cart.addBook2Cart(one);
		cart.addBook2Cart(one);
		String orderId = orderService.checkout(cart, new User(1,"","",""));
		System.out.println(orderId);
		
		
	}
	
	
}
