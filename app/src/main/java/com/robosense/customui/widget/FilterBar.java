package com.robosense.customui.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.robosense.customui.R;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by maxwell on 17-5-11.
 */

public class FilterBar extends LinearLayout {

    private RecyclerView      mRecyclerView;
    private Button            mBtnFilter;
    private FilterAdapter     mFilterAdapter;
    private ArrayList<String> selectedDatas;
    private ArrayList<String> allDatas;
    Context mContext;
    private PopupWindow mPopupWindow;
    private LinearLayout mLlFilter;

    public FilterBar(Context context) {
        super(context);
        this.mContext = context;
    }

    public FilterBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initControl();
    }

    public FilterBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initControl();
    }


    public void setFilterData(ArrayList<String> datas) {
        this.selectedDatas = datas;
        mFilterAdapter = new FilterAdapter(mContext, datas);
        mRecyclerView.setAdapter(mFilterAdapter);
    }

    public void setAllDatas(ArrayList<String> allDatas) {
        this.allDatas = allDatas;
    }

    private void initControl() {
        bindControl();
        bindControlEvent();
        renderCalendar();
    }

    private void renderCalendar() {

    }

    private void bindControlEvent() {
        mBtnFilter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mPopupWindow != null && mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                } else {
                    Toast.makeText(mContext, "筛选", Toast.LENGTH_SHORT).show();
                    //popwindows 选择数据
                    // Initialize a new instance of LayoutInflater service
                    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

                    // Inflate the custom layout/view
                    View customView = inflater.inflate(R.layout.custom_layout, null);

                /*
                    public PopupWindow (View contentView, int width, int height)
                        Create a new non focusable popup window which can display the contentView.
                        The dimension of the window must be passed to this constructor.

                        The popup does not provide any background. This should be handled by
                        the content view.

                    Parameters
                        contentView : the popup's content
                        width : the popup's width
                        height : the popup's height
                */
                    // Initialize a new instance of popup window
                    mPopupWindow = new PopupWindow(
                            customView,
                            LayoutParams.WRAP_CONTENT,
                            LayoutParams.WRAP_CONTENT
                    );

                    // Set an elevation value for popup window
                    // Call requires API level 21
                    if (Build.VERSION.SDK_INT >= 21) {
                        mPopupWindow.setElevation(5.0f);
                    }

                    // Get a reference for the custom view close button
                    ImageButton closeButton = (ImageButton) customView.findViewById(R.id.ib_close);

                    // Set a click listener for the popup window close button
                    closeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Dismiss the popup window
                            mPopupWindow.dismiss();
                        }
                    });

                /*
                    public void showAtLocation (View parent, int gravity, int x, int y)
                        Display the content view in a popup window at the specified location. If the
                        popup window cannot fit on screen, it will be clipped.
                        Learn WindowManager.LayoutParams for more information on how gravity and the x
                        and y parameters are related. Specifying a gravity of NO_GRAVITY is similar
                        to specifying Gravity.LEFT | Gravity.TOP.

                    Parameters
                        parent : a parent view to get the getWindowToken() token from
                        gravity : the gravity which controls the placement of the popup window
                        x : the popup's x location offset
                        y : the popup's y location offset
                */
                    // Finally, show the popup window at the center location of root relative layout
                    mPopupWindow.showAsDropDown(mLlFilter, 0, 0, 0);
                }
            }
        });
    }

    private void bindControl() {
        final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        layoutInflater.inflate(R.layout.activity_filter_bar, this, true);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_filter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));

        mBtnFilter = (Button) findViewById(R.id.btn_filter);
        mLlFilter = (LinearLayout) findViewById(R.id.ll_filter);
    }

}
