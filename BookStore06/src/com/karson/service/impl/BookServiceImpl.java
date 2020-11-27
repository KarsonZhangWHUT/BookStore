package com.karson.service.impl;

import java.util.List;

import com.karson.bean.Book;
import com.karson.bean.Page;
import com.karson.dao.BookDao;
import com.karson.dao.impl.BookDaoImpl;
import com.karson.service.BookService;

public class BookServiceImpl implements BookService {
	private BookDao bd = new BookDaoImpl();

	@Override
	public boolean add(Book book) {
		return bd.addBook(book);
	}

	@Override
	public boolean update(Book book) {
		return bd.update(book);
	}

	@Override
	public boolean delete(Book book) {
		return bd.delBook(book);
	}

	@Override
	public Book getOne(Book book) {
		return bd.getBook(book);
	}

	@Override
	public List<Book> getAll() {
		return bd.getAllBook();
	}

	@Override
	public Page<Book> getPage(String pageNo, String pageSize) {
		// 1、将用户传入的数据先封装
		Page<Book> page = new Page<Book>();

		int pN = 1;
		int pS = page.getPageSize();
		try {
			pN = Integer.parseInt(pageNo);
			
		} catch (Exception e) {
//			e.printStackTrace();
		}
		
		try {
			pS = Integer.parseInt(pageSize);
		} catch (Exception e) {
			// TODO: handle exception
		}
		// 2.因为要使用totalCount，所以还需要查数据库
		// 先设置页面显示的条目的个数
		page.setPageSize(pS);
		// 再设置总记录数
		int totalCount = bd.getTotalCount();// 获取总记录数
		page.setTotalCount(totalCount);
		// 根据总记录数和页面显示的条目的个数获取总页码数，getTotalPage()
		page.setPageNo(pN);
		// 3.查询数据并封装
		List<Book> pageList = bd.getPageList(page.getIndex(), page.getPageSize());
		page.setPageData(pageList);

		return page;
	}

	@Override
	public Page<Book> getPageByPrice(String pageNo, String pageSize, String minPrice, String maxPrice) {

		double max = Double.MAX_VALUE;
		double min = 0.0;
		try {
			max = Double.parseDouble(maxPrice);
			min = Double.parseDouble(minPrice);
		} catch (Exception e) {
		}
		// 1、将用户传入的数据先封装
		Page<Book> page = new Page<Book>();

		int pN = 1;
		int pS = page.getPageSize();
		try {
			pN = Integer.parseInt(pageNo);
			pS = Integer.parseInt(pageSize);
		} catch (Exception e) {
//					e.printStackTrace();
		}

		// 按照价格区间获取总记录数
		int totalCountByPrice = bd.getTotalCountByPrice(min, max);

		page.setTotalCount(totalCountByPrice);
		page.setPageSize(pS);

		// 最后设置
		page.setPageNo(pN);

		// 3.查询相应数据
		List<Book> pageByPrice = bd.getPageByPrice(page.getIndex(), page.getPageSize(), min, max);

		// 4.封装进page对象
		page.setPageData(pageByPrice);
//		bd.getPageByPrice(pN, pS, min, max);

		return page;
	}

}
