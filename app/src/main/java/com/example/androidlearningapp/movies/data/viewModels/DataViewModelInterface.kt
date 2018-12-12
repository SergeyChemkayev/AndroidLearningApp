package com.example.androidlearningapp.movies.data.viewModels

import com.example.androidlearningapp.movies.entity.Movie
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject

interface DataViewModelInterface {

    val isAbleToLoadMovies: Subject<Boolean>
    val isLoadingMore: Subject<Boolean>
    val isRefreshing: Subject<Boolean>
    val emptyViewVisibility: Subject<Boolean>
    val recyclerViewVisibility: Subject<Boolean>
    val isError: Subject<Unit>

    val moviesSubject: BehaviorSubject<List<Movie>>

    fun getMovies(refresh: Boolean)
    fun disposeMovies()
}