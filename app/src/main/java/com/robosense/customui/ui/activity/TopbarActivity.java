package com.robosense.customui.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.robosense.customui.R;
import com.robosense.customui.widget.TopBar;

public class TopbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topbar);

        TopBar topbar = (TopBar) findViewById(R.id.topbar);
        topbar.setOnTopbarClickListener(new TopBar.onTopbarClickListener() {
            @Override
            public void onLeftClick() {
                Toast.makeText(TopbarActivity.this, "click left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightClick() {

                Toast.makeText(TopbarActivity.this, "click right", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
