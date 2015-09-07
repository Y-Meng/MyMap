package com.mcy.myfragment;

import android.os.Bundle;

import com.mcy.mobile.core.BaseFragment;

public class FragmentPatrolList extends BaseFragment {

	private static FragmentPatrolList mInstance;
	private FragmentPatrolList(){}
	
	public static FragmentPatrolList getInstance(){
		if(mInstance==null)
			mInstance = new FragmentPatrolList();
		return mInstance;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub

	}

}
