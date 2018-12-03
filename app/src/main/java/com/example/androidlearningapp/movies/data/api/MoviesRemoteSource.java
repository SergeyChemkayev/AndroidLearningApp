package com.example.androidlearningapp.movies.data.api;

import com.example.androidlearningapp.movies.entity.MovieList;

import io.reactivex.Single;

public interface MoviesRemoteSource {

    Single<MovieList> getMovieListObservable();
}
