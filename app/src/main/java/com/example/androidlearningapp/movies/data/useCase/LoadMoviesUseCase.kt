package com.example.androidlearningapp.movies.data.useCase

import com.example.androidlearningapp.movies.data.api.MoviesNetwork
import com.example.androidlearningapp.movies.data.api.MoviesRemoteSource
import com.example.androidlearningapp.movies.entity.Movie
import io.reactivex.Observable

class LoadMoviesUseCase : UseCase<Unit, Observable<List<Movie>>> {

    override fun execute(request: Unit): Observable<List<Movie>> {
        val moviesRemoteSource: MoviesRemoteSource = MoviesNetwork.getInstance()
        return moviesRemoteSource.movieListSingle
                .map { it.list }
    }
}