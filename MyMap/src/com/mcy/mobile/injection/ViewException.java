
package com.mcy.mobile.injection;

import com.mcy.mobile.http.BaseException;


public class ViewException extends BaseException {
	private static final long serialVersionUID = 1L;
	private String strMsg = null;
	
	public ViewException(String strExce) {
		super(1);
		strMsg = strExce;
	}
	
	public void printStackTrace() {
		if(strMsg!=null)
			System.err.println(strMsg);
		
		super.printStackTrace();
	}
}
