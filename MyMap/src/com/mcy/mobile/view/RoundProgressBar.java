package com.mcy.mobile.view;

import com.mcy.mymap.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class RoundProgressBar extends View {

	private int progress = 0;
	private int stroke = 10;
	// private int radius = 50;//根据控件布局大小计算
	private int radius;
	private float textSize;
	private int maxCount;
	private int baseColor;
	private int progressColor;
	private int textColor;
	private String text;
	private int style;
	public static final int STROKE = 0;
	public static final int FILL = 1;

	private Paint paint;
	int width = 100;
	int height = 100;

	public RoundProgressBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs);
	}

	public RoundProgressBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public RoundProgressBar(Context context) {
		this(context, null, 0);
	}

	private void init(Context context, AttributeSet attrs) {
		paint = new Paint();
		TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
				R.styleable.NodeProgressBar);
		textSize = mTypedArray.getDimension(
				R.styleable.RoundProgressBar_TextSize, 20);
		textColor = mTypedArray.getColor(
				R.styleable.RoundProgressBar_TextColor, Color.DKGRAY);
		baseColor = mTypedArray.getColor(
				R.styleable.RoundProgressBar_BaseColor, Color.RED);
		progressColor = mTypedArray.getColor(
				R.styleable.RoundProgressBar_ProgressColor, Color.GREEN);
		style = mTypedArray.getInteger(
				R.styleable.RoundProgressBar_Style,STROKE);
		maxCount = mTypedArray.getInteger(
				R.styleable.RoundProgressBar_MaxCount, 100);
		mTypedArray.recycle();
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		radius = Math.min(width, height) / 2 - stroke;
		RectF oval = new RectF(width / 2 - radius, height / 2 - radius, width
				/ 2 + radius, height / 2 + radius);
		paint.setColor(baseColor);
		paint.setAntiAlias(true);
		paint.setStrokeWidth(stroke);
		paint.setStyle(Style.STROKE);
		canvas.drawCircle(width / 2, height / 2, radius, paint);

		paint.setColor(progressColor);
		paint.setStrokeWidth(stroke);
		switch (style) {
		case STROKE:
			paint.setStyle(Style.STROKE);
			canvas.drawArc(oval, 0, 360 * progress / maxCount, false, paint);
			paint.setTextAlign(Align.CENTER);
			paint.setColor(textColor);
			paint.setTextSize(textSize);
			paint.setStyle(Style.FILL);
			paint.setTypeface(Typeface.DEFAULT_BOLD); // 设置字体
			text = String.valueOf(progress/maxCount)+"%";
			canvas.drawText(text, width / 2, height / 2 + textSize / 2, paint);
			break;
		case FILL:
            paint.setStyle(Style.FILL);
            canvas.drawArc(oval, 0, 360 * progress / maxCount, false, paint);
			break;
		default:
			break;
		}

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		if (widthMode == MeasureSpec.EXACTLY) {
			// Parent has told us how big to be. So be it.
			width = widthSize;
		} else {
			if (widthMode == MeasureSpec.AT_MOST) {
				width = Math.min(100, widthSize);
			}
		}
		if (heightMode == MeasureSpec.EXACTLY) {
			// Parent has told us how big to be. So be it.
			height = heightSize;
		} else {
			if (heightMode == MeasureSpec.AT_MOST)
				height = Math.min(100, heightSize);
		}
		setMeasuredDimension(width, height);
	}

	public synchronized int getProgress() {
		return progress;
	}

	public synchronized void setProgress(int progress) {
		if(progress<0)
			this.progress = 0;
		else if(progress>maxCount)
			this.progress = maxCount;
		else
			this.progress = progress;
		postInvalidate();
	}

	public void setStroke(int stroke) {
		this.stroke = stroke;
		postInvalidate();
	}

	public synchronized void setTextSize(float textSize) {
		this.textSize = textSize;
		postInvalidate();
	}

	public synchronized int getMaxCount() {
		return maxCount;
	}

	public synchronized void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
		postInvalidate();
	}

	public synchronized void setBaseColor(int baseColor) {
		this.baseColor = baseColor;
		postInvalidate();
	}

	public synchronized void setProgressColor(int progressColor) {
		this.progressColor = progressColor;
		postInvalidate();
	}

	public synchronized void setTextColor(int textColor) {
		this.textColor = textColor;
		postInvalidate();
	}

	public synchronized String getText() {
		return text;
	}

	public synchronized void setText(String text) {
		this.text = text;
		postInvalidate();
	}

	public synchronized int getStyle() {
		return style;
	}

	public synchronized void setStyle(int style) {
		this.style = style;
		postInvalidate();
	}
}
