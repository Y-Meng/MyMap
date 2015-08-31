package com.mcy.mobile.hybird;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
/**
 * html5页面加载
 * @author Administrator
 *
 */
@SuppressLint("SetJavaScriptEnabled")
public class FragmentWebView extends Fragment{

	private Handler mHandler = new Handler();
	private WebView webView;
	private AfjsInterface afjs;
	

//	protected String url;
//	protected String jsondata;
	
//	public String getUrl() {
//		return url;
//	}
//
//	public void setUrl(String url) {
//		this.url = url;
//		if(webView!=null){
//			webView.loadUrl(url);
//		}
//		Log.d("WebView", "set url");
//	}

//	public FragmentWebView(int sectionNumber,String url) {
//		Bundle args = new Bundle();
//		args.putInt(TAG, sectionNumber);
//		setArguments(args);
//		setUrl(url);
//	}

	public FragmentWebView() {
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(webView==null){
			webView = new WebView(getActivity());
			webView.getSettings().setJavaScriptEnabled(true);
			webView.getSettings().setDefaultTextEncodingName("UTF-8");
			
			afjs = new AfjsInterface(getActivity(), mHandler);
			webView.addJavascriptInterface(afjs,"afjs");
			//webView.loadUrl(url);
		}
		Log.d("WebView", "view created");
		return webView;
	}
	
	/**
	 * 加载网页
	 * @param url
	 */
	protected void loadURL(String url){
		if(webView!=null)
			webView.loadUrl(url);
	}
	
	/**
	 * 设置接口存储JSON数据，网页JS脚本可获�?
	 * @param data
	 */
	protected void setData(String data) {
		afjs.setData(data);
	}
}
