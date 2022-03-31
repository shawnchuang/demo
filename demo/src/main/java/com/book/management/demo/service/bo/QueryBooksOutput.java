package com.book.management.demo.service.bo;

import java.util.List;

import com.book.management.demo.bean.BooksBean;
import com.book.management.demo.bean.ResultBean;

public class QueryBooksOutput extends ResultBean {

	private List<BooksBean> books;

	public List<BooksBean> getBooks() {
		return books;
	}
	public void setBooks(List<BooksBean> books) {
		this.books = books;
	}
}
