package com.betterment.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.actionbarsherlock.R;

public class AllocationMeter extends View implements AllocationChangeListener {
	private static final String TAG = AllocationMeter.class.getSimpleName();
	public static final float DEFAULT_MAX_ALLOCATION = 100; // Assuming this is km/h and you drive a super-car

	// Speedometer internal state
	private float mMaxAllocation = 100;
	private float mCurrentAllocation = 60;
	
	// Scale drawing tools
	private Paint onMarkPaint;
	private Paint offMarkPaint;
	private Paint scalePaint;
	private Paint readingPaint;
	private Path onPath;
	private Path offPath;
	final RectF oval = new RectF();
	
	// Drawing colors
	private int ON_COLOR = Color.argb(255, 0xff, 0xA5, 0x00);
	private int OFF_COLOR = Color.argb(255,0x3e,0x3e,0x3e);
	private int SCALE_COLOR = Color.argb(255, 255, 255, 255);
	private float SCALE_SIZE = 20f;
	private float READING_SIZE = 40f;
	
	// Scale configuration
	private float centerX;
	private float centerY;
	private float radius;

	public AllocationMeter(Context context){
		super(context);
		Log.d(TAG,"AllocationMeter(Context) called");
	}
	
	public AllocationMeter(Context context, AttributeSet attrs) {
		super(context, attrs);
		Log.d(TAG,"AllocationMeter(Context, AttributeSet) called");
//        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
//                R.styleable.AllocationMeter,
//                0, 0);
//        try {
//            mMaxAllocation = a.getFloat(R.styleable.AllocationMeter_maxAllocation, DEFAULT_MAX_ALLOCATION);
//            mCurrentAllocation = a.getFloat(R.styleable.AllocationMeter_currentAllocation, 0);
//            ON_COLOR = a.getColor(R.styleable.AllocationMeter_onColors, ON_COLOR);
//            OFF_COLOR = a.getColor(R.styleable.AllocationMeter_offColors, OFF_COLOR);
//            SCALE_COLOR = a.getColor(R.styleable.AllocationMeter_scaleColors, SCALE_COLOR);
//            SCALE_SIZE = a.getDimension(R.styleable.AllocationMeter_scaleTextSizes, SCALE_SIZE);
//            READING_SIZE = a.getDimension(R.styleable.AllocationMeter_readingTextSizes, READING_SIZE);
//        } finally{
//            a.recycle();
//        }
		initDrawingTools();
	}

	private void initDrawingTools(){
		onMarkPaint = new Paint();
		onMarkPaint.setStyle(Paint.Style.STROKE);
		onMarkPaint.setColor(ON_COLOR);
		onMarkPaint.setStrokeWidth(35f);
		onMarkPaint.setShadowLayer(5f, 0f, 0f, ON_COLOR);
		onMarkPaint.setAntiAlias(true);

		offMarkPaint = new Paint(onMarkPaint);
		offMarkPaint.setColor(OFF_COLOR);
		offMarkPaint.setStyle(Paint.Style.FILL_AND_STROKE);
		offMarkPaint.setShadowLayer(0f, 0f, 0f, OFF_COLOR);

		scalePaint = new Paint(offMarkPaint);
		scalePaint.setStrokeWidth(2f);
		scalePaint.setTextSize(SCALE_SIZE);
		scalePaint.setShadowLayer(5f, 0f, 0f, Color.RED);
		scalePaint.setColor(SCALE_COLOR);

		readingPaint = new Paint(scalePaint);
		readingPaint.setStyle(Paint.Style.FILL_AND_STROKE);
		offMarkPaint.setShadowLayer(3f, 0f, 0f, Color.WHITE);
		readingPaint.setTextSize(65f);
		readingPaint.setTypeface(Typeface.SANS_SERIF);
		readingPaint.setColor(Color.WHITE);

		onPath = new Path();
		offPath = new Path();
	}

	public float getCurrentSpeed() {
		return mCurrentAllocation;
	}

	public void setCurrentSpeed(float mCurrentSpeed) {
		if(mCurrentSpeed > this.mMaxAllocation)
			this.mCurrentAllocation = mMaxAllocation;
		else if(mCurrentSpeed < 0)
			this.mCurrentAllocation = 0;
		else
			this.mCurrentAllocation = mCurrentSpeed;
	}

	@Override
	protected void onSizeChanged(int width, int height, int oldw, int oldh) {
		Log.d(TAG, "Size changed to " + width + "x" + height);

		// Setting up the oval area in which the arc will be drawn
		if (width > height){
			radius = height/2;
		}else{
			radius = width/2;
		}
		oval.set(centerX - radius,
				centerY - radius,
				centerX + radius,
				centerY + radius);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		Log.d(TAG, "Width spec: " + MeasureSpec.toString(widthMeasureSpec));
//		Log.d(TAG, "Height spec: " + MeasureSpec.toString(heightMeasureSpec));

		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);

		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		int chosenWidth = chooseDimension(widthMode, widthSize);
		int chosenHeight = chooseDimension(heightMode, heightSize);

		int chosenDimension = Math.min(chosenWidth, chosenHeight);
		centerX = chosenDimension / 2;
		centerY = chosenDimension / 2;
		setMeasuredDimension(800, 300);
	}

	private int chooseDimension(int mode, int size) {

        return getPreferredSize();
	}

	// in case there is no size specified
	private int getPreferredSize() {
		return 600;
	}

	@Override
	public void onDraw(Canvas canvas){
		drawScaleBackground(canvas);
		drawScale(canvas);
		drawLegend(canvas);
		drawReading(canvas);
	}

	/**
	 * Draws the segments in their OFF state
	 * @param canvas
	 */
	private void drawScaleBackground(Canvas canvas){
		canvas.drawARGB(0, 0, 0, 0);
		Log.d(TAG,"drawScaleBackground");
		offPath.reset();
		for(int i = -180; i < 0; i+=4){
			offPath.addArc(oval, i, 2f);
		}
		canvas.drawPath(offPath, offMarkPaint);
	}

	private void drawScale(Canvas canvas){
		onPath.reset();
		for(int i = -180; i < (mCurrentAllocation/mMaxAllocation)*180 - 180; i+=4){
			onPath.addArc(oval, i, 2f);
		}
		canvas.drawPath(onPath, onMarkPaint);
	}

	private void drawLegend(Canvas canvas){
		canvas.save(Canvas.MATRIX_SAVE_FLAG);
		canvas.rotate(-180, centerX,centerY);
		Path circle = new Path();
		double halfCircumference = radius * Math.PI;
		double increments = 20;
		for(int i = 0; i < this.mMaxAllocation; i += increments){
			circle.addCircle(centerX, centerY, radius, Path.Direction.CW);
			canvas.drawTextOnPath(String.format("%d", i),
								circle,
								(float) (i*halfCircumference/this.mMaxAllocation),
								-30f,
								scalePaint);
		}

		canvas.restore();
	}

	private void drawReading(Canvas canvas){
		Path path = new Path();
		String message = String.format("%d", (int)this.mCurrentAllocation);
		float[] widths = new float[message.length()];
		readingPaint.getTextWidths(message, widths);
		float advance = 0;
		for(double width:widths)
			advance += width;
		Log.d(TAG,"advance: "+advance);
		path.moveTo(centerX - advance/2, centerY);
		path.lineTo(centerX + advance/2, centerY);
		canvas.drawTextOnPath(message, path, 0f, 0f, readingPaint);
	}

	@Override
	public void onAllocationChanged(float newSpeedValue) {
		this.setCurrentSpeed(newSpeedValue);
		this.invalidate();
	}
}
