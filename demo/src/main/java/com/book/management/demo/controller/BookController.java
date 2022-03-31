package com.book.management.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.management.demo.bean.BooksBean;
import com.book.management.demo.controller.vo.AddBookVin;
import com.book.management.demo.controller.vo.AddBookVout;
import com.book.management.demo.controller.vo.BooksVo;
import com.book.management.demo.controller.vo.DeleteBookVout;
import com.book.management.demo.controller.vo.QueryBooksVout;
import com.book.management.demo.controller.vo.ResultView;
import com.book.management.demo.controller.vo.UpdateBookVin;
import com.book.management.demo.controller.vo.UpdateBookVout;
import com.book.management.demo.general.config.ErrorCode;
import com.book.management.demo.general.config.SystemConfig;
import com.book.management.demo.general.util.DateUtil;
import com.book.management.demo.service.BookService;
import com.book.management.demo.service.bo.AddBooksInput;
import com.book.management.demo.service.bo.AddBooksOutput;
import com.book.management.demo.service.bo.DeleteBooksInput;
import com.book.management.demo.service.bo.DeleteBooksOutput;
import com.book.management.demo.service.bo.QueryBooksInput;
import com.book.management.demo.service.bo.QueryBooksOutput;
import com.book.management.demo.service.bo.UpdateBooksInput;
import com.book.management.demo.service.bo.UpdateBooksOutput;

@RestController
@RequestMapping("/bookManagement/books")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@PostMapping()
	public AddBookVout addBook(@RequestBody AddBookVin vin) {
		AddBookVout result = new AddBookVout();
		try {
			AddBooksInput input = new AddBooksInput();
			BooksBean book = new BooksBean();
			book.setAuthor(vin.getAuthor());
			book.setISBN(vin.getIsbn());
			book.setName(vin.getName());
			book.setPrice(vin.getPrice());
			book.setPublicationDate(DateUtil.dateFormat(vin.getPublicationDate(), "yyyy-MM-dd"));
			book.setPublisher(vin.getPublisher());
			book.setTranslator(vin.getTranslator());
			
			List<BooksBean> books = new ArrayList<>();
			books.add(book);
			input.setBooks(books);
			
			AddBooksOutput output = bookService.addBooks(input);
			
			if(output.isSuccess()) {
				result.setResultStatus(new ResultView(true));
			}else {
				result.setResultStatus(new ResultView(false, output.getErrorCode(), output.getErrorMsg()));
			}
		}catch(Exception e) {
			e.printStackTrace();
			result.setResultStatus(new ResultView(false, ErrorCode.ADD_BOOKS_002.getCode(), ErrorCode.ADD_BOOKS_002.getMsg()));
		}
		return result;
	}
	
	@PatchMapping()
	public UpdateBookVout updateBook(@RequestBody UpdateBookVin vin) {
		UpdateBookVout result = new UpdateBookVout();
		try {
			UpdateBooksInput input = new UpdateBooksInput();
			BooksBean book = new BooksBean();
			book.setAuthor(vin.getAuthor());
			book.setISBN(vin.getISBN());
			book.setName(vin.getName());
			book.setPrice(vin.getPrice());
			book.setPublicationDate(vin.getPublicationDate());
			book.setPublisher(vin.getPublisher());
			book.setTranslator(vin.getTranslator());
			
			input.setBook(book);
			
			UpdateBooksOutput output = bookService.updateBooks(input);
			
			if(output.isSuccess()) {
				result.setResultStatus(new ResultView(true));
			}else {
				result.setResultStatus(new ResultView(false, output.getErrorCode(), output.getErrorMsg()));
			}
		}catch(Exception e) {
			result.setResultStatus(new ResultView(false, ErrorCode.UPDATE_BOOKS_003.getCode(), ErrorCode.UPDATE_BOOKS_003.getMsg()));
		}
		return result;
	}

	@DeleteMapping("/{isbn}")
	public DeleteBookVout deleteBook(@PathVariable("isbn") String isbn) {
		DeleteBookVout result = new DeleteBookVout();
		try {
			DeleteBooksInput input = new DeleteBooksInput();
			List<String> ISBNs = new ArrayList<>();
			ISBNs.add(isbn);
			input.setISBNs(ISBNs);
			
			DeleteBooksOutput output = bookService.deleteBooks(input);
			
			if(output.isSuccess()) {
				result.setResultStatus(new ResultView(true));
			}else {
				result.setResultStatus(new ResultView(false, output.getErrorCode(), output.getErrorMsg()));
			}
		}catch(Exception e) {
			result.setResultStatus(new ResultView(false, ErrorCode.DELETE_BOOKS_002.getCode(), ErrorCode.DELETE_BOOKS_002.getMsg()));
		}
		return result;
	}
	
	@GetMapping("/{page}/{count}")
	public QueryBooksVout queryBook(@PathVariable("page") Integer page, @PathVariable("count") Integer count, 
			@RequestParam(required = false) String author, @RequestParam(required = false) String name, @RequestParam(required = false) String publisher, @RequestParam(required = false) String isbn) {
		QueryBooksVout result = new QueryBooksVout();
		try {
			QueryBooksInput input = new QueryBooksInput();
			input.setPage(page != null ? page : 1);
			input.setSize(count != null ? count : SystemConfig.DEFAULT_SIZE);
			input.setAuthor(author);
			input.setISBN(isbn);
			input.setName(name);
			input.setPublisher(publisher);
			if(StringUtils.isNoneBlank(author) || StringUtils.isNoneBlank(isbn) || StringUtils.isNoneBlank(name) || StringUtils.isNoneBlank(publisher)) {
				input.setType(SystemConfig.QUERY_TYPE_CONDITION);
			}else {
				input.setType(SystemConfig.QUERY_TYPE_ALL);
			}
			
			QueryBooksOutput output = bookService.queryBooks(input);
			
			if(output.isSuccess()) {
				List<BooksBean> books = output.getBooks();
				List<BooksVo> booksVo = new ArrayList<>();
				for(BooksBean bookBean : books) {
					booksVo.add(new BooksVo(bookBean));
				}
				result.setBooks(booksVo);
				result.setResultStatus(new ResultView(true));
			}else {
				result.setResultStatus(new ResultView(false, output.getErrorCode(), output.getErrorMsg()));
			}
		}catch(Exception e) {
			result.setResultStatus(new ResultView(false, ErrorCode.QUERY_BOOKS_002.getCode(), ErrorCode.QUERY_BOOKS_002.getMsg()));
		}
		return result;
	}
}
