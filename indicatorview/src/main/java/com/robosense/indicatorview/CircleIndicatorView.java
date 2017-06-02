package com.robosense.indicatorview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by maxwell on 17-5-25.
 */

public class CircleIndicatorView extends View {

    private float radius;
    private float borderWidth;
    private FillMode mFillMode = FillMode.NONE;
    private int   selectColor;
    private int   normalColor;
    private float space;
    private int   textColor;
    private int   mCount; // indicator 的数量

    private Paint mCirclePaint;
    private Paint mTextPaint;

    private ArrayList<Indicator> mIndicators;

    public static class Indicator {
        public float cx; // 圆心x 坐标
        public float cy; // 圆心y 坐标
    }

    public enum FillMode {
        LETTER,
        NUMBER,
        NONE
    }

    public CircleIndicatorView(Context context) {
        super(context);
        init();
    }

    public CircleIndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getAttr(context, attrs);
        init();
    }


    public CircleIndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttr(context, attrs);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CircleIndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        getAttr(context, attrs);
        init();
    }

    private void init() {
        //初始化画笔
        mCirclePaint = new Paint();
        mCirclePaint.setDither(true);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mIndicators = new ArrayList<>();
        initValue();
    }

    private void initValue() {
        mCirclePaint.setColor(normalColor);
        mCirclePaint.setStrokeWidth(borderWidth);

        //text
    }

    private void measureIndicator() {
        mIndicators.clear();
        float cx = 0;
        for (int i = 0; i < mCount; i++) {
            final Indicator indicator = new Indicator();
            if (i == 0) {
                cx = radius + borderWidth;
            } else {
                cx += (radius + borderWidth) * 2 + space;
            }

            indicator.cx = cx;
            indicator.cy = getMeasuredHeight() / 2;

            mIndicators.add(indicator);
        }
    }

    private void setCount(int count) {
        mCount = 4;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = (int) ((radius + borderWidth) * 2 * mCount + space * (mCount - 1));
        int height = (int) (radius * 2);
        setMeasuredDimension(width, height);
        measureIndicator();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /*<attr name="indicator_radius" format="dimension"/>
                <attr name="indicator_borderWidth" format="dimension"/>
                <attr name="indicator_fillMode" format="integer"/>
                <attr name="indicator_selectColor" format="color|reference"/>
                <attr name="indicator_normalColor" format="color|reference"/>
                <attr name="indicator_space" format="dimension"/>
                <attr name="indicator_textColor" format="color|reference"/>*/
    private void getAttr(Context context, AttributeSet attrs) {
        final TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CircleIndicatorView);
        radius = ta.getDimensionPixelSize(R.styleable.CircleIndicatorView_indicator_radius, DisplayUtils.dpToPx(6));
        borderWidth = ta.getDimension(R.styleable.CircleIndicatorView_indicator_borderWidth, 0);
        selectColor = ta.getColor(R.styleable.CircleIndicatorView_indicator_selectColor, Color.WHITE);
        normalColor = ta.getColor(R.styleable.CircleIndicatorView_indicator_normalColor, Color.GRAY);
        space = ta.getDimension(R.styleable.CircleIndicatorView_indicator_space, DisplayUtils.dpToPx(2));
        textColor = ta.getColor(R.styleable.CircleIndicatorView_indicator_normalColor, 0);
        int fillMode = ta.getInt(R.styleable.CircleIndicatorView_indicator_fillMode, 2);
        switch (fillMode) {
            case 0:
                mFillMode = FillMode.LETTER;
                break;
            case 1:
                mFillMode = FillMode.NUMBER;
                break;
            case 2:
                mFillMode = FillMode.NONE;
                break;
        }
        ta.recycle();
    }

}
