package com.mcy.myfragment;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import com.mcy.mobile.core.BaseFragment;
import com.mcy.mobile.injection.InjectView;
import com.mcy.mymap.R;

public class FragmentPatrolList extends BaseFragment {

	
	@InjectView(R.id.edtText)
	EditText edtText;
	@InjectView(R.id.listView)
	ListView lstView;
	
	public FragmentPatrolList(){}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_list);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		edtText.setHint("—≤ºÏ¡–±Ì");
	}

}
