package com.mcy.mobile.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class LocateView extends View{
	
	private Paint mPaint;
	private Paint mBasePaint;
	private int mTouchX;
	private int mTouchY;
	private int mMoveX = 0;
	private int mMoveY = 0;
	private int centerX = 0,centerY = 0;
	
	public LocateView(Context context) {
		super(context);
        init();
	}
	
	public LocateView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public LocateView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init(){
		mPaint = new Paint();
		mPaint.setColor(Color.BLUE);
		mPaint.setStyle(Style.STROKE);
		mPaint.setStrokeWidth(2);
		mBasePaint = new Paint();
		mBasePaint.setColor(Color.BLACK);
		mBasePaint.setStyle(Style.STROKE);
		mBasePaint.setStrokeWidth(4);
		setFocusableInTouchMode(true);
	}

	@Override
	public boolean performClick() {
		return super.performClick();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mPaint.setColor(Color.RED);
			mTouchX = (int)event.getRawX(); 
			mTouchY = (int)event.getRawY(); 
		case MotionEvent.ACTION_UP:
			mPaint.setColor(Color.BLUE);
			mMoveX = 0;
			mMoveY = 0;
			performClick();
			break;
		case MotionEvent.ACTION_MOVE:
			mMoveX = (int)event.getRawX()-mTouchX;
			mMoveY = (int)event.getRawY()-mTouchY;
			mTouchX = (int) event.getRawX();
			mTouchY = (int) event.getRawY();
			centerX = centerX + (int) mMoveX;
			centerY = centerY + (int) mMoveY;
			layout(centerX - getWidth() / 2, centerY - getHeight() / 2,
					centerX + getWidth() / 2, centerY + getHeight() / 2);
		default:
			break;
		}
		return true;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
			super.onLayout(changed, left, top, right, bottom);
			//����ڸ��ؼ������꣬�Ǿ�����Ļ����?

	        centerX = (left+right)/2;
	        centerY = (top+bottom)/2;
//	        width = getWidth();
//	        height = getHeight();
	        //������Ļ����
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int w = getWidth();
		int h = getHeight();
		
		canvas.drawLine(0, h/2, w/2-w/6, h/2, mBasePaint);
		canvas.drawLine(w, h/2, w/2+w/6, h/2, mBasePaint);
		canvas.drawLine(w/2, 0, w/2, h/2-h/6, mBasePaint);
		canvas.drawLine(w/2, h, w/2, h/2+h/6, mBasePaint);

		canvas.drawLine(w/2-w/4, h/2-h/4, w/2-w/12, h/2-h/12, mBasePaint);
		canvas.drawLine(w/2+w/4, h/2+h/4, w/2+w/12, h/2+h/12, mBasePaint);
		canvas.drawLine(w/2-w/4, h/2+h/4, w/2-w/12, h/2+h/12, mBasePaint);
		canvas.drawLine(w/2+w/4, h/2-h/4, w/2+w/12, h/2-h/12, mBasePaint);
		
		canvas.drawRect(w/2-w/6, h/2-h/6,
		        w/2+w/6, h/2+h/6, mBasePaint);
		
		canvas.drawLine(w/2, h/2-h/12, w/2, h/2+h/12, mPaint);
		canvas.drawLine(w/2-w/12, h/2, w/2+w/12, h/2, mPaint);
	}
	public int getCenterX(){
		 return centerX;
	}
	public int getCenterY(){
		 return centerY;
	}
//	public int getCenterXinParent(){
//		return centerX;
//	}
//	public int getCenterYinParent(){
//		 
//		return centerY;
//	}
}
