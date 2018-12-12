package com.example.androidlearningapp.movies.data.useCase

import com.example.androidlearningapp.movies.data.api.MoviesNetwork
import com.example.androidlearningapp.movies.data.api.MoviesRemoteSource
import com.example.androidlearningapp.movies.entity.Movie
import io.reactivex.Single

class LoadMoviesUseCase : UseCase<Unit, Single<List<Movie>>> {

    override fun execute(request: Unit): Single<List<Movie>> {
        val moviesRemoteSource: MoviesRemoteSource = MoviesNetwork.getInstance()
        return moviesRemoteSource.movieListSingle
                .map { it.list }
    }
}