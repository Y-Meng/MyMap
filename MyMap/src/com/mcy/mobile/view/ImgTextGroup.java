package com.mcy.mobile.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ImgTextGroup extends LinearLayout {

	private ImageView img;
	private TextView txt;
	
	public ImgTextGroup(Context context) {
		super(context);
		setOrientation(HORIZONTAL);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		params.setMargins(6, 0, 0, 0);
		setLayoutParams(params);
		img = new ImageView(context);
		txt = new TextView(context);
		addView(img);
		addView(txt);
	}
	
	public ImgTextGroup(Context context,Bitmap bitmap,CharSequence text){
		this(context);
		setImg(bitmap);
		setText(text);
	}

	@Override
	public boolean performClick() {
		// TODO Auto-generated method stub
		Log.d("ACTION", "CLICK");
		return super.performClick();
	}

	@Override
	public boolean performLongClick() {
		// TODO Auto-generated method stub
		Log.d("ACTION", "LONG_CLICK");
		return super.performLongClick();
	}

	public void setImg(Bitmap bitmap){
		img.setImageBitmap(bitmap);
	}
	
	public void setText(CharSequence text){
		txt.setText(text);
	}
}
