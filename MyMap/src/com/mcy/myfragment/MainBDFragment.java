package com.mcy.myfragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.mcy.mobile.core.BaseFragment;
import com.mcy.mobile.injection.InjectView;
import com.mcy.mobile.view.floatbutton.AddFloatingActionButton;
import com.mcy.mymap.R;

public class MainBDFragment extends BaseFragment {

	private static MainBDFragment mInstance;
	
	@InjectView(R.id.baiduMapView)
	MapView mMapView;
	@InjectView(R.id.btnAddRecord)
	AddFloatingActionButton btnAddRecord;
	@InjectView(R.id.txtException)
	TextView txtException;
	@InjectView(R.id.txtMap)
	TextView txtMap;
	@InjectView(R.id.txtPatrol)
	TextView txtPatrol;
	@InjectView(R.id.lstContainer)
	View lstContainer;
	
	public BaiduMap mMap;
	private Context mContext;
	private FragmentManager fm;
	private BaseFragment curFragment;
	
	private OnClickListener listener;
	
	private MainBDFragment(){
		
	}
	
	public static MainBDFragment newInstance(){
		if(mInstance==null)
			mInstance = new MainBDFragment();
		return mInstance;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_bd);
		fm = getFragmentManager();
		
		listener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.txtException:
					showList(1);
					break;
					
				case R.id.txtMap:
					hideList();
					break;
					
				case R.id.txtPatrol:
					showList(2);
					break;
					
				case R.id.btnAddRecord:
					newRecord();
					break;
				default:
					break;
				}	
			}
		};
	}

	/**
	 * 新增缺陷记录
	 */
	protected void newRecord() {
		// TODO Auto-generated method stub
		Dialog dialog = new Dialog(mContext);
		dialog.setContentView(R.layout.fragment_camera);
		dialog.show();
	}

	/**
	 * 返回地图界面
	 */
	protected void hideList() {
		fm.beginTransaction().hide(curFragment).commit();
	}

	/**
	 * 显示任务列表
	 * @param i 列表类型
	 */
	protected void showList(int i) {
		switch (i) {
		case 1:
			//显示缺陷列表
			curFragment = FragmentExceptionList.getInstance();
			fm.beginTransaction().replace(R.id.lstContainer, curFragment).commit();
			break;
		case 2:
			//显示巡检列表
			curFragment = FragmentPatrolList.getInstance();
			fm.beginTransaction().replace(R.id.lstContainer, curFragment).commit();
			break;
		default:
			break;
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mContext = getActivity();
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
		
		btnAddRecord.setOnClickListener(listener);
		txtException.setOnClickListener(listener);
		txtMap.setOnClickListener(listener);
		txtPatrol.setOnClickListener(listener);
	}

}
