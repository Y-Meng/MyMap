package com.mcy.mobile.hybird;

import android.content.Context;
import android.os.Handler;
import android.webkit.JavascriptInterface;
import android.widget.Toast;
/**
 * android interface for JS
 * @author mengchaoyue
 *
 */
public class AfjsInterface {

	private Context mContext;
	private Handler mHandler;
	private String data;
	
	public AfjsInterface(Context context,Handler handler){
		mHandler = handler;
		mContext = context;
	}
	
	@JavascriptInterface
	public void showMessage(final String message){
		mHandler.post(new Runnable() {
			@Override
			public void run() {
                Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();				
			}
		});
	}
	
	@JavascriptInterface
	public String getData(){
		return data;
	}
	
	public void setData(String data){
		this.data = data;
	}
}
