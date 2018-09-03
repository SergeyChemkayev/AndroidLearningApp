package com.example.androidlearningapp.data.api.listeners;

import com.example.androidlearningapp.entity.Movie;

import java.util.List;

public interface GetMoviesListener {
    void onGetMoviesSuccess(List<Movie> movies);

    void onGetMoviesError(Throwable error);
}
