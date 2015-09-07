package com.mcy.myfragment;

import android.app.Activity;
import android.os.Bundle;

import com.mcy.mobile.core.BaseFragment;
import com.mcy.mymap.R;

public class MainFragment extends BaseFragment{

	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		
	}

}
