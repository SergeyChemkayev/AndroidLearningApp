package com.example.androidlearningapp.movies.data.api

import com.example.androidlearningapp.movies.entity.Movie

interface MovieCacheSource {

    fun putMovies(movies: List<Movie>)
    fun getMovies(): List<Movie>
    fun removeMovies()
}