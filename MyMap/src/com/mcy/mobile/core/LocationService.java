package com.mcy.mobile.core;

import java.util.Timer;
import java.util.TimerTask;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class LocationService extends Service {

	private static final String TAG = "LocationService";

	private LocationBinder mBinder;

	private LocationManager lm;
	private TimerTask mTask;
	private Timer mTimer;
	protected User mUser;

	protected Location lastLoc = null;

	@Override
	public IBinder onBind(Intent intent) {
		Log.d(TAG, "bind");
		return mBinder;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		mBinder = new LocationBinder();

		mUser = ((BaseApplication) getApplication()).getUser();

		mTask = new TimerTask() {

			@Override
			public void run() {
				//TODO do something
				
				
				
				
				
				
				
			}
		};

		LocationListener locationListener = new LocationListener() {

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				Log.d(TAG, "Status changed");
			}

			@Override
			public void onProviderEnabled(String provider) {
				Log.d(TAG, "provider enabled");
			}

			@Override
			public void onProviderDisabled(String provider) {
				Log.d(TAG, "Provider disabled");
			}

			@Override
			public void onLocationChanged(Location location) {
				//send broadcast
				Intent intent = new Intent();
				intent.putExtra("LOCATION", location);
                sendBroadcast(intent);
			}
		};

		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0,
				locationListener);

		mTimer = new Timer();
		mTimer.schedule(mTask, 0, 1 * 1000);

		Log.d(TAG, "create");
	}

	@Override
	public void onDestroy() {
		mTimer.cancel();
		mTask.cancel();
		super.onDestroy();
		Log.d(TAG, "destory");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "start");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.d(TAG, "unbind");
		return super.onUnbind(intent);
	}

	protected double getDistance(Location curLoc, Location lastLoc) {
		if (lastLoc == null)
			return 6;
		return Math.sqrt((curLoc.getLatitude() - lastLoc.getLatitude())
				* (curLoc.getLatitude() - lastLoc.getLatitude())
				+ (curLoc.getLongitude() - lastLoc.getLongitude())
				* (curLoc.getLongitude() - lastLoc.getLongitude()));
	}

	/**
	 * 获取定位结果
	 * 
	 * @return
	 */
	private Location getCurLocation() {
		Location loc = null;
		if (lm != null) {
			loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		}
		return loc;
	}

	/**
	 * 服务绑定接口
	 * @author mengchaoyue
	 */
	public class LocationBinder extends Binder {
		// 获取位置
		public Location getLocation() {
			return getCurLocation();
		}
	}
}
