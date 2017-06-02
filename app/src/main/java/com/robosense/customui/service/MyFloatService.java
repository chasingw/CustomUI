package com.robosense.customui.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.robosense.customui.engine.FloatViewManager;

/**
 * Created by maxwell on 17-6-2.
 */

public class MyFloatService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        final FloatViewManager manager = FloatViewManager.getInstance(this);
        manager.showFloatCircleView();
    }
}
