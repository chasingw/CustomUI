package com.robosense.customui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.robosense.customui.R;

import static android.R.attr.bitmap;

/**
 * Created by maxwell on 17-6-2.
 */

public class FloatCircleView extends View {

    public int width  = 150;
    public int height = 150;

    private Paint mPaintText;
    private Paint mPaintCircle;

    private String  mText     = "MES";
    private boolean isDraging = false;
    private Bitmap mSrcBitmap;
    private Bitmap mScaledBitmap;

    public FloatCircleView(Context context) {
        super(context);
        initPaints();
    }

    public FloatCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaints();
    }

    public FloatCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaints();
    }

    private void initPaints() {
        mPaintCircle = new Paint();
        mPaintCircle.setColor(Color.GRAY);
        mPaintCircle.setAntiAlias(true);

        mPaintText = new Paint();
        mPaintText.setTextSize(25);
        mPaintText.setColor(Color.WHITE);
        mPaintText.setAntiAlias(true);
        mPaintText.setFakeBoldText(true);

        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        mScaledBitmap = Bitmap.createScaledBitmap(mSrcBitmap, width, height, true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isDraging) {

            canvas.drawBitmap(mScaledBitmap, 0, 0, null);

        } else {

            canvas.drawCircle(width / 2, height / 2, width / 2, mPaintCircle);
            float textWidth = mPaintText.measureText(mText);
            float x = width / 2 - textWidth / 2;
            final Paint.FontMetrics fontMetrics = mPaintText.getFontMetrics();
            float dy = (fontMetrics.descent + fontMetrics.ascent) / 2;
            float y = height / 2 + dy;
            canvas.drawText(mText, x, y, mPaintText);
        }
    }

    public void setDragState(boolean isDraging) {
        this.isDraging = isDraging;
        invalidate();
    }
}
