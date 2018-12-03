package com.example.androidlearningapp.movies.data.api;

import com.example.androidlearningapp.movies.entity.MovieList;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MoviesNetwork implements MoviesRemoteSource {

    @Override
    public Single<MovieList> getMovieListObservable() {
        return NetworkClient.getRetrofit().create(MoviesApi.class)
                .movies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
