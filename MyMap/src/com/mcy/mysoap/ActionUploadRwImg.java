package com.mcy.mysoap;

import com.mcy.mobile.soap.SoapAction;

public class ActionUploadRwImg extends SoapAction {

	public ActionUploadRwImg(String rwid,String name,String var) {
		super("UpLoadRwImage");
		setParam("strRwid", rwid);
		setParam("strImgName", name);
		setParam("strImgVal", var);
	}

	@Override
	public void setParams() {
		// TODO Auto-generated method stub

	}

}
