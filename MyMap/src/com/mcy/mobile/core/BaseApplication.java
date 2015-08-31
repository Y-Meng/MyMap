package com.mcy.mobile.core;


import com.mcy.mobile.http.HttpServer;
import com.mcy.mobile.soap.SoapServer;

import android.app.Application;

public class BaseApplication extends Application {

    private User user;
      
    private HttpServer httpServer;
    
    private SoapServer soapServer;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public HttpServer getHttpServer() {
		return httpServer;
	}

	public void setHttpServer(HttpServer httpServer) {
		this.httpServer = httpServer;
	}

	public SoapServer getSoapServer() {
		return soapServer;
	}

	public void setSoapServer(SoapServer soapServer) {
		this.soapServer = soapServer;
	}
}
