package com.example.androidlearningapp.movies.data.api;

import com.example.androidlearningapp.movies.entity.MovieList;

import io.reactivex.Observable;

public interface MoviesRemoteSource {

    Observable<MovieList> getMovieListSingle();
}
