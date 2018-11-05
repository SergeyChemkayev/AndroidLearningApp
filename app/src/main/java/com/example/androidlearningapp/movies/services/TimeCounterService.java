package com.example.androidlearningapp.movies.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.androidlearningapp.movies.services.timertasks.TimeCounterTimerTask;

import java.util.Timer;
import java.util.TimerTask;

public class TimeCounterService extends Service {
    Timer timer;

    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer();
        TimerTask showToast = new TimeCounterTimerTask(this, System.currentTimeMillis());
        timer.scheduleAtFixedRate(showToast, 0, 2000);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Service stoped",Toast.LENGTH_SHORT).show();
        timer.cancel();
    }
}
