package com.mcy.mysoap;

import com.mcy.mobile.soap.SoapAction;

public class ActionLogin extends SoapAction {

	private String mUser;
	private String mPwd;
	
	public ActionLogin(String user,String pwd) {
		super("GetLoginUser");
		mUser = user;
		mPwd = pwd;
	}

	@Override
	public void setParams() {
		setParam("strUid", mUser);
		setParam("strPwd", mPwd);
	}

}
