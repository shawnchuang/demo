package com.book.management.demo.dao.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.book.management.demo.bean.BooksBean;

@Table
@Entity
public class Books {
	@Id
	private String ISBN;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private String author;
	@Column
	private String translator;
	@Column(nullable=false)
	private String publisher;
	@Column(name="publication_date", nullable=false)
	private Timestamp publicationDate;
	@Column(nullable=false)
	private BigDecimal price;
	@Column(name="create_user")
	private String createUser;
	@Column(name="create_date", nullable=false)
	private Timestamp createDate;
	@Column(name="update_user")
	private String updateUser;
	@Column(name="update_date")
	private Timestamp updateDate;
	
	public Books() {
	}
	public Books(BooksBean booksBean) {
		this.ISBN = booksBean.getISBN();
		this.author = booksBean.getAuthor();
		this.name = booksBean.getName();
		this.price = booksBean.getPrice();
		this.publicationDate = new Timestamp(booksBean.getPublicationDate().getTime());
		this.publisher = booksBean.getPublisher();
		this.translator = booksBean.getTranslator();
		this.createDate = new Timestamp(new Date().getTime());
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
	public Timestamp getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(Timestamp publicationDate) {
		this.publicationDate = publicationDate;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
}
