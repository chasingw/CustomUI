package com.robosense.customui.widget;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.robosense.customui.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by maxwell on 17-5-9.
 */

public class NewCalendar extends LinearLayout {

    private ImageView btnPrev;
    private ImageView btnNext;
    private TextView  tvDate;
    private GridView  grid;
    private Calendar curDate = Calendar.getInstance();

    public NewCalendar(Context context) {
        super(context);
    }

    public NewCalendar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initControl(context);
    }

    public NewCalendar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initControl(context);
    }

    private void initControl(Context context) {
        bindControl(context);
        bindControlEvent();
        renderCalendar();
    }

    private void bindControl(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.calendar_view, this, true);

        btnPrev = (ImageView) findViewById(R.id.btn_Prev);
        btnNext = (ImageView) findViewById(R.id.btn_next);
        tvDate = (TextView) findViewById(R.id.tv_date);
        grid = (GridView) findViewById(R.id.calendar_grid);
    }

    private void bindControlEvent() {
        btnPrev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                curDate.add(Calendar.MONTH, -1);
                renderCalendar();
            }
        });
        btnNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                curDate.add(Calendar.MONTH, 1);
                renderCalendar();
            }
        });

    }

    /**
     * 渲染
     */
    private void renderCalendar() {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM yyyy");
        tvDate.setText(simpleDateFormat.format(curDate.getTime()));

        //gridview
        final ArrayList<Date> cells = new ArrayList<>();
        Calendar calendar = (Calendar) curDate.clone();

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int prevDays = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        calendar.add(Calendar.DAY_OF_MONTH, -prevDays);

        int maxCellCount = 6 * 7;
        while (cells.size() < maxCellCount) {
            cells.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        grid.setAdapter(new CalendarAdapter(getContext(), cells));
    }

    private class CalendarAdapter extends ArrayAdapter<Date> {

        LayoutInflater mInflater;
        public CalendarAdapter(@NonNull Context context, ArrayList<Date> dates) {

            super(context, R.layout.item_calendar_day, dates);
            mInflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            final Date date = getItem(position);
            if(convertView == null ){
                convertView = mInflater.inflate(R.layout.item_calendar_day, parent, false);
            }
            int day = date.getDate();
            ((TextView)convertView).setText(String.valueOf(day));
            return convertView;
        }
    }
}
