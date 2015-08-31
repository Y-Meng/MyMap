package com.mcy.mobile.soap;

public abstract class SoapServer {
	
	
	protected static String URL;
	protected static String NAME_SPACE;
	
	protected static String default_url = "http://192.168.0.105/HnDist.Phjg.Server/Analyse.asmx";
	protected static String default_name_space="http://tempuri.org/";
	
	public SoapServer(){
		initServerConfig();
	}
	
	public static String getURL(){
		if(URL!=null)
		    return URL;
		else
			return default_url;
	}
	public static String getNAME_SPACE() {
		if(NAME_SPACE!=null)
		    return NAME_SPACE;
		else
			return default_name_space;
	}
	
	public abstract void initServerConfig();
}
