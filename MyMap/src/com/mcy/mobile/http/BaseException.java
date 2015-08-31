package com.mcy.mobile.http;

public class BaseException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code ;
	public int getCode() {
		return code;
	}

	public BaseException(int code){
		super();
		this.code = code;
	}
	
	public BaseException(int  code,String msg){
		super(msg);
		this.code = code;
	}
	
	public BaseException(int code,String msg,Throwable throwable){
		super(msg,throwable);
		this.code = code;
	}
	
	public BaseException(int code,Throwable throwable){
		super(throwable);
		this.code = code;
	}
}
