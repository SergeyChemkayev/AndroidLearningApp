package com.example.androidlearningapp.movies.data.api;

import com.example.androidlearningapp.movies.data.api.listeners.GetMoviesListener;

public interface MoviesRemoteSource {

    void getMovies();
    void setGetMoviesListener(GetMoviesListener getMoviesListener);
}
