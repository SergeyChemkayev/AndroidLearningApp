package com.example.androidlearningapp.services;

import com.example.androidlearningapp.listeners.GetMoviesListener;

public interface MoviesRemoteSource {

    void getMovies();
    void setGetMoviesListener(GetMoviesListener getMoviesListener);
}
