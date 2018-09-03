package com.example.androidlearningapp.data.api;

import android.support.annotation.NonNull;

import com.example.androidlearningapp.data.api.listeners.GetMoviesListener;
import com.example.androidlearningapp.entity.ResultList;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesNetwork implements MoviesRemoteSource {
    private static final int TIMEOUT_SECONDS = 20;
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
                .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .build();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat("yyyy-MM-dd") .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MoviesApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
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
