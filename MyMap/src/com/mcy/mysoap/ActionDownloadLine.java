package com.mcy.mysoap;

import com.mcy.mobile.soap.SoapAction;

public class ActionDownloadLine extends SoapAction {

	public ActionDownloadLine(String userid) {
		super("DownloadLineRw");
		setParam("strUserid", userid);
	}

	@Override
	public void setParams() {

	}

}
