package com.mcy.mobile.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;

import android.util.Log;

public class AsyncHttpPost extends BaseRequest {
	private static final long serialVersionUID = 2L;
	private DefaultHttpClient httpClient;
	private List<RequestParameter> paramerters = null;
	
	public AsyncHttpPost(ParseHandler handler, String url,List<RequestParameter> parameters,
			RequestResultCallback requestCallback) {
		this.handler = handler;
		this.url = url;
		this.paramerters = parameters;
		this.requestCallback = requestCallback;
		if (httpClient == null)
			httpClient = new DefaultHttpClient();
	}

	@Override
	public void run() {
		try {
			request = new HttpPost(url);
			Log.d(AsyncHttpPost.class.getName(),
						"AsyncHttpGet  request to url :" + url);
			request.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, connectTimeout);
			request.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
					readTimeout);
			if(paramerters!=null&&paramerters.size()>0){
				List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
				for(RequestParameter p : paramerters){
					list.add(new BasicNameValuePair(p.getName(),p.getValue()));
				}
			((HttpPost)request).setEntity(new UrlEncodedFormEntity(list, HTTP.UTF_8) );
			}
			HttpResponse response = httpClient.execute(request);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				ByteArrayOutputStream content = new ByteArrayOutputStream();
				response.getEntity().writeTo(content);
				String ret = new String(content.toByteArray()).trim();
				content.close();
				Object Object = null;
				if(AsyncHttpPost.this.handler !=null){
					Object = AsyncHttpPost.this.handler.handle(ret);
					if( AsyncHttpPost.this.requestCallback != null&&Object !=null){
						AsyncHttpPost.this.requestCallback.onSuccess(Object);
						 return ;
					}
					if(Object == null||"".equals(Object.toString())){
						RequestException exception = new RequestException(RequestException.IO_EXCEPTION,"IO Error");
						AsyncHttpPost.this.requestCallback.onFail(exception);
					}
				}else{
					AsyncHttpPost.this.requestCallback.onSuccess(ret);
				}
			} else {
				RequestException exception = new RequestException(
						RequestException.IO_EXCEPTION, "IO Error"
								+ statusCode);
				AsyncHttpPost.this.requestCallback.onFail(exception);
			}

			Log.d(AsyncHttpPost.class.getName(),
					"AsyncHttpGet  request to url :" + url + "  finished!");
		}catch(java.lang.IllegalArgumentException e){
			RequestException exception = new RequestException(
					RequestException.IO_EXCEPTION, "IO Error");
			AsyncHttpPost.this.requestCallback.onFail(exception);
			Log.d(AsyncHttpGet.class.getName(),
					"AsyncHttpPost  request to url :" + url + "  onFail"
							+ e.getMessage());
		}  catch (org.apache.http.conn.ConnectTimeoutException e) {
			RequestException exception = new RequestException(
					RequestException.SOCKET_TIMEOUT_EXCEPTION, "Socket Timeout");
			AsyncHttpPost.this.requestCallback.onFail(exception);
			Log.d(AsyncHttpPost.class.getName(),
					"AsyncHttpGet  request to url :" + url + "  onFail  "
							+ e.getMessage());
		} catch (java.net.SocketTimeoutException e) {
			RequestException exception = new RequestException(
					RequestException.SOCKET_TIMEOUT_EXCEPTION, "Socket Timeout");
			AsyncHttpPost.this.requestCallback.onFail(exception);
			Log.d(AsyncHttpPost.class.getName(),
					"AsyncHttpGet  request to url :" + url + "  onFail  "
							+ e.getMessage());
		} catch (UnsupportedEncodingException e) {
			RequestException exception = new RequestException(
					RequestException.UNSUPPORTED_ENCODEING_EXCEPTION, "Encoding Error");
			AsyncHttpPost.this.requestCallback.onFail(exception);
			Log.d(AsyncHttpPost.class.getName(),
					"AsyncHttpGet  request to url :" + url + "  UnsupportedEncodingException"
							+ e.getMessage());
		} catch (org.apache.http.conn.HttpHostConnectException e) {
			RequestException exception = new RequestException(
					RequestException.CONNECT_EXCEPTION, "Connect Error");
			AsyncHttpPost.this.requestCallback.onFail(exception);
			Log.d(AsyncHttpPost.class.getName(),
					"AsyncHttpGet  request to url :" + url + "  HttpHostConnectException"
							+ e.getMessage());
		} catch (ClientProtocolException e) {
			RequestException exception = new RequestException(
					RequestException.CLIENT_PROTOL_EXCEPTION, "Cilent Error");
			AsyncHttpPost.this.requestCallback.onFail(exception);
			e.printStackTrace();
			Log.d(AsyncHttpPost.class.getName(),
					"AsyncHttpGet  request to url :" + url + "  ClientProtocolException"
							+ e.getMessage());
		} catch (IOException e) {
			RequestException exception = new RequestException(
					RequestException.IO_EXCEPTION, "IO Error");
			AsyncHttpPost.this.requestCallback.onFail(exception);
			e.printStackTrace();
			Log.d(AsyncHttpPost.class.getName(),
					"AsyncHttpGet  request to url :" + url + "  IOException  "
							+ e.getMessage());
		} finally {
			//request.
		}
		super.run();
	}
}
