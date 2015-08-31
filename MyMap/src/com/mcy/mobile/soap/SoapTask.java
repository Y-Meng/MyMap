package com.mcy.mobile.soap;

import java.io.IOException;

import org.ksoap2.transport.HttpResponseException;
import org.xmlpull.v1.XmlPullParserException;

import android.os.AsyncTask;

public class SoapTask extends AsyncTask<Void, Void, Object>{

	SoapAction mAction;
	SoapCallback mCallback;
	
	public SoapTask(SoapAction action,SoapCallback callback){
		mAction = action;
		mCallback = callback;
	}
	

	@Override
	protected Object doInBackground(Void... params) {
		Object object = null;
		try {
			object = mAction.call();
			return object;
		} catch (HttpResponseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	protected void onPostExecute(Object result) {
		//super.onPostExecute(result);
		if(result==null){
			mCallback.onFail();
		}else{
			mCallback.onSuccess(result);
		}
	}
}
