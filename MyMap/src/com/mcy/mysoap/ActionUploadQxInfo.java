package com.mcy.mysoap;

import com.mcy.mobile.soap.SoapAction;

public class ActionUploadQxInfo extends SoapAction {

	public ActionUploadQxInfo(String var) {
		super("UpLoadQxInfo");
		setParam("strQxValue", var);
	}

	@Override
	public void setParams() {
		// TODO Auto-generated method stub

	}

}
