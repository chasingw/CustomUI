package com.robosense.customui.engine;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.robosense.customui.MainActivity;
import com.robosense.customui.widget.FloatCircleView;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by maxwell on 17-6-2.
 */

public class FloatViewManager {

    private        Context          mContext;
    private static FloatViewManager instance;
    //通过windowmanager操控浮窗的显示和隐藏以及位置的改变
    private final  WindowManager    mWindowManager;
    private final  FloatCircleView  mCircleView;
    private View.OnTouchListener mOnCirCleTouchListener = new View.OnTouchListener() {

        private float startx;
        private float starty;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    x0 = event.getRawX();
                    y0 = event.getRawY();
                    startx = event.getRawX();
                    starty = event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    mCircleView.setDragState(true);

                    float x = event.getRawX();
                    float y = event.getRawY();
                    float dx = x - startx;
                    float dy = y - starty;
                    mParams.x += dx;
                    mParams.y += dy;
                    mWindowManager.updateViewLayout(mCircleView, mParams);

                    

                    startx = x;
                    starty = y;
                    break;
                case MotionEvent.ACTION_UP:
                    mCircleView.setDragState(false);
                    //判断当前位置在左边还是右边
                    float x1 = event.getRawX();
                    float y1 = event.getRawY();
                    if (x1 < getScreenWidth() / 2) {

                        //左边
                        mParams.x = 0;
                    } else {

                        mParams.x = getScreenWidth() - mCircleView.width;
                    }
                    //动画
                    mWindowManager.updateViewLayout(mCircleView, mParams);

                    if (Math.abs(x1 - x0) > 6 || Math.abs(y1 - y0) > 6) {
                        return true;
                    } else {
                        return false;
                    }
                default:
                    break;

            }
            return false;
        }
    };
    private float x0;
    private float y0;
    private String packname = "com.robosense.mes";

    public int getScreenWidth() {

        return mWindowManager.getDefaultDisplay().getWidth();
    }

    private WindowManager.LayoutParams mParams;

    private FloatViewManager(Context context) {
        this.mContext = context;
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mCircleView = new FloatCircleView(mContext);
        mCircleView.setOnTouchListener(mOnCirCleTouchListener);
        mCircleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mParams.x += 200;
                if(mParams.x > (getScreenWidth() - mParams.width)) {
                    mParams.x = 0;
                }
                mWindowManager.updateViewLayout(mCircleView, mParams);

//                final Intent intent = new Intent(mContext, MainActivity.class);
//                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
//                mContext.startActivity(intent);
                Toast.makeText(mContext, "别点我", Toast.LENGTH_SHORT).show();


                //隐藏circleView 显示菜单栏 开启动画
//                PackageManager packageManager = mContext.getPackageManager();
//                if (checkPackInfo(packname)) {
//                    Intent intent = packageManager.getLaunchIntentForPackage(packname);
//                    mContext.startActivity(intent);
//                } else {
//                    Toast.makeText(mContext, "没有安装" + packname, 1).show();
//                }

            }
        });
    }
    /**
     * 检查包是否存在
     *
     * @param packname
     * @return
     */
    private boolean checkPackInfo(String packname) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = mContext.getPackageManager().getPackageInfo(packname, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }

    public static FloatViewManager getInstance(Context context) {
        if (instance == null) {
            synchronized (FloatViewManager.class) {
                if (instance == null) {
                    instance = new FloatViewManager(context);
                }
            }
        }
        return instance;
    }

    /**
     * 展示浮窗
     */
    public void showFloatCircleView() {

        mParams = new WindowManager.LayoutParams();
        mParams.width = mCircleView.width;
        mParams.height = mCircleView.height;
        mParams.gravity = Gravity.TOP | Gravity.LEFT;
        mParams.x = 0;
        mParams.y = 0;
        mParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        mParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        mParams.format = PixelFormat.RGBA_8888;

        mWindowManager.addView(mCircleView, mParams);
    }
}
