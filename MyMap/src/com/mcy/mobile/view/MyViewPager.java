package com.mcy.mobile.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class MyViewPager extends ViewPager {

	private boolean scrollable = true;
	
	public MyViewPager(Context context) {
		super(context);
	}

	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setScrollable(boolean scrollable){
		this.scrollable = scrollable;
	}
	@Override
	public void scrollTo(int x, int y) {
		if(scrollable)
		   super.scrollTo(x, y);
	}

	@Override
	public void setCurrentItem(int item, boolean smoothScroll) {
		// TODO Auto-generated method stub
		super.setCurrentItem(item, smoothScroll);
	}

	@Override
	public void setCurrentItem(int item) {
		// TODO Auto-generated method stub
		super.setCurrentItem(item);
	}

//	@SuppressLint("ClickableViewAccessibility")
//	@Override
//	public boolean onTouchEvent(MotionEvent arg0) {
//		// TODO Auto-generated method stub
//		//return super.onTouchEvent(arg0);
//		//super.onTouchEvent(arg0);
//		return true;
//	}
}
