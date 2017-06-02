package com.robosense.customui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.robosense.customui.service.MyFloatService;
import com.robosense.customui.ui.activity.BottomNavigationActivity;
import com.robosense.customui.ui.activity.CalculateActivity;
import com.robosense.customui.ui.activity.ConstraintActivity;
import com.robosense.customui.ui.activity.IndicatorActivity;
import com.robosense.customui.ui.activity.ScoreViewActivity;
import com.robosense.customui.ui.activity.TopbarActivity;

import com.robosense.customui.ui.activity.ListActivity;
import com.robosense.customui.ui.activity.ScoreViewActivity;
import com.robosense.customui.ui.activity.TopbarActivity;


public class MainActivity extends AppCompatActivity {

    private static final int OVERLAY_PERMISSION_CODE = 1111;
    private       boolean askedForOverlayPermission;
    private  String  packname = "com.robosense.mes";

    private static final String TAG = "MainActivity";

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
        findViewById(R.id.btn_scoreview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScoreViewActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_circleindicator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IndicatorActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_constraintlayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConstraintActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!Settings.canDrawOverlays(MainActivity.this)) {
                        askedForOverlayPermission = true;
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                        startActivityForResult(intent, OVERLAY_PERMISSION_CODE);
                        Toast.makeText(MainActivity.this, "请打开显示权限", Toast.LENGTH_SHORT).show();
                    } else {
                        startService();
                    }
                } else {
                    startService();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OVERLAY_PERMISSION_CODE) {
            askedForOverlayPermission = false;
            if (Settings.canDrawOverlays(this)) {
                // SYSTEM_ALERT_WINDOW permission not granted...
                //Toast.makeText(MyProtector.getContext(), "ACTION_MANAGE_OVERLAY_PERMISSION Permission Granted", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "-- startService --");
                startService();

            } else {
                Toast.makeText(MainActivity.this, "ACTION_MANAGE_OVERLAY_PERMISSION Permission Denied", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void startService() {
        Intent intent = new Intent(MainActivity.this, MyFloatService.class);
        startService(intent);
        finish();
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
            packageInfo = getPackageManager().getPackageInfo(packname, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }
}
