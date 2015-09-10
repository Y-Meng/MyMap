package com.mcy.mysoap;

import com.mcy.mobile.soap.SoapAction;

public class ActionUploadPath extends SoapAction {

	public ActionUploadPath(String result) {
		super("UpLoadPathData");
		setParam("strResult", result);
	}

	@Override
	public void setParams() {
		// TODO Auto-generated method stub

	}

}
