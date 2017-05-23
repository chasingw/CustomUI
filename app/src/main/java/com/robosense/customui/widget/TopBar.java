package com.robosense.customui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.robosense.customui.R;

/**
 * Created by maxwell on 17-5-23.
 */

public class TopBar extends RelativeLayout{

    private Button btnLeftText;
    private Button btnRightText;
    private TextView tvTitle;

    private String title;
    private int titleColor;
    private float titleTextSize;

    private String   leftText;
    private int      leftTextColor;
    private Drawable leftTextBackgroundColor;

    private String rightText;
    private int rightTextColor;
    private Drawable rightTextBackgroundColor;

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        final TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        title = ta.getString(R.styleable.TopBar_title);
        titleColor = ta.getColor(R.styleable.TopBar_titleTextColor, 0);
        titleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize, 0);

        leftText = ta.getString(R.styleable.TopBar_leftText);
        leftTextColor = ta.getColor(R.styleable.TopBar_leftTextColor, 0);
        leftTextBackgroundColor = ta.getDrawable(R.styleable.TopBar_leftTextBackground);

        rightText = ta.getString(R.styleable.TopBar_rightText);
        rightTextColor = ta.getColor(R.styleable.TopBar_rightTextColor, 0);
        rightTextBackgroundColor = ta.getDrawable(R.styleable.TopBar_rightTextBackground);

        ta.recycle();

        tvTitle = new TextView(context);
        btnLeftText = new Button(context);
        btnRightText = new Button(context);

        tvTitle.setText(title);
        tvTitle.setTextColor(titleColor);
        tvTitle.setTextSize(titleTextSize);

        btnLeftText.setText(leftText);
        btnLeftText.setTextColor(leftTextColor);
        btnLeftText.setBackground(leftTextBackgroundColor);

        btnRightText.setText(rightText);
        btnRightText.setTextColor(rightTextColor);
        btnRightText.setBackground(rightTextBackgroundColor);

        setBackgroundColor(0xff55ffaa);

        final LayoutParams tvlayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvlayoutParams.addRule(CENTER_IN_PARENT, TRUE);

        final LayoutParams leftlayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftlayoutParams.addRule(ALIGN_PARENT_LEFT, TRUE);


        final LayoutParams rightlayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightlayoutParams.addRule(ALIGN_PARENT_RIGHT, TRUE);

        addView(tvTitle, tvlayoutParams);
        addView(btnLeftText, leftlayoutParams);
        addView(btnRightText, rightlayoutParams);

    }
}
