package com.mcy.mysoap;

import com.mcy.mobile.soap.SoapAction;

public class ActionUploadRwInfo extends SoapAction {

	public ActionUploadRwInfo(String var) {
		super("UpLoadXsrwInfo");
		setParam("strXsResult", var);
	}

	@Override
	public void setParams() {
		// TODO Auto-generated method stub

	}

}
