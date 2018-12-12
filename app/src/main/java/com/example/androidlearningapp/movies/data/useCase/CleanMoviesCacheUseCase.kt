package com.example.androidlearningapp.movies.data.useCase

import com.example.androidlearningapp.movies.data.api.MovieCacheSource
import com.example.androidlearningapp.movies.data.api.MovieRoomCacheManager

class CleanMoviesCacheUseCase(
        private val movieCacheManager: MovieCacheSource = MovieRoomCacheManager()
) : UseCase<Unit, Unit> {

    override fun execute(request: Unit) {
        movieCacheManager.removeMovies()
    }
}