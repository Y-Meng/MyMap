package com.mcy.mymap;

import com.mcy.mobile.core.BaseActivity;
import com.mcy.mobile.core.BaseApplication;
import com.mcy.mobile.core.Login;
import com.mcy.mobile.core.User;
import com.mcy.mobile.injection.InjectVandM;
import com.mcy.mobile.injection.InjectView;
import com.mcy.mobile.soap.SoapServer;
import com.mcy.mobile.util.PreferenceUtil;
import com.mcy.mymap.R;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends BaseActivity implements Login{

	private static final String TAG = LoginActivity.class.getName();

	@InjectVandM(id = R.id.btnLogin, click = "btnClick")
	Button btnLogin;
	@InjectVandM(id = R.id.btnExit, click = "btnClick")
	Button btnExit;
	@InjectView(R.id.txtLoginUser)
	TextView txtUser;
	@InjectView(R.id.txtLoginPwd)
	TextView txtPwd;
	@InjectView(R.id.chbRememberUser)
	CheckBox chbRememberUser;

	protected BaseApplication mApp;
	protected User mUser;
	protected Context mContext;

	private String strUser, strPwd;
	private SharedPreferences mSp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		mApp = getBaseApplication();
		mContext = this;
		mSp = getSharedPreferences("userInfo", MODE_PRIVATE);
		
		SoapServer mServer = new SoapServer() {
			
			@Override
			public void initServerConfig() {
				// TODO Auto-generated method stub
				
			}
		};
		
		mApp.setSoapServer(mServer);
		
		// 是否存在历史用户信息
		boolean isRemember = PreferenceUtil.read(mSp, "isRemember", false);
		if (isRemember == true) {
			chbRememberUser.setChecked(true);
			txtUser.setText(PreferenceUtil.read(mSp, "user", ""));
			txtPwd.setText(PreferenceUtil.read(mSp, "pwd", ""));
		} else {
			chbRememberUser.setChecked(false);
		}
	}

	/**
	 * 单击事件监听
	 * 
	 * @param Clicked View
	 */
	protected void btnClick(View v) {
		switch (v.getId()) {
		case R.id.btnLogin:
			login();
			break;
		case R.id.btnExit:
			finish();
			break;
		default:
			break;
		}
		Log.d(TAG, "button click");
	}

	private void login() {
		if (txtUser.getText().toString().equals("")) {
			Toast.makeText(getBaseContext(), "用户名不能为空", Toast.LENGTH_SHORT).show();
		} else {
			if (txtPwd.getText().toString().equals("")) {
				Toast.makeText(getBaseContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
			} else {
				strUser = txtUser.getText().toString();
				strPwd = txtPwd.getText().toString();
				if (chbRememberUser.isChecked()) {
					PreferenceUtil.write(mSp, "user", strUser);
					PreferenceUtil.write(mSp, "pwd", strPwd);
					PreferenceUtil.write(mSp, "isRemember", true);
				} else {
					PreferenceUtil.write(mSp, "isRemember", false);
				}
				showProgress("正在登录...");
				LoginTask task = new LoginTask();
				task.execute(strUser,strPwd);
			}
		}
	}

	/**
	 * 登录异步任务
	 * @author Administrator
	 *
	 */
	private class LoginTask extends AsyncTask<String, Void, Object>{

		@Override
		protected Object doInBackground(String... params) {
			//TODO cancel test
			try {
				wait(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return login(params[0], params[1]);
		}

		@Override
		protected void onPostExecute(Object result) {
	   	    cancelProgress();
	   	    handleLoginResult(result);
		}
	}

	@Override
	public Object login(String user, String pwd) {
		// TODO do login
		
		return null;
	}

	@Override
	public void handleLoginResult(Object result) {
		// TODO handle login result
		startActivity(new Intent(this,MainActivity.class));
	}
}
