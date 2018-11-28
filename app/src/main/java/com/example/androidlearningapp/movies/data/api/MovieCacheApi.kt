package com.example.androidlearningapp.movies.data.api

import com.example.androidlearningapp.movies.entity.Movie

interface MovieCacheApi {

    fun putEntries(movies: List<Movie>)
    fun getEntries(): MutableList<Movie>
    fun removeEntries()
}