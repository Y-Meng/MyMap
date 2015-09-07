package com.mcy.myfragment;

import android.os.Bundle;

import com.mcy.mobile.core.BaseFragment;

public class FragmentExceptionList extends BaseFragment {

	private static FragmentExceptionList mInstance;
	private FragmentExceptionList(){}
	
	public static FragmentExceptionList getInstance(){
		if(mInstance==null)
			mInstance = new FragmentExceptionList();
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
