package com.mcy.mobile.soap;

import java.io.IOException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;
import android.util.Log;

public abstract class SoapAction {
	
	
	protected String mMethod;//访问方法名称
	
	protected SoapObject rpc = null;//远程调用对象
	
	protected SoapSerializationEnvelope envelope = null;//服务访问封装
	
	protected HttpTransportSE transport = null;//访问转换对象
	
	public SoapAction(String method){
		mMethod = method;
		// name参数是函数名
		rpc = new SoapObject(SoapServer.getNAME_SPACE(),mMethod);
		envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		// 注册Envelope
		(new MarshalBase64()).register(envelope);
        // 设置是否调用的是dotNet�?发的WebService  
        envelope.dotNet = true;  
        // envelope.setOutputSoapObject(rpc);等价于envelope.bodyOut = rpc; 
        transport = new HttpTransportSE(SoapServer.getURL());
        transport.debug=true;
	}
	
	public abstract void setParams();
	
	/**
	 * 调用方法访问
	 * @return 返回对象
	 * @throws HttpResponseException
	 * @throws IOException
	 * @throws XmlPullParserException
	 */
    public Object call() throws HttpResponseException, IOException, XmlPullParserException{
    	Object response = null;
    	
    	if(transport!=null){
    		setParams();
    		envelope.bodyOut = rpc; 
    		Log.d("soap call", SoapServer.getURL()+":"+mMethod);
    		transport.call(SoapServer.getNAME_SPACE()+mMethod,envelope);
        	response = envelope.getResponse();	
        }else{
    		Log.e("SoapAction", "未初始化");
    	}	
    	return response;
    }

	public SoapObject getRpc() {
		return rpc;
	}

	public SoapSerializationEnvelope getEnvelope() {
		return envelope;
	}

	public HttpTransportSE getTransport() {
		return transport;
	}
}
