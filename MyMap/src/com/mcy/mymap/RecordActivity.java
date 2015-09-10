package com.mcy.mymap;

import com.mcy.mobile.core.BaseActivity;
import com.mcy.mobile.core.FragmentCamera;
import com.mcy.mobile.injection.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RecordActivity extends BaseActivity {

    @InjectView(R.id.btnSave)
    Button btnSave;
    @InjectView(R.id.btnCancel)
    Button btnCancel;
    @InjectView(R.id.edtX)
    EditText edtX;
    @InjectView(R.id.edtY)
    EditText edtY;
    
    FragmentCamera mCamera;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_record);
		Intent intent = getIntent();
		double x = intent.getDoubleExtra("x", 0);
		double y = intent.getDoubleExtra("y", 0);
		
		edtX.setText(String.valueOf(x));
		edtY.setText(String.valueOf(y));
		
		mCamera = (FragmentCamera) getFragmentManager().findFragmentById(R.id.frgCamera);
	}

	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.record, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
}
