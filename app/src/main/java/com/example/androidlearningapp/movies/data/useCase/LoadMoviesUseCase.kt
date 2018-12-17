package com.example.androidlearningapp.movies.data.useCase

import com.example.androidlearningapp.movies.data.api.MovieCacheSource
import com.example.androidlearningapp.movies.data.api.MovieRoomCacheManager
import com.example.androidlearningapp.movies.data.api.MoviesNetwork
import com.example.androidlearningapp.movies.data.api.MoviesRemoteSource
import com.example.androidlearningapp.movies.entity.Movie
import io.reactivex.Completable

class LoadMoviesUseCase(
        private val movieCacheManager: MovieCacheSource = MovieRoomCacheManager(),
        private val moviesRemoteSource: MoviesRemoteSource = MoviesNetwork.getInstance(),
        private var pageNumber: Int = 1
) : UseCase<Int, Completable> {

    override fun execute(input: Int): Completable {
        pageNumber = input
        return moviesRemoteSource.movieListSingle
                .doOnSuccess { this.saveMoviesCache(it.list) }
                .ignoreElement()
    }

    private fun saveMoviesCache(movies: List<Movie>) {
        if (pageNumber == 1) {
            movieCacheManager.removeMovies()
        }
        movieCacheManager.putMovies(movies)
    }
}