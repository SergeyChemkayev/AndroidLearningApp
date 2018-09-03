package com.example.androidlearningapp.data.api;

import com.example.androidlearningapp.data.api.listeners.GetMoviesListener;

public interface MoviesRemoteSource {

    void getMovies();
    void setGetMoviesListener(GetMoviesListener getMoviesListener);
}
