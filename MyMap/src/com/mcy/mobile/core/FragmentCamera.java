package com.mcy.mobile.core;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.mcy.mobile.injection.InjectView;
import com.mcy.mobile.util.BitmapUtil;
import com.mcy.mobile.util.SDPathUtil;
import com.mcy.mymap.R;

public class FragmentCamera extends BaseFragment {
	
	static final int REQUEST_CAPTURE_IMAGE = 0;
	static final int REQUEST_SELECT_IMAGE = 1;

	@InjectView(R.id.PhotoView)GridLayout photoView;
	@InjectView(R.id.BtnCapture)ImageButton btnCapture;
	@InjectView(R.id.BtnSelect)ImageButton btnSelect;
	
	String photoName;
	List<String> pics;
    boolean firstInit = true;
	
	public String mDZJGH;//鐢靛瓙鐩戠鍙�
	protected  String mRWLX;//浠诲姟绫诲�??
	protected String mNode = "";
	
	public FragmentCamera(String dzjgh,String rwlx,String node){
		super();
		mDZJGH = dzjgh;
		mRWLX = rwlx;
		mNode = node;
		pics = new ArrayList<String>();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setContentView(R.layout.fragment_camera);
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	protected OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.BtnCapture:
				photoName = getFileName();
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(new File(photoName)));
				intent.putExtra("return-data", true);// 涓嶅姞鏈夋椂浼氳繑鍥瀌ata涓虹�?
				startActivityForResult(intent, REQUEST_CAPTURE_IMAGE);
				break;
			case R.id.BtnSelect:
				Intent intent1 = new Intent();
				intent1.setType("image/*");
				intent1.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(intent1, REQUEST_SELECT_IMAGE);
				break;
			default:
				break;
			}
		}
	};
	
	@Override
	protected void initView() {
		btnCapture.setOnClickListener(l);
		btnSelect.setOnClickListener(l);
	}
	
	
	@Override
	public void onStart() {
		super.onStart();
	}

	
	
	@Override
	public void onResume() {	
		super.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==REQUEST_CAPTURE_IMAGE&&resultCode==Activity.RESULT_OK){
			setPic(photoName);
		}else if(requestCode==REQUEST_SELECT_IMAGE&&resultCode==Activity.RESULT_OK){
			Uri uriAdded = data.getData();
			Cursor cursor = getActivity().getContentResolver().query(uriAdded, null, null, null, null);
			if(cursor.moveToNext()){
				//_data:瀵瑰簲鏂囦欢缁濆璺緞
				String path = cursor.getString(cursor.getColumnIndex("_data"));
				setPic(path);
			}
		}
	}

	/**
	 * 娣诲姞澶氬紶鍥剧�?
	 * @param paths
	 */
	public void setPics(List<String> paths){
		if(paths!=null&&paths.size()>1){
			//绗竴涓槸鍗犱綅绗�?
			for (int i = 1; i < paths.size(); i++) {
				setPic(paths.get(i));
			}
		}
	}
	
	/**
	 * 娣诲姞涓�寮犲浘鐗�
	 * @param photepath
	 */
	protected void setPic(final String photepath){
		Bitmap bitmap = BitmapUtil.scaleFromFile(photepath, 64, 64);
		ImageView imgview = new ImageView(getActivity());
		imgview.setImageBitmap(bitmap);
		imgview.setTag(photepath);
//		LinearLayout.LayoutParams layoutparams = new LinearLayout.LayoutParams(123,123);
//		imgview.setLayoutParams(layoutparams);
//		imgview.setPadding(3, 3, 3, 3);
		photoView.addView(imgview);
		imgview.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				v.buildDrawingCache();
				// ImageView瀵硅�?(iv_photo)蹇呴』鍋氬涓嬭缃悗锛屾墠鑳借幏鍙栧叾涓殑鍥惧�?
				// v.setDrawingCacheEnabled(true);
				// 鍦↖mageView瀵硅�?(iv_photo)琚玹ouch down鐨勬椂鍊欙紝鑾峰彇ImageView涓殑鍥惧儚
				String filepath = (String) v.getTag();
				// File file = new File(filepath);
				// Uri u=Uri.fromFile(file);
				Bitmap bitmap =  BitmapUtil.scaleFromFile(filepath, 500, 500);// getBitmapFromUri(u);
				// Bitmap bitmap
				// =Bitmap.createBitmap(v.getDrawingCache()) ;
				// 娓呯┖鐢诲浘缂撳瓨鍖�?
				// v.setDrawingCacheEnabled(false);
				showImgWindow(getActivity().getBaseContext(), photoView, bitmap);
			}
		});
		imgview.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(final View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						getActivity());
				builder.setTitle("鍒犻櫎鎻愮ず");
				builder.setMessage("纭畾鍒犻櫎鍚楋�?");
				builder.setPositiveButton("纭�?",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								photoView.removeView(v);
								pics.remove(photepath);
							}
						});
				builder.setNegativeButton("鍙栨�?",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						}).show();
				return true;
			}
		});
		pics.add(photepath);
	}
	
	/**
	 * 鐢熸垚鏂囦欢鍚�
	 * 
	 * @return 杩斿洖閫氳繃褰撳墠鏃堕棿鐢熸垚鐨勬枃浠跺�?
	 */
	protected String getFileName(int requestCode) {
		String name = null;
		SimpleDateFormat formater = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss",
				Locale.CHINA);
		Date curDate = new Date(System.currentTimeMillis());
		name = formater.format(curDate);
		if (requestCode == 1)
			name = SDPathUtil.sdMediaPath() + "photo_" + name + ".jpg";
		else
			name = SDPathUtil.sdMediaPath() + "vedio_" + name + ".mp4";
		return name;
	}
	
	/**
	 * 鑾峰彇鏂囦欢鍚嶇О
	 */
	protected String getFileName(){
		String name = null;
		SimpleDateFormat formater = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss",
				Locale.CHINA);
		Date curDate = new Date(System.currentTimeMillis());
		name = formater.format(curDate);
		name = SDPathUtil.getPatrolPath(mDZJGH,mRWLX,mNode) + "photo_" + name + ".jpg";
		return name;
	}
	
	/**
	 * 寮瑰嚭鍥剧墖绐楀�?
	 * 
	 * @param context涓婁笅鏂�?
	 * @param parent鐖舵帶浠�?
	 * @param str鍥剧墖璺緞
	 */
	@SuppressLint("InflateParams")
	protected void showImgWindow(Context context, View parent,Bitmap bitmap) {

		LayoutInflater inflater = (LayoutInflater) getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.layout_image, null);
		//view.setAlpha(0.5f);
		final PopupWindow ImgWindow = new PopupWindow(view, 600, 800, true);
		ImageView img = (ImageView) view.findViewById(R.id.img);
		img.setImageBitmap(bitmap);
		ImageButton button = (ImageButton)view.findViewById(R.id.close);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ImgWindow.dismiss();
			}
		});
		
		ImgWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
	}

	public List<String> getPics() {
		return pics;
	}
	
}
