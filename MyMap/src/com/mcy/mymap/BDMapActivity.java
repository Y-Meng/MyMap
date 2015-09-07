package com.mcy.mymap;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class BDMapActivity extends Activity {

	private static final String TAG = BDMapActivity.class.getName();

	private SDKReceiver mReceiver;
	private MapView mBaiduMapView;
	private BaiduMap mBaiduMap;
	private LocationClient mLocationClient;
	private BDLocationListener mBDLocationListener = new BDLocationListener() {
		
		@Override
		public void onReceiveLocation(BDLocation bdLocation) {
			MyLocationData loc = new MyLocationData.Builder()
	        .accuracy(bdLocation.getRadius())
			.latitude(bdLocation.getLatitude())
			.longitude(bdLocation.getLongitude())
			.build();
	        mBaiduMap.setMyLocationData(loc);
	        LatLng latlng = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
	        MapStatus mapStatus = new MapStatus.Builder().target(latlng).build();
	        MapStatusUpdate statusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
	        mBaiduMap.setMapStatus(statusUpdate);
		}
	};
	
	public Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		// SDK��ʼ����SDK���������ʹ��֮ǰ����Ҫ����
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.activity_bdmap);
		mBaiduMapView = (MapView) findViewById(R.id.baiduMapView);
		// ���ذٶ�LOGO
		View child = mBaiduMapView.getChildAt(1);
		if (child != null){            
		     child.setVisibility(View.INVISIBLE);           
		}
		
		mBaiduMap = mBaiduMapView.getMap();
		mBaiduMap.setOnMapClickListener(new OnMapClickListener() {

			@Override
			public boolean onMapPoiClick(MapPoi arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void onMapClick(LatLng arg0) {
				Toast.makeText(
						mContext,
						String.valueOf(arg0.latitude) + ","
								+ String.valueOf(arg0.longitude),
						Toast.LENGTH_LONG).show();
			}
		});
		// ��λ
		mBaiduMap.setMyLocationEnabled(true);	
		mLocationClient = new LocationClient(getApplicationContext());
		mLocationClient.registerLocationListener(mBDLocationListener);
		mLocationClient.start();

		// ע�� SDK �㲥������
		IntentFilter iFilter = new IntentFilter();
		iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
		iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
		mReceiver = new SDKReceiver();
		registerReceiver(mReceiver, iFilter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.action_settings:

			return true;
		case R.id.action_set_normal:
			mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
			return true;
		case R.id.action_set_satellite:
			mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
			return true;
		case R.id.action_enable_traffic:
			mBaiduMap.setTrafficEnabled(!mBaiduMap.isTrafficEnabled());
			return true;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		super.onPause();
		// activity ��ͣʱͬʱ��ͣ��ͼ�ؼ�
		mBaiduMapView.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// activity �ָ�ʱͬʱ�ָ���ͼ�ؼ�
		mBaiduMapView.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// activity ����ʱͬʱ���ٵ�ͼ�ؼ�,ֹͣ��λ��ע��������
		mBaiduMapView.onDestroy();
		mLocationClient.stop();
		unregisterReceiver(mReceiver);
	}
	
	
	/**
	 * ����㲥�����࣬���� SDK key ��֤�Լ������쳣�㲥
	 */
	public class SDKReceiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			String s = intent.getAction();
			Log.d(TAG, "action: " + s);
			if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
				Toast.makeText(context,
						"key ��֤����! ���� AndroidManifest.xml �ļ��м�� key ����",
						Toast.LENGTH_LONG).show();
			} else if (s
					.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
				Toast.makeText(context,
						"key ��֤����! ���� AndroidManifest.xml �ļ��м�� key ����",
						Toast.LENGTH_LONG).show();
			}
		}
	}
}
