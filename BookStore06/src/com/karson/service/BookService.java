package com.karson.service;

import java.util.List;

import com.karson.bean.Book;
import com.karson.bean.Page;

public interface BookService {
	boolean add(Book book);

	boolean update(Book book);

	boolean delete(Book book);

	Book getOne(Book book);

	List<Book> getAll();

	Page<Book> getPage(String pageNo, String pageSize);

	Page<Book> getPageByPrice(String pageNo, String pageSize, String minPrice, String maxPrice);

}
