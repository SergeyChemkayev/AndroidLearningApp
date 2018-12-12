package com.example.androidlearningapp.movies.data.viewModels

import com.example.androidlearningapp.movies.entity.MovieElement
import io.reactivex.Observable

interface DataViewModelInterface {

    val isAbleToLoadMovies: Observable<Boolean>
    val isLoadingMore: Observable<Boolean>
    val isRefreshing: Observable<Boolean>
    val emptyViewVisibility: Observable<Boolean>
    val recyclerViewVisibility: Observable<Boolean>
    val isError: Observable<Unit>

    val moviesSubject: Observable<List<MovieElement>>

    fun getMovies(refresh: Boolean)
    fun disposeMovies()
}