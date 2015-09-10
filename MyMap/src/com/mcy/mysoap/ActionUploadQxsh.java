package com.mcy.mysoap;

import com.mcy.mobile.soap.SoapAction;

public class ActionUploadQxsh extends SoapAction {

	public ActionUploadQxsh(String var) {
		super("UploadQxshRw");
		setParam("strQxshResult", var);
	}

	@Override
	public void setParams() {
		// TODO Auto-generated method stub

	}

}
