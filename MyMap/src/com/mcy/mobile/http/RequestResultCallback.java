package com.mcy.mobile.http;

public interface RequestResultCallback {
	/**
	 * 网络请求中的回调接口
	 * @author MCY
	 */
	public void onSuccess(Object o);	
	public void onFail(Exception e);	
}
