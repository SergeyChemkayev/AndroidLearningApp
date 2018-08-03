package com.example.androidlearningapp.services.impl;

import android.support.annotation.NonNull;

import com.example.androidlearningapp.listeners.GetMoviesListener;
import com.example.androidlearningapp.ResultList;
import com.example.androidlearningapp.api.MoviesApi;
import com.example.androidlearningapp.services.MoviesRemoteSource;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesNetwork implements MoviesRemoteSource {
    private static final String BASE_URL = "http://www.mocky.io/v2/";
    private static volatile MoviesRemoteSource INSTANCE = null;
    private MoviesApi moviesApi;
    private GetMoviesListener getMoviesListener;

    public static MoviesRemoteSource getInstance() {
        MoviesRemoteSource moviesRemoteSource = INSTANCE;
        if (moviesRemoteSource == null) {
            synchronized (MoviesNetwork.class) {
                moviesRemoteSource = INSTANCE;
                if (moviesRemoteSource == null) {
                    INSTANCE = moviesRemoteSource = new MoviesNetwork();
                }
            }
        }
        return moviesRemoteSource;
    }

    private MoviesNetwork() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        moviesApi = retrofit.create(MoviesApi.class);
    }

    @Override
    public void setGetMoviesListener(GetMoviesListener getMoviesListener) {
        this.getMoviesListener = getMoviesListener;
    }

    @Override
    public void getMovies() {
        moviesApi.movies().enqueue(new Callback<ResultList>() {
            @Override
            public void onResponse(@NonNull Call<ResultList> call, @NonNull Response<ResultList> response) {
                ResultList resultList = response.body();
                if (resultList != null && getMoviesListener != null) {
                    getMoviesListener.onGetMoviesSuccess(resultList.getList());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResultList> call, @NonNull Throwable t) {
                if (getMoviesListener != null) {
                    getMoviesListener.onGetMoviesError(t);
                }
            }
        });
    }


}
