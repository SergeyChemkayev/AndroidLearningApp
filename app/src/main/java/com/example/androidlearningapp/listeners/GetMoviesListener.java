package com.example.androidlearningapp.listeners;

import com.example.androidlearningapp.movieitems.Movie;

import java.util.List;

public interface GetMoviesListener {
    void onGetMoviesSuccess(List<Movie> movies);

    void onGetMoviesError(Throwable error);
}
