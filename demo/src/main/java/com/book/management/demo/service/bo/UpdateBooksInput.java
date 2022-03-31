package com.book.management.demo.service.bo;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.book.management.demo.bean.BaseInput;
import com.book.management.demo.bean.BooksBean;

public class UpdateBooksInput extends BaseInput {

	private BooksBean book;

	public BooksBean getBook() {
		return book;
	}

	public void setBook(BooksBean book) {
		this.book = book;
	}

	@Override
	public boolean validate() {
		boolean result = true;
		Logger logger = LogManager.getLogger(UpdateBooksInput.class);
		
		if(book == null) {
			logger.error("[updateBooks] 驗證 input 參數錯誤，book 為空");
			result = false;
		}
		if(StringUtils.isBlank(book.getISBN())) {
			logger.error("[updateBooks] 驗證 input 參數錯誤，book ISBN 為空");
			result = false;
		}
		
		return result;
	}
}
