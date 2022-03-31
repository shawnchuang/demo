package com.book.management.demo.service.bo;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.book.management.demo.bean.BaseInput;
import com.book.management.demo.general.config.SystemConfig;

public class QueryBooksInput extends BaseInput {

	private String type;
	private int page;
	private int size;
	private String ISBN;
	private String name;
	private String author;
	private String publisher;
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
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	@Override
	public boolean validate() {
		boolean result = true;
		Logger logger = LogManager.getLogger(QueryBooksInput.class);
		
		if(StringUtils.isBlank(this.type)) {
			logger.error("[queryBooks] 驗證 input 參數錯誤，type 為空");
			result = false;
		}
		if(SystemConfig.QUERY_TYPE_CONDITION.equals(this.type) 
				&& StringUtils.isBlank(ISBN) 
				&& StringUtils.isBlank(name) 
				&& StringUtils.isBlank(author) 
				&& StringUtils.isBlank(publisher)) {
			logger.error("[queryBooks] 驗證 input 參數錯誤，所有查詢條件為空");
			result = false;
		}
		
		return result;
	}
}
