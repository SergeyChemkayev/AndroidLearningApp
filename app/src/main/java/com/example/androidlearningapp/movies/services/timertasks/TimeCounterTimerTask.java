package com.example.androidlearningapp.movies.services.timertasks;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
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
                        DateFormat formatter = new SimpleDateFormat("hh:mm:ss.SSS",Locale.GERMAN);
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(System.currentTimeMillis()-startTime);
                        Toast.makeText(context, formatter.format(calendar.getTime()), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }
}
