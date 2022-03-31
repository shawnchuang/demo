package com.book.management.demo.service.bo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.book.management.demo.bean.BaseInput;

public class DeleteBooksInput extends BaseInput {
	private List<String> ISBNs;	// ISBN 清單

	public List<String> getISBNs() {
		return ISBNs;
	}

	public void setISBNs(List<String> iSBNs) {
		ISBNs = iSBNs;
	}
	@Override
	public boolean validate() {
		boolean result = true;
		Logger logger = LogManager.getLogger(DeleteBooksInput.class);
		
		if(CollectionUtils.isEmpty(ISBNs)) {
			logger.error("[deleteBooks] 驗證 input 參數錯誤，ISBNs 為空");
			result = false;
		}
		
		return result;
	}
}
