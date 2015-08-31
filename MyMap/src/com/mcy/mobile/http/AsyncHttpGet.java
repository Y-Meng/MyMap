package com.mcy.mobile.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.util.Log;
/**
 * 
 * @author MCY
 */
public class AsyncHttpGet extends BaseRequest{
	private static final long serialVersionUID = 2L;
	DefaultHttpClient httpClient;
	List<RequestParameter> parameter;
	public AsyncHttpGet(ParseHandler handler,String url,List<RequestParameter> parameter,RequestResultCallback requestCallback){
		this.handler = handler;
		this.url = url;
		this.parameter = parameter;
		this.requestCallback = requestCallback;
		if(httpClient == null)
			httpClient = new DefaultHttpClient();
	}
	
	@Override
	public void run() {
		 try{
			 if(parameter!=null&&parameter.size()>0){
				 StringBuilder bulider  = new StringBuilder();
				 for(RequestParameter p:parameter){
					 if(bulider.length()!=0){
						 bulider.append("&");
					 }
					 
					 bulider.append(UrlUtils.encode(p.getName()));
					 bulider.append("=");
					 bulider.append(UrlUtils.encode(p.getValue()));		 
				 } 
				 url += "?"+bulider.toString();
			 }
			 Log.d(AsyncHttpGet.class.getName(),
						"AsyncHttpGet  request to url :" + url);
			 request = new HttpGet(url);

			HttpResponse response = httpClient.execute(request);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
			 ByteArrayOutputStream content = new ByteArrayOutputStream();
			 
			 response.getEntity().writeTo(content);
			 String ret = new String(content.toByteArray()).trim();
			 content.close();
			 Object Object = null;
			if(AsyncHttpGet.this.handler !=null){
				Object = AsyncHttpGet.this.handler.handle(ret);
				if( AsyncHttpGet.this.requestCallback != null&&Object !=null){
					 AsyncHttpGet.this.requestCallback.onSuccess(Object);
					 return ;
				}
				if(Object == null||"".equals(Object.toString())){
					RequestException exception = new RequestException(RequestException.IO_EXCEPTION,"Request IO Error");
					AsyncHttpGet.this.requestCallback.onFail(exception);
				}
			}else{
				AsyncHttpGet.this.requestCallback.onSuccess(ret);
			}
			}else{
				RequestException exception = new RequestException(RequestException.IO_EXCEPTION,"Request IO Error");
				AsyncHttpGet.this.requestCallback.onFail(exception);
			}
			Log.d(AsyncHttpGet.class.getName(), "AsyncHttpGet  request to url :"+url+"  finished !");
		}catch(java.lang.IllegalArgumentException e){
			RequestException exception = new RequestException(
					RequestException.IO_EXCEPTION, "Request IO Error");
			AsyncHttpGet.this.requestCallback.onFail(exception);
			Log.d(AsyncHttpGet.class.getName(),
					"AsyncHttpGet  request to url :" + url + "  onFail  "
							+ e.getMessage());
		}  catch (org.apache.http.conn.ConnectTimeoutException e) {
			RequestException exception = new RequestException(
					RequestException.SOCKET_TIMEOUT_EXCEPTION, "Socket Timeout Error");
			AsyncHttpGet.this.requestCallback.onFail(exception);
			Log.d(AsyncHttpGet.class.getName(),
					"AsyncHttpGet  request to url :" + url + "  onFail  "
							+ e.getMessage());
		} catch (java.net.SocketTimeoutException e) {
			RequestException exception = new RequestException(
					RequestException.SOCKET_TIMEOUT_EXCEPTION, "Socket Timeout Error");
			AsyncHttpGet.this.requestCallback.onFail(exception);
			Log.d(AsyncHttpGet.class.getName(),
					"AsyncHttpGet  request to url :" + url + "  onFail  "
							+ e.getMessage());
		} catch (UnsupportedEncodingException e) {
			RequestException exception = new RequestException(
					RequestException.UNSUPPORTED_ENCODEING_EXCEPTION, "Data Encoding Error");
			AsyncHttpGet.this.requestCallback.onFail(exception);
			Log.d(AsyncHttpGet.class.getName(),
					"AsyncHttpGet  request to url :" + url + "  UnsupportedEncodingException"
							+ e.getMessage());
		} catch (org.apache.http.conn.HttpHostConnectException e) {
			RequestException exception = new RequestException(
					RequestException.CONNECT_EXCEPTION, "Connect Error");
			AsyncHttpGet.this.requestCallback.onFail(exception);
			Log.d(AsyncHttpGet.class.getName(),
					"AsyncHttpGet  request to url :" + url + "  HttpHostConnectException"
							+ e.getMessage());
		} catch (ClientProtocolException e) {
			RequestException exception = new RequestException(
					RequestException.CLIENT_PROTOL_EXCEPTION, "Cilent Error");
			AsyncHttpGet.this.requestCallback.onFail(exception);
			e.printStackTrace();
			Log.d(AsyncHttpGet.class.getName(),
					"AsyncHttpGet  request to url :" + url + "  ClientProtocolException"
							+ e.getMessage());
		} catch (IOException e) {
			RequestException exception = new RequestException(
					RequestException.IO_EXCEPTION, "Connect IO Error");
			AsyncHttpGet.this.requestCallback.onFail(exception);
			e.printStackTrace();
			Log.d(AsyncHttpGet.class.getName(),
					"AsyncHttpGet  request to url :" + url + "IOException"
							+ e.getMessage());
		} finally {
			//request.
		}
		super.run();
	}
}
