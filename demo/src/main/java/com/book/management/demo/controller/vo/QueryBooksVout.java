package com.book.management.demo.controller.vo;

import java.util.List;

public class QueryBooksVout extends BaseVout {

	private List<BooksVo> books;

	public List<BooksVo> getBooks() {
		return books;
	}
	public void setBooks(List<BooksVo> books) {
		this.books = books;
	} 
}
