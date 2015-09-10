package com.mcy.myfragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.baidu.mapapi.map.BaiduMap;
import com.mcy.mobile.core.BaseFragment;
import com.mcy.mobile.injection.InjectView;
import com.mcy.mobile.view.floatbutton.AddFloatingActionButton;
import com.mcy.mymap.MainActivity;
import com.mcy.mymap.R;
import com.mcy.mymap.RecordActivity;

public class MainFragment extends BaseFragment {
	
	public final static int REQUEST_NEW_RECORD = 0;
	public final static int REQUEST_VIEW_RECORD = 1;
    public final static int REQUEST_EDIT_RECORD = 2;

	@InjectView(R.id.btnAddRecord)
	AddFloatingActionButton btnAddRecord;
	@InjectView(R.id.txtException)
	TextView txtException;
	@InjectView(R.id.txtMap)
	TextView txtMap;
	@InjectView(R.id.txtPatrol)
	TextView txtPatrol;
	
	public BaiduMap mMap;
	private Context mContext;
	private FragmentManager fm;
	private FragmentMapBaidu mapFragment;
	private FragmentExceptionList exceptionList;
	private FragmentPatrolList patrolList;
	
	private OnClickListener listener;
	
	public MainFragment(){
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		fm = getFragmentManager();
		
		mapFragment = new FragmentMapBaidu();
		patrolList = new FragmentPatrolList();
		exceptionList = new FragmentExceptionList();
		
		listener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.txtException:
					showList(1);
					break;
					
				case R.id.txtMap:
					showList(0);
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

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * 新增缺陷记录
	 */
	protected void newRecord() {
		Intent intent = new Intent(mContext,RecordActivity.class);
		startActivityForResult(intent, REQUEST_NEW_RECORD);
	}

	/**
	 * 显示任务列表
	 * @param i 列表类型
	 */
	protected void showList(int i) {
		switch (i) {
		case 1:
			//显示缺陷列表
			if(!exceptionList.isVisible()){
				fm.beginTransaction()
				.hide(mapFragment)
				.hide(patrolList)
				.show(exceptionList).commit();
				btnAddRecord.setVisibility(View.GONE);
			}
			break;
		case 2:
			//显示巡检列表
			if(!patrolList.isVisible()){
				fm.beginTransaction()
				.hide(exceptionList)
				.hide(mapFragment)
				.show(patrolList).commit();
				btnAddRecord.setVisibility(View.GONE);
			}
			break;
		case 0:
			//显示地图
			if(!mapFragment.isVisible()){
			    fm.beginTransaction()
			    .hide(exceptionList)
			    .hide(patrolList)
			    .show(mapFragment).commit();
				btnAddRecord.setVisibility(View.VISIBLE);
			}
			break;
		default:
			
			break;
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mContext = getActivity();
		((MainActivity) activity).onSectionAttached(1);
	}

	@Override
	protected void initView() {

		btnAddRecord.setOnClickListener(listener);
		txtException.setOnClickListener(listener);
		txtMap.setOnClickListener(listener);
		txtPatrol.setOnClickListener(listener);
		
		fm.beginTransaction()
		.add(R.id.exceptionContainer, exceptionList)
		.add(R.id.mapContainer, mapFragment)
		.add(R.id.patrolContainer, patrolList)
		.commit();
		showList(0);
	}

}
