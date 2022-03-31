package com.book.management.demo.controller.vo;

public class ResultView {

	private String errorCode;
	private String errorMsg;
	private boolean success;
	
	public ResultView() {
	}
	public ResultView(boolean success, String errorCode, String errorMsg) {
		this.success = success;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	public ResultView(boolean success) {
		this.success = success;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
