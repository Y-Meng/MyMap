package com.mcy.mobile.http;

public interface RequestResultCallback {
	/**
	 * ���������еĻص��ӿ�
	 * @author MCY
	 */
	public void onSuccess(Object o);	
	public void onFail(Exception e);	
}
