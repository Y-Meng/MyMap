package com.mcy.myfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.mcy.mobile.core.BaseFragment;

public class FragmentMapBaidu extends BaseFragment {

	private MapView mMapView;
	private BaiduMap mMap;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mMapView = new MapView(getActivity());
		initView();
		return mMapView;
	}

	@Override
	protected void initView() {
		//hide logo
		View child = mMapView.getChildAt(1);
		if (child != null){            
		     child.setVisibility(View.INVISIBLE);           
		}

		mMapView.showZoomControls(false);
		mMap = mMapView.getMap();
		mMap.setTrafficEnabled(false);
	}

}
