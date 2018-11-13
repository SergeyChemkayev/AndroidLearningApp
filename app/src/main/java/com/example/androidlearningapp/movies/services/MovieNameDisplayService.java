package com.example.androidlearningapp.movies.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.androidlearningapp.movies.entity.Movie;

public class MovieNameDisplayService extends Service {

    private MyBinder binder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        showToast((Movie) intent.getParcelableExtra("movie"));
        return binder;
    }

    public void showToast(Movie movie) {
        Toast.makeText(this, movie.getName(), Toast.LENGTH_SHORT).show();
    }

    public class MyBinder extends Binder {
        public MovieNameDisplayService getService() {
            return MovieNameDisplayService.this;
        }
    }
}
