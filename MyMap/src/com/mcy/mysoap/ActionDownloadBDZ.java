package com.mcy.mysoap;

import com.mcy.mobile.soap.SoapAction;

public class ActionDownloadBDZ extends SoapAction {

	public ActionDownloadBDZ(String userid) {
		super("DownloadBdzRw");
		setParam("strUserid", userid);
	}

	@Override
	public void setParams() {
		
	}

}
