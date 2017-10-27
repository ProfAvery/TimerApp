package edu.fullerton.ecs.timerapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

/**
 * Created by AD\kavery on 10/26/17.
 */

public class TimerService extends Service {

    private TimerApplication app;

    @Override
    public void onCreate() {
        super.onCreate();

        app = (TimerApplication) getApplication();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
