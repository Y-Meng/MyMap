package com.mcy.mobile.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mcy.mobile.http.HttpServer;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.util.Log;
import android.webkit.MimeTypeMap;

/**
 * �汾���¹�����
 * @author Administrator
 *
 */
public class AppUpdateUtil {

	private static final String TAG = "VersionUpdate";
	
	/**
	 * ��ȡ���������°汾��Ϣ
	 * @return �汾��Ϣ
	 */
	private static VersionInfo getUpdateVersion(String json){
		try {
			JSONArray verArr = new JSONArray(json);
			JSONObject verInfo = verArr.getJSONObject(0);
			if(verInfo!=null){
			    VersionInfo v = new VersionInfo(Float.parseFloat(verInfo.getString("VERSIONCODE")));
			    v.setchangelog(verInfo.getString("CHANGELOG"));
			    v.setUrl(verInfo.getString("APPURL"));
				return v;
			}else{
				return null;
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ��ȡ��ǰ�汾��Ϣ
	 * @return �汾��Ϣ
	 * @throws NameNotFoundException 
	 */
	private static VersionInfo getCurrentVersion(Context context) throws NameNotFoundException{
		PackageInfo packInfo = context.getPackageManager().getPackageInfo(
				context.getPackageName(), 0);
		int versionCode = packInfo.versionCode;
		VersionInfo v = new VersionInfo(versionCode);
		return v ;
	}
	
	/**
	 * ����°�?
	 * @param context
	 * @throws NameNotFoundException
	 */
	public static void checkVersion(final Context context,String json,final String appName) throws NameNotFoundException{
		
		final VersionInfo update = getUpdateVersion(json);
		
		VersionInfo current = getCurrentVersion(context);
		if(update!=null&&update.getVersionCode()>current.getVersionCode()){
			Log.e("Version", update.versionCode+":"+current.versionCode);
			AlertDialog.Builder builder = new Builder(context);
			builder.setMessage(update.getChangelog());
			builder.setCancelable(false);
			builder.setTitle("发现新版�?"+update.versionCode);
			builder.setPositiveButton("更新", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
				    downloadUpdate(context,HttpServer.getWebServerUrl()+update.url,appName);	
				}
			});
			builder.setNegativeButton("取消", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
				}
			});
			builder.create().show();

		}else{
			Log.i(TAG, "无新版本");
		}
	}

	/**
	 * ���ظ���
	 * @param context
	 */
	private static void downloadUpdate(Context context, String apkUrl,String appName) {
		DownloadManager dm = 
				(DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
		Uri uri = Uri.parse(apkUrl);
		DownloadManager.Request request = new Request(uri);
		request.setAllowedNetworkTypes(Request.NETWORK_MOBILE | Request.NETWORK_WIFI);   
        //�����ļ�����  
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();  
        String mimeString = 
        		mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(apkUrl));  
        request.setMimeType(mimeString);  
        //��֪ͨ������ʾ   
        request.setNotificationVisibility(Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);  
        request.setVisibleInDownloadsUi(true);  
        //sdcard��Ŀ¼�µ�download�ļ���  
        request.setDestinationInExternalPublicDir("/DIST_FILES/APK/", appName);  
        request.setTitle("新版本下�?");   
        dm.enqueue(request);  	
	}

	/**
	 * �汾��Ϣʵ����
	 * @author Administrator
	 *
	 */
	 static class VersionInfo{
		private float versionCode;
		private String url;
		private String changelog;

		public VersionInfo(float code){
			versionCode = code;
		}
		
		public float getVersionCode() {
			return versionCode;
		}

		public void setVersionCode(float versionCode) {
			this.versionCode = versionCode;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getChangelog() {
			return changelog;
		}

		public void setchangelog(String changelog) {
			this.changelog = changelog;
		}
	}
}