package com.mcy.mobile.core;


import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
/**
 * Base class for activitys.
 * @author Administrator
 *
 */
public class BaseActivity extends InjectActivity {
	
	protected ProgressDialog mProgressDialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onPause() {
		if(mProgressDialog!=null)
			mProgressDialog.dismiss();
		Log.i("Activity", "Pause");
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		if(mProgressDialog!=null)
			mProgressDialog.dismiss();
		Log.i("Activity", "Destory");
		super.onDestroy();
	}
	
	/**
	 * get the instance of application
	 * @return
	 */
	protected BaseApplication getBaseApplication() {
		return (BaseApplication)getApplication();
	}
	
	/**
	 * get the user had login
	 * @return current user
	 */
	public User getUser(){
		return getBaseApplication().getUser();
	}

	/**
	 * show progress when do something need long time
	 */
	public void showProgress(){
		if(mProgressDialog==null)
			mProgressDialog = new ProgressDialog(this,ProgressDialog.THEME_HOLO_LIGHT);
		onInitProgressDialog(mProgressDialog);
		if(!mProgressDialog.isShowing())
			mProgressDialog.show();
	}
	/**
	 * show progress when do something need long time
	 * @param message for progress
	 */
	public void showProgress(String message){
		if(mProgressDialog == null)
			mProgressDialog = new ProgressDialog(this,ProgressDialog.THEME_HOLO_LIGHT);
		onInitProgressDialog(mProgressDialog);
		mProgressDialog.setMessage(message);
		if(!mProgressDialog.isShowing())
		    mProgressDialog.show();
	}
	/**
	 * call when progress init
	 * @param progressDialog
	 */
	protected void onInitProgressDialog(ProgressDialog progressDialog){
		//mProgressDialog.setProgressStyle(ProgressDialog.THEME_HOLO_LIGHT);
	}
	/**
	 * hide progress dialog
	 */
	public void cancelProgress(){
		if(mProgressDialog!=null&&mProgressDialog.isShowing())
		   mProgressDialog.dismiss();
	}

	/**
	 * Toast message
	 * @param msg
	 * @param length
	 */
	public void showToast(String msg,int length){
		Toast.makeText(getApplicationContext(), msg, length).show();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
	
	@SuppressWarnings("unchecked")
	public  <T extends View> T $(View v,int id){
		return (T)v.findViewById(id);
	}
	
	@SuppressWarnings("unchecked")
	public  <T extends View> T $(int id){
		return (T)findViewById(id);
	}
}
