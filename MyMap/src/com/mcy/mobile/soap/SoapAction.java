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
	
	protected String mMethod;
	
	protected SoapObject rpc = null;
	
	protected SoapSerializationEnvelope envelope = null;
	
	protected HttpTransportSE transport = null;
	
	public SoapAction(String method){
		
		mMethod = method;
		// remote process call
		rpc = new SoapObject(SoapServer.getNAME_SPACE(),mMethod);
		envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		// register Envelope
		(new MarshalBase64()).register(envelope);
        // dotNet WebService  
        envelope.dotNet = true;  
        // envelope.setOutputSoapObject(rpc); == nvelope.bodyOut = rpc; 
        transport = new HttpTransportSE(SoapServer.getURL());
        transport.debug=true;
	}
	
	public abstract void setParams();
	
	/**
	 * call soap action
	 * @return result
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
    		Log.e("SoapAction", "null transport");
    	}	
    	return response;
    }
    
    public void setParam(String key,Object value){
    	rpc.addProperty(key, value);
    }
}
