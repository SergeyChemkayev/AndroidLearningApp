package com.example.androidlearningapp.movies.data.api.listeners;

import com.example.androidlearningapp.movies.entity.Movie;

import java.util.List;

public interface GetMoviesListener {
    void onGetMoviesSuccess(List<Movie> movies);

    void onGetMoviesError(Throwable error);
}
