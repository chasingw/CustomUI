package com.robosense.customui.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.robosense.customui.R;

/**
 * Created by maxwell on 17-5-24.
 */

public class ScoreView extends View {

    private int   score;
    private float unitage;
    private Paint mPaintBlack;
    private Paint mPaintWhite;
    private int   mScoreColor;

    private RectF mRectF;
    private float arc_y = 0;
    private int   score_text;
    private float scoreTextSize;

    public ScoreView(Context context, int score) {
        super(context);
        init(score);
    }

    //    <attr name="scorenum" format="integer"/>
//        <attr name="scoreColor" format="reference|color"/>
//        <attr name="scoreSize" format="dimension"/>
    public ScoreView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        final TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ScoreView);
        score = ta.getInt(R.styleable.ScoreView_scorenum, 0);
        mScoreColor = ta.getColor(R.styleable.ScoreView_scoreColor, 0);
        scoreTextSize = ta.getDimension(R.styleable.ScoreView_scoreSize, 0);
        ta.recycle();
        init(score);
    }

    private void init(int score) {
        this.score = score;

        final Resources res = getResources();
        //以10dp为单位量
        unitage = res.getDimension(R.dimen.unitage);

        //初始化黑色笔
        mPaintBlack = new Paint();
        //设置抗锯齿
        mPaintBlack.setAntiAlias(true);
        //设置图像抖动
        mPaintBlack.setDither(true);
        //设置画笔颜色
        mPaintBlack.setColor(Color.BLACK);
        //设置画笔的风格为空心
        mPaintBlack.setStyle(Paint.Style.STROKE);
        //设置空心外框宽度为2dp
        mPaintBlack.setStrokeWidth(unitage * 0.2f);

        //初始白色笔
        mPaintWhite = new Paint();
        mPaintWhite.setAntiAlias(true);
        mPaintWhite.setStyle(Paint.Style.STROKE);
        mPaintWhite.setStrokeWidth(unitage * 0.2f);
        mPaintWhite.setDither(true);

        //设置文本的字号大小
        mPaintWhite.setTextSize(scoreTextSize);
        //设置文本的对齐方式为水平居中
        mPaintWhite.setTextAlign(Paint.Align.CENTER);
        mPaintWhite.setColor(mScoreColor);

        //初始化圆弧所需条件（以及设置圆弧的外接矩形的四边）
        mRectF = new RectF();
        mRectF.set(unitage * 0.5f, unitage * 0.5f, unitage * 18.5f, unitage * 18.5f);
        //设置整个控件的宽高
        setLayoutParams(new ViewGroup.LayoutParams((int) (unitage * 19.5f), (int) (unitage * 19.5f)));

        //获取该view的视图树观察者并添加绘制变化监听者
        this.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                new DrawThread();
                getViewTreeObserver().removeOnPreDrawListener(this);
                return false;
            }
        });

    }

    private class DrawThread implements Runnable {
        //2.开启子线程,并通过绘制监听实时更新绘制数据
        private final Thread mDrawThread;
        private       int    statek;
        int count;

        public DrawThread() {
            mDrawThread = new Thread(this);
            mDrawThread.start();
        }

        @Override
        public void run() {
            while (true) {
                switch (statek) {
                    case 0://给一点点缓冲的时间
                        try {
                            Thread.sleep(200);
                            statek = 1;
                        } catch (InterruptedException e) {

                        }
                        break;
                    case 1:
                        try {//更新显示的数据
                            Thread.sleep(20);
                            arc_y += 3.6f;
                            score_text++;
                            count++;
                            postInvalidate();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                }
                if (count >= score)//满足该条件就结束循环
                    break;
            }

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制自定义View
        canvas.drawArc(mRectF, 0, 360, false, mPaintBlack);
        canvas.drawArc(mRectF, -90, arc_y, false, mPaintWhite);
        //绘制文本
        canvas.drawText(score_text + "", unitage * 9.7f, unitage * 11.0f, mPaintWhite);

    }
}

