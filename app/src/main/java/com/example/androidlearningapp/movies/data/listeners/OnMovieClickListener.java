package com.example.androidlearningapp.movies.data.listeners;

import android.view.View;

import com.example.androidlearningapp.movies.entity.Movie;

public interface OnMovieClickListener {

    void onMovieClick(Movie movie, View coverView, View descriptionView);
}
