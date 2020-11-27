package com.karson.dao;

import java.util.List;

import com.karson.bean.Book;

public interface BookDao {
	List<Book> getAllBook();

	boolean addBook(Book book);

	boolean delBook(Book book);

	boolean update(Book book);

	Book getBook(Book book);

	/**
	 * 分页查找图书的方法
	 * 
	 * @param index
	 * @param size
	 * @return
	 */
	List<Book> getPageList(int index, int size);
	
	List<Book> getPageByPrice(int index, int size,double minPrice,double maxPrice);

	int getTotalCount();
	
	int getTotalCountByPrice(double minPrice,double maxPrice);

}
