package com.mcy.mysoap;

import com.mcy.mobile.soap.SoapAction;

public class ActionUploadQxImg extends SoapAction {

	public ActionUploadQxImg(String id,String name,String var,String zt) {
		super("UpLoadQxImage");
		setParam("strQxID", id);
		setParam("strImgName", name);
		setParam("strImgVal", var);
		setParam("strQxzt", zt);
	}

	@Override
	public void setParams() {
		// TODO Auto-generated method stub

	}

}
