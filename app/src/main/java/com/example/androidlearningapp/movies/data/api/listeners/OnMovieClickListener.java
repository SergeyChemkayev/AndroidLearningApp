package com.example.androidlearningapp.movies.data.api.listeners;

import android.view.View;

import com.example.androidlearningapp.movies.entity.Movie;

public interface OnMovieClickListener {

    void onMovieClick(Movie movie, View coverView, View descriptionView);
}
