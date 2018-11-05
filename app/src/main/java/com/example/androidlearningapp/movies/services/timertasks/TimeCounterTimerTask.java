package com.example.androidlearningapp.movies.services.timertasks;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import java.util.TimerTask;

public class TimeCounterTimerTask extends TimerTask {
    private Context context;
    private Handler mHandler = new Handler();
    private long startTime;

    public TimeCounterTimerTask(Context context, long startTime) {
        this.context = context;
        this.startTime = startTime;
    }


    @Override
    public void run() {
        new Thread(new Runnable() {
            public void run() {
                mHandler.post(new Runnable() {
                    public void run() {
                        long timeInMillis = System.currentTimeMillis() - startTime;
                        Toast.makeText(context, String.format("%02d:%02d:%02d", timeInMillis / 1000 / 3600, timeInMillis / 1000 / 60 % 60, timeInMillis / 1000 % 60), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }
}
