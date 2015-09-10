package com.mcy.mysoap;

import com.mcy.mobile.soap.SoapAction;

public class ActionDownloadQXSH extends SoapAction {

	public ActionDownloadQXSH(String userid) {
		super("DownloadQxshRw");
		setParam("strUserid", userid);
	}

	@Override
	public void setParams() {

	}

}
