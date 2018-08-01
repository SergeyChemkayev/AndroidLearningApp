package com.example.androidlearningapp;

import android.app.Application;

import com.example.androidlearningapp.api.MoviesApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private static MoviesApi moviesApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://www.mocky.io/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        moviesApi = retrofit.create(MoviesApi.class);
    }

    public static MoviesApi getMoviesApi(){
        return moviesApi;
    }
}
