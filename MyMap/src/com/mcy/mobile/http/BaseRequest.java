
package com.mcy.mobile.http;

import java.io.Serializable;
import org.apache.http.client.methods.HttpUriRequest;


public class BaseRequest  implements   Runnable, Serializable {
	//static HttpClient httpClient = null;
	protected HttpUriRequest request = null;
	
	private static final long serialVersionUID = 1L;
	protected ParseHandler handler = null;
	protected String url = null;
	/**
	 * default is 5 ,to set .
	 */
	protected int connectTimeout = 5;
	/**
	 * default is 5 ,to set .
	 */
	protected int readTimeout = 5;
	protected RequestResultCallback requestCallback = null;
	
	
	@Override
	public void run() {
		
	}
	
	protected void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}
	
	protected void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}
	
	public HttpUriRequest getRequest() {
		return request;
	}
}
