package com.example.androidlearningapp.movies.data.useCase

import com.example.androidlearningapp.movies.data.api.MovieCacheSource
import com.example.androidlearningapp.movies.data.api.MovieRoomCacheManager
import com.example.androidlearningapp.movies.entity.Movie
import io.reactivex.Flowable

class LoadMoviesCacheUseCase(
        private val movieCacheManager: MovieCacheSource = MovieRoomCacheManager()
) : UseCase<Unit, Flowable<List<Movie>>> {

    override fun execute(input: Unit): Flowable<List<Movie>> {
        return movieCacheManager.getMovies()
    }
}