package com.book.management.demo.service;

import com.book.management.demo.service.bo.AddBooksInput;
import com.book.management.demo.service.bo.AddBooksOutput;
import com.book.management.demo.service.bo.DeleteBooksInput;
import com.book.management.demo.service.bo.DeleteBooksOutput;
import com.book.management.demo.service.bo.QueryBooksInput;
import com.book.management.demo.service.bo.QueryBooksOutput;
import com.book.management.demo.service.bo.UpdateBooksInput;
import com.book.management.demo.service.bo.UpdateBooksOutput;

public interface BookService {
	// 新增多本書籍
	public AddBooksOutput addBooks(AddBooksInput input);

	// 刪除多本書籍
	public DeleteBooksOutput deleteBooks(DeleteBooksInput input);
	
	// 更新書本資訊
	public UpdateBooksOutput updateBooks(UpdateBooksInput input);
	
	// 查詢書籍資訊
	public QueryBooksOutput queryBooks(QueryBooksInput input);
}
