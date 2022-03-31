package com.book.management.demo.general.config;

public enum ErrorCode {

	ADD_BOOKS_001("1000001", "輸入參數錯誤"),
	ADD_BOOKS_002("1000002", "ISBN 已存在"),
	ADD_BOOKS_003("1000003", "系統繁忙中，請稍後在試"),

	DELETE_BOOKS_001("1001001", "輸入參數錯誤"),
	DELETE_BOOKS_002("1001002", "刪除失敗，查無 ISBN"),
	
	UPDATE_BOOKS_001("1002001", "輸入參數錯誤"),
	UPDATE_BOOKS_002("1002002", "查無 ISBN"),
	UPDATE_BOOKS_003("1002003", "系統繁忙中，請稍後在試"),

	QUERY_BOOKS_001("1003001", "輸入參數錯誤"),
	QUERY_BOOKS_002("1003002", "系統繁忙中，請稍後在試");
	
	private String code;
	private String msg;
	private ErrorCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
