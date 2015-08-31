package com.mcy.mobile.http;


public class HttpServer {
	

	private static String WEB_URL="http://192.168.0.105/HnDist.Phjg.Server/";
	

	public static String getWebServerUrl(){
		
		return WEB_URL;
	}

	public static void setWebServerUrl(String weburl){
		WEB_URL = weburl;
	}
}
