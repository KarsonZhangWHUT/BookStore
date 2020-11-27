package com.karson.test;

import java.util.List;

import org.junit.Test;

import com.karson.bean.Book;
import com.karson.bean.Page;
import com.karson.dao.BookDao;
import com.karson.dao.impl.BookDaoImpl;
import com.karson.service.BookService;
import com.karson.service.impl.BookServiceImpl;

public class BookDaoTest {

	BookDao bd = new BookDaoImpl();

	@Test
	public void demo1() {
		List<Book> list = bd.getAllBook();
		for (Book b : list)
			System.out.println(b);
	}

	@Test
	public void TestAddBook() {
		boolean b = bd.addBook(new Book(null, "书", "作者", 12.8, 200, 200, null));
		System.out.println(b);
	}

	@Test
	public void TestDelBook() {
		boolean b = bd.delBook(new Book(2, null, null, 0, 0, 0, null));
		System.out.println(b);
	}

	@Test
	public void TestGetBook() {
		Book book = bd.getBook(new Book(3, null, null, 0, 0, 0, null));
		System.out.println(book);
	}

//	public Book(Integer id, String title, String author, double price, int sales, int stock, String imgPath) {
//		super();
//		this.id = id;
//		this.title = title;
//		this.author = author;
//		this.price = price;
//		this.sales = sales;
//		this.stock = stock;
//		if (imgPath != null)
//			this.imgPath = imgPath;
//	}
	@Test
	public void TestUpdate() {
//		String sql = "update bs_book set title=?,author=?,price=?,sales=?,stock=?,img_path=? where id=?";
//		return update(sql, book.getTitle(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(),
//				book.getImgPath(), book.getId()) > 0;
		boolean b = bd.update(new Book(6, "haha", "hahahah", 55, 2, 2, null));
		System.out.println(b);
	}

	@Test
	public void test6() {
		BookService bs = new BookServiceImpl();
		Page<Book> page = bs.getPage("0", "4");
		System.out.println(page.getPageData());
		System.out.println(page.getPageNo());
		System.out.println(page.getTotalCount());
		System.out.println(page.getTotalPage());
		System.out.println(page.isHasNext());
		System.out.println(page.isHasPre());

	}
	
	@Test
	public void test7() {
		
		BookService bs = new BookServiceImpl();
		Page<Book> page = bs.getPageByPrice("1", "4", "20", "100");
		System.out.println(page.getPageData());
		System.out.println(page.getPageNo());
		System.out.println(page.getTotalCount());
		System.out.println(page.getTotalPage());
		System.out.println(page.isHasNext());
		System.out.println(page.isHasPre());

	}
}
