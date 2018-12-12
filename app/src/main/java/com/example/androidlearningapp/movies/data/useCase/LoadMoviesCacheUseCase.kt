package com.example.androidlearningapp.movies.data.useCase

import com.example.androidlearningapp.movies.data.api.MovieCacheSource
import com.example.androidlearningapp.movies.data.api.MovieRoomCacheManager
import com.example.androidlearningapp.movies.entity.Movie

class LoadMoviesCacheUseCase(
        private val movieCacheManager: MovieCacheSource = MovieRoomCacheManager()
) : UseCase<Unit, List<Movie>> {

    override fun execute(request: Unit): List<Movie> {
        return movieCacheManager.getMovies()
    }
}