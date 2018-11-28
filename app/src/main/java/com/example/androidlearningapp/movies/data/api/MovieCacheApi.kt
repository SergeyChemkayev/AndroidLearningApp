package com.example.androidlearningapp.movies.data.api

import com.example.androidlearningapp.movies.entity.Movie

interface MovieCacheApi {

    fun putMovies(movies: List<Movie>)
    fun getMovies(): List<Movie>?
    fun removeMovies()
}