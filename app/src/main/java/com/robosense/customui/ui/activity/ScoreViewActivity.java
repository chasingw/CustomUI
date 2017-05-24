package com.robosense.customui.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.robosense.customui.R;
import com.robosense.customui.widget.ScoreView;

public class ScoreViewActivity extends AppCompatActivity {

    private LinearLayout scoreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_view);
//        scoreView = (LinearLayout) findViewById(R.id.score_View);
//        scoreView.addView(new ScoreView(this, 80));
    }
}
