package com.example.androidlearningapp.movies.data.api;

import android.util.Log;

import com.example.androidlearningapp.movies.data.listeners.GetMoviesListener;
import com.example.androidlearningapp.movies.entity.MovieList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MoviesNetwork implements MoviesRemoteSource {
    private GetMoviesListener getMoviesListener;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void setGetMoviesListener(GetMoviesListener getMoviesListener) {
        this.getMoviesListener = getMoviesListener;
    }

    @Override
    public void getMovies() {
        compositeDisposable.add(getMovieListObservable().subscribeWith(getMovieListObserver()));
    }

    @Override
    public void dispose() {
        compositeDisposable.dispose();
    }

    private Observable<MovieList> getMovieListObservable() {
        return NetworkClient.getRetrofit().create(MoviesApi.class)
                .movies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<MovieList> getMovieListObserver() {
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
