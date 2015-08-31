package com.mcy.mobile.view;

import com.mcy.mymap.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 节点进度
 * @author mcy
 * 
 */
public class NodeProgressBar extends View {

	private float nodeRadius = 5;
	private float progressWidth;
	private int baseColor;
	private int progressColor;
	private int textColor;
	private float textSize = 12;
	private int progress = 1;
	private int maxNode;
	private String[] nodesInfo = null;
	private int style;
	private Paint paint;

	public static final int STROKE = 0;
	public static final int FILL = 1;

	public NodeProgressBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		paint = new Paint();
		TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
				R.styleable.NodeProgressBar);
		
		baseColor = mTypedArray.getColor(R.styleable.NodeProgressBar_baseColor,
				Color.LTGRAY);
		progressColor = mTypedArray.getColor(
				R.styleable.NodeProgressBar_progressColor, Color.GREEN);
		textColor = mTypedArray.getColor(R.styleable.NodeProgressBar_textColor,
				Color.BLACK);
		textSize = mTypedArray.getDimension(
				R.styleable.NodeProgressBar_textSize, 20);
		progressWidth = mTypedArray.getDimension(
				R.styleable.NodeProgressBar_progressWidth, 6);
		nodeRadius = mTypedArray.getDimension(
				R.styleable.NodeProgressBar_nodeRadius, 18);
		maxNode = mTypedArray
				.getInteger(R.styleable.NodeProgressBar_maxNode, 6);
		CharSequence[] entries = mTypedArray.getTextArray(R.styleable.NodeProgressBar_nodesInfo);
		style = mTypedArray.getInt(R.styleable.NodeProgressBar_style, FILL);
		mTypedArray.recycle();
		nodesInfo = new String[]{"1","2","3","4","5","6"};
		if(entries!=null){
			for (int i = 0; i < entries.length; i++) {
				nodesInfo[i] = entries[i].toString();
			}
		}
	}

	public NodeProgressBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public NodeProgressBar(Context context) {
		this(context, null);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int width = 720;
		int height = 60;
		if (widthMode == MeasureSpec.EXACTLY) {
			// Parent has told us how big to be. So be it.
			width = widthSize;
		} else {
			if (widthMode == MeasureSpec.AT_MOST) {
				width = Math.max(500, widthSize);
			}
		}
		if (heightMode == MeasureSpec.EXACTLY) {
			// Parent has told us how big to be. So be it.
			height = heightSize;
		}
		// else {
		// if (heightMode == MeasureSpec.AT_MOST)
		// height = Math.max(60, heightSize);
		// }
		setMeasuredDimension(width, height);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int w = getWidth();
		int h = getHeight();
		float l = (w - 4 * nodeRadius) / (maxNode - 1);

		// draw background
		paint.setColor(baseColor);
		paint.setAntiAlias(true);
		for (int i = 1; i <= maxNode; i++) {
			canvas.drawCircle(2 * nodeRadius + l * (i - 1), h / 3, nodeRadius,
					paint);
		}
		paint.setStrokeWidth(3 * progressWidth);
		canvas.drawLine(2 * nodeRadius, h / 3, w - 2 * nodeRadius, h / 3, paint);

		// draw progress
		if(progress>0){
			paint.setColor(progressColor);
			if (style == STROKE) {
				paint.setStyle(Style.STROKE);
				paint.setStrokeWidth(2);
				for (int i = 1; i <= progress; i++) {
					canvas.drawCircle(2 * nodeRadius + l * (i - 1), h / 3,
							2 * nodeRadius / 3, paint);
				}
			} else {
				paint.setStyle(Style.FILL);
				for (int i = 1; i <= progress; i++) {
					canvas.drawCircle(2 * nodeRadius + l * (i - 1), h / 3,
							3 * nodeRadius / 5, paint);
				}
				paint.setStrokeWidth(progressWidth);
				if (progress < maxNode)
					canvas.drawLine(2 * nodeRadius, h / 3, l / 2 + l
							* (progress - 1), h / 3, paint);
				else
					canvas.drawLine(nodeRadius, h / 3, w - 2 * nodeRadius, h / 3,
							paint);
			}
		}
		
		// draw text
		paint.setStrokeWidth(0);
		paint.setColor(textColor);
		paint.setTextSize(textSize);
		paint.setTextAlign(Align.CENTER);
		paint.setTypeface(Typeface.DEFAULT);
		for (int i = 0; i < nodesInfo.length; i++) {
			canvas.drawText(nodesInfo[i], 2 * nodeRadius + l * (i), 14*h/15,
					paint);
		}
	}

	/**
	 * 设置进度
	 * 
	 * @param progress
	 */
	public synchronized void setProgress(int progress) {
		if (progress < 1) {
			this.progress = 0;
		} else if (progress > maxNode) {
			this.progress = maxNode;
		} else {
			this.progress = progress;
			Log.d("node", "changed");
		}
	}
	/**
	 * 获取当前进度
	 * @return
	 */
	public int getProgress(){
		return progress;
	}
	/**
	 * 设置节点信息
	 * @param nodesInfo
	 */
	public synchronized void setNodesInfo(String[] nodesInfo) {
		this.nodesInfo = nodesInfo;
	}
}
