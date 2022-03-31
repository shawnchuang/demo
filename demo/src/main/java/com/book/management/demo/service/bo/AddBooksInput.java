package com.book.management.demo.service.bo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.book.management.demo.bean.BaseInput;
import com.book.management.demo.bean.BooksBean;

public class AddBooksInput extends BaseInput {

	private List<BooksBean> books;
	
	@Override
	public boolean validate() {
		boolean result = true;
		Logger logger = LogManager.getLogger(AddBooksInput.class);
		
		if(CollectionUtils.isEmpty(books)) {
			logger.error("[addBooks] 驗證 input 參數錯誤，books 為空");
			result = false;
		}
		
		return result;
	}
	public List<BooksBean> getBooks() {
		return books;
	}
	public void setBooks(List<BooksBean> books) {
		this.books = books;
	}
}
