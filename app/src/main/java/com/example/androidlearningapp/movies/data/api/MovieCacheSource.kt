package com.example.androidlearningapp.movies.data.api

import com.example.androidlearningapp.movies.entity.Movie
import io.reactivex.Flowable

interface MovieCacheSource {

    fun putMovies(movies: List<Movie>)
    fun getMovies(): Flowable<List<Movie>>
    fun removeMovies()
}