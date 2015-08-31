package com.mcy.mobile.core;

public interface Login {

	public  Object login(String user, String pwd);

	public  void handleLoginResult(Object result);

}
