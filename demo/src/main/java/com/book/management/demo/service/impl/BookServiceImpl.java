package com.book.management.demo.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.book.management.demo.bean.BooksBean;
import com.book.management.demo.dao.BooksDao;
import com.book.management.demo.dao.entity.Books;
import com.book.management.demo.general.config.ErrorCode;
import com.book.management.demo.general.config.SystemConfig;
import com.book.management.demo.service.BookService;
import com.book.management.demo.service.bo.AddBooksInput;
import com.book.management.demo.service.bo.AddBooksOutput;
import com.book.management.demo.service.bo.DeleteBooksInput;
import com.book.management.demo.service.bo.DeleteBooksOutput;
import com.book.management.demo.service.bo.QueryBooksInput;
import com.book.management.demo.service.bo.QueryBooksOutput;
import com.book.management.demo.service.bo.UpdateBooksInput;
import com.book.management.demo.service.bo.UpdateBooksOutput;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BooksDao booksDao;
	private Logger logger = LogManager.getLogger(BookServiceImpl.class);
	
	@Override
	@Transactional
	public AddBooksOutput addBooks(AddBooksInput input) {
		logger.info("[addBooks] start");
		AddBooksOutput result = new AddBooksOutput();
		boolean goNext = true;
		ErrorCode errorCode = null;
		
		// 驗證 input
		if(!input.validate()) {
			goNext = false;
			errorCode = ErrorCode.ADD_BOOKS_001;
		}
		
		// 驗證 isbn
		List<Books> books = new ArrayList<>();
		if(goNext) {
			logger.info("[addBooks] validate ISBN");
			List<String> ids = new ArrayList<>();
			for(BooksBean book : input.getBooks()) {
				Books entity = new Books(book);
				books.add(entity);
				ids.add(book.getISBN());
			}
			
			List<Books> findAllById = booksDao.findAllById(ids);
			
			if(!CollectionUtils.isEmpty(findAllById)) {
				goNext = false;
				errorCode = ErrorCode.ADD_BOOKS_002;
				logger.error("[addBooks] isbn is exist: " + ids);
			}
		}
		
		// 寫入 DB
		if(goNext) {
			logger.info("[addBooks] insert DB");
			booksDao.saveAllAndFlush((Iterable<Books>)books);
		}
		
		// 整理回傳
		if(goNext) {
			result.setSuccess(true);
		}else {
			result.setErrorCode(errorCode.getCode());
			result.setErrorMsg(errorCode.getMsg());
		}
		
		logger.info("[addBooks] end");
		return result;
	}

	@Override
	public DeleteBooksOutput deleteBooks(DeleteBooksInput input) {
		logger.info("[deleteBooks] start");
		DeleteBooksOutput result = new DeleteBooksOutput();
		boolean goNext = true;
		ErrorCode errorCode = null;
		
		// 驗證 input
		if(!input.validate()) {
			goNext = false;
			errorCode = ErrorCode.DELETE_BOOKS_001;
		}
		
		// 刪除資料
		if(goNext) {
			logger.info("[deleteBooks] delete DB data");
			booksDao.deleteAllById(input.getISBNs());
		}
		
		// 整理回傳
		if(goNext) {
			result.setSuccess(true);
		}else {
			result.setErrorCode(errorCode.getCode());
			result.setErrorMsg(errorCode.getMsg());
		}
		
		logger.info("[deleteBooks] end");
		return result;
	}

	@Override
	public UpdateBooksOutput updateBooks(UpdateBooksInput input) {
		logger.info("[updateBooks] start");
		UpdateBooksOutput result = new UpdateBooksOutput();
		boolean goNext = true;
		ErrorCode errorCode = null;
		
		// 驗證 input
		if(!input.validate()) {
			goNext = false;
			errorCode = ErrorCode.UPDATE_BOOKS_001;
		}
		
		// ISBN 查詢資料
		Books books = null;
		if(goNext) {
			logger.info("[updateBooks] query books by isbn");
			BooksBean book = input.getBook();
			List<String> ids = new ArrayList<>();
			ids.add(book.getISBN());
			List<Books> findById = booksDao.findAllById(ids);
			
			if(!CollectionUtils.isEmpty(findById)) {
				books = findById.get(0);
				if(StringUtils.isNoneBlank(book.getAuthor())) {
					books.setAuthor(book.getAuthor());
				}
				if(StringUtils.isNoneBlank(book.getName())) {
					books.setName(book.getName());
				}
				if(book.getPrice() != null) {
					books.setPrice(book.getPrice());
				}
				if(book.getPublicationDate() != null) {
					books.setPublicationDate(new Timestamp(book.getPublicationDate().getTime()));
				}
				if(StringUtils.isNoneBlank(book.getPublisher())) {
					books.setPublisher(book.getPublisher());
				}
				if(StringUtils.isNoneBlank(book.getTranslator())) {
					books.setTranslator(book.getTranslator());
				}
				books.setUpdateDate(new Timestamp(new Date().getTime()));
			}else {
				goNext = false;
				errorCode = ErrorCode.UPDATE_BOOKS_002;
				logger.error(String.format("[updateBooks] isbn: %s is not exist", book.getISBN()));
			}
		}
		
		// 寫入 DB
		if(goNext) {
			logger.info("[updateBooks] update DB");
			booksDao.save(books);
		}
		
		// 整理回傳
		if(goNext) {
			result.setSuccess(true);
		}else {
			result.setErrorCode(errorCode.getCode());
			result.setErrorMsg(errorCode.getMsg());
		}
		
		logger.info("[updateBooks] end");
		return result;
	}

	@Override
	public QueryBooksOutput queryBooks(QueryBooksInput input) {
		logger.info("[queryBooks] start");
		QueryBooksOutput result = new QueryBooksOutput();
		boolean goNext = true;
		ErrorCode errorCode = null;
		
		// 驗證 input
		if(!input.validate()) {
			goNext = false;
			errorCode = ErrorCode.QUERY_BOOKS_001;
		}
		
		// 查詢書籍
		List<BooksBean> bookList = new ArrayList<>();
		if(goNext) {
			logger.info("[queryBooks] query type = " + input.getType());
			Pageable page = PageRequest.of(input.getPage() - 1, input.getSize());
			List<Books> entityList = null;
			// 全部查詢
			if(SystemConfig.QUERY_TYPE_ALL.equals(input.getType())) {
				Page<Books> findAll = booksDao.findAll(page);
				entityList = findAll.getContent();
			}
			
			// 條件查詢
			if(SystemConfig.QUERY_TYPE_CONDITION.equals(input.getType())) {
				logger.info(String.format("[queryBooks] condition: author=%s, ISBN=%s, name=%s, publisher=%s", input.getAuthor(), input.getISBN(), input.getName(), input.getPublisher()));
				Books entity = new Books();
				if(StringUtils.isNoneBlank(input.getAuthor())) {
					entity.setAuthor(input.getAuthor());
				}
				if(StringUtils.isNoneBlank(input.getISBN())) {
					entity.setISBN(input.getISBN());
				}
				if(StringUtils.isNoneBlank(input.getName())) {
					entity.setName(input.getName());
				}
				if(StringUtils.isNoneBlank(input.getPublisher())) {
					entity.setPublisher(input.getPublisher());
				}
				ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name", ExampleMatcher.GenericPropertyMatcher.of(StringMatcher.CONTAINING, true));
				
				Example<Books> example = Example.of(entity, matcher);
				Page<Books> findAll = booksDao.findAll(example, page);
				entityList = findAll.getContent();
			}
			
			// 物件轉換
			if(!CollectionUtils.isEmpty(entityList)) {
				for(Books book : entityList) {
					BooksBean booksBean = new BooksBean(book);
					bookList.add(booksBean);
				}
			}
		}
		
		// 整理回傳
		if(goNext) {
			result.setSuccess(true);
			result.setBooks(bookList);
		}else {
			result.setErrorCode(errorCode.getCode());
			result.setErrorMsg(errorCode.getMsg());
		}
		
		logger.info("[queryBooks] end");
		return result;
	}

}
