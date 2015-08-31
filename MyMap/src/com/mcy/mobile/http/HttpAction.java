package com.mcy.mobile.http;

/**
 * @author Administrator
 *
 */
public class HttpAction {

	public static final String ACTION_TASK_UPDATA = "Task_Update.aspx";
	public static final String ACTION_TASK_SAVE = "Task_Save.aspx";
	public static final String ACTION_WYHC_SAVE = "Wyhc_Save.aspx";
	
	private static final String ACTION_LOGIN = "Login.aspx?";
	private static final String ACTION_VER_QUERY =  "verquery.aspx?";
	private static final String APP_ID = "PHJG";
	
	private static String getServer(){
		return HttpServer.getWebServerUrl();
	}
	
	
	public static String login(String username,String password){
		return getServer()+ACTION_LOGIN
				+"userbh="+username
				+"&userpwd="+password;
	}
	
	public static String verQuery(){
	       return HttpServer.getWebServerUrl()+ACTION_VER_QUERY	+"appid="+APP_ID;
	}
}
