package com.mcy.mobile.util;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class DownloadReceiver extends BroadcastReceiver {

	public DownloadReceiver() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
	    if(action.equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) 
		  Toast.makeText(context, "下载完成", Toast.LENGTH_LONG).show();
	}
}
