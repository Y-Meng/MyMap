package com.mcy.mobile.http;


public class RequestException extends BaseException{
	private static final long serialVersionUID = 1L;
	 
	public final  static int SERVER_CLOSED_EXCEPTION = 0x01;
	 
	public final  static int CONNECT_EXCEPTION = 0X02;
	
 
	public final  static int SOCKET_EXCEPTION = 0x03;
	
	 
	public final  static int BIND_EXCEPTION = 0x04;
	
	 
	public final  static int CONNECT_TIMEOUT_EXCEPTION = 0x05;
	
	 
	public final  static int UNSUPPORTED_ENCODEING_EXCEPTION = 0x06;
	
	 
	public final  static int  SOCKET_TIMEOUT_EXCEPTION = 0x06;
	
	 
	public final  static int  CLIENT_PROTOL_EXCEPTION = 0x07;
	 
	public final  static int IO_EXCEPTION = 0x08;
	
	
	public RequestException(int code, String msg, Throwable throwable) {
		super(code, msg, throwable);
		// TODO Auto-generated constructor stub
	}
	
	public RequestException(int code, Throwable throwable) {
		super(code, throwable);
		// TODO Auto-generated constructor stub
	}

	public RequestException(int code, String msg) {
		super(code, msg);
		// TODO Auto-generated constructor stub
	}
	
	public RequestException(int code) {
		super(code);
		// TODO Auto-generated constructor stub
	}
}
