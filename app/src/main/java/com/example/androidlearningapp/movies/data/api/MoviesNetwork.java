package com.example.androidlearningapp.movies.data.api;

import android.util.Log;

import com.example.androidlearningapp.movies.data.listeners.GetMoviesListener;
import com.example.androidlearningapp.movies.entity.MovieList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MoviesNetwork implements MoviesRemoteSource {
    private GetMoviesListener getMoviesListener;

    public void setGetMoviesListener(GetMoviesListener getMoviesListener) {
        this.getMoviesListener = getMoviesListener;
    }

    public void getMovies() {
        getObservable().subscribeWith(getObserver());
    }

    private Observable<MovieList> getObservable() {
        return NetworkClient.getRetrofit().create(MoviesApi.class)
                .movies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<MovieList> getObserver() {
        return new DisposableObserver<MovieList>() {
            @Override
            public void onNext(MovieList movieList) {
                if (movieList != null && getMoviesListener != null) {
                    getMoviesListener.onGetMoviesSuccess(movieList.getList());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (getMoviesListener != null) {
                    getMoviesListener.onGetMoviesError(e);
                }
            }

            @Override
            public void onComplete() {
                Log.d("RX", "Completed");
            }
        };
    }
}
