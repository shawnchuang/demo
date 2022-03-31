package com.book.management.demo.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.book.management.demo.dao.entity.Books;

public class BooksBean {
	private String ISBN;
	private String name;
	private String author;
	private String translator;
	private String publisher;
	private Date publicationDate;
	private BigDecimal price;

	public BooksBean() {
	}
	public BooksBean(Books book) {
		this.author = book.getAuthor();
		this.ISBN = book.getISBN();
		this.name = book.getName();
		this.price = book.getPrice();
		this.publicationDate = book.getPublicationDate();
		this.publisher = book.getPublisher();
		this.translator = book.getTranslator();
	}
	
	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTranslator() {
		return translator;
	}

	public void setTranslator(String translator) {
		this.translator = translator;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
