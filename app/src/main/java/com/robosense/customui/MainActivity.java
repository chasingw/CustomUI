package com.robosense.customui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.robosense.customui.ui.activity.BottomNavigationActivity;
import com.robosense.customui.ui.activity.CalculateActivity;
import com.robosense.customui.ui.activity.ListActivity;
import com.robosense.customui.ui.activity.TopbarActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnNavi = (Button) findViewById(R.id.btn_navi);
        btnNavi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BottomNavigationActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_customer_calculator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalculateActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_filter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_topbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TopbarActivity.class);
                startActivity(intent);
            }
        });
    }
}
