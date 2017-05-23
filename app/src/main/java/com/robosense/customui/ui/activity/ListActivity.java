package com.robosense.customui.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.robosense.customui.R;
import com.robosense.customui.widget.FilterBar;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private FilterBar mFilterBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mFilterBar = (FilterBar) findViewById(R.id.filterbar);
        final ArrayList<String> datas = new ArrayList<>();
        datas.add("往昔强");
        datas.add("往昔强");
        datas.add("往昔强");
        datas.add("往昔强");
        datas.add("往昔强");
        datas.add("往昔强");
        datas.add("往昔强");
        datas.add("往昔强");
        mFilterBar.setFilterData(datas);
    }
}
