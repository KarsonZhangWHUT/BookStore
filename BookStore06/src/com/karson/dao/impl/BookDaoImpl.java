package com.karson.dao.impl;

import java.util.List;

import com.karson.bean.Book;
import com.karson.dao.BaseDao;
import com.karson.dao.BookDao;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {

	@Override
	public List<Book> getAllBook() {
		String sql = "select id,title,author,price,sales,stock,img_path imgPath from bs_book";
		return getBeanList(sql);
	}

	@Override
	public boolean addBook(Book book) {
		String sql = "insert into bs_book(title,author,price,sales,stock,img_path)values(?,?,?,?,?,?)";
		return update(sql, book.getTitle(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(),
				book.getImgPath()) > 0;
	}

	@Override
	public boolean delBook(Book book) {
		String sql = "delete from bs_book where id=?";
		return update(sql, book.getId()) > 0;
	}

	@Override
	public boolean update(Book book) {
		String sql = "update bs_book set title=?,author=?,price=?,sales=?,stock=?,img_path=? where id=?";
		return update(sql, book.getTitle(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(),
				book.getImgPath(), book.getId()) > 0;
	}

	@Override
	public Book getBook(Book book) {
		String sql = "select id,title,author,price,sales,stock,img_path imgPath from bs_book where id=?";
		return getBean(sql, book.getId());
	}

	@Override
	public List<Book> getPageList(int index, int size) {
		String sql = "select id,title,author,price,sales,stock,img_path imgPath from bs_book limit ?,?";
		return getBeanList(sql, index, size);
	}

	@Override
	public int getTotalCount() {
		String sql = "select count(*) from bs_book";
		Object singleValue = getSingleValue(sql);
		int totalCount = 0;
		try {
			totalCount = Integer.parseInt(singleValue.toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return totalCount;
	}

	@Override
	public List<Book> getPageByPrice(int index, int size, double minPrice, double maxPrice) {
		String sql = "select id,title,author,price,sales,stock,img_path imgPath from bs_book where price between ? and ? limit ?,?";
		return getBeanList(sql, minPrice, maxPrice, index, size);
	}

	@Override
	public int getTotalCountByPrice(double minPrice, double maxPrice) {

		String sql = "select count(*) from bs_book where price between ? and ?";

		Object object = getSingleValue(sql, minPrice, maxPrice);

		int totalCountByPrice = 0;
		try {
			totalCountByPrice = Integer.parseInt(object.toString());
		} catch (Exception e) {
		}

		return totalCountByPrice;
	}

}
