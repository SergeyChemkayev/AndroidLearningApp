package com.example.androidlearningapp.movies.data.useCase

import com.example.androidlearningapp.movies.data.api.MovieCacheSource
import com.example.androidlearningapp.movies.data.api.MovieRoomCacheManager

class CleanMoviesCacheUseCase : UseCase<Unit, Unit> {

    private val movieCacheManager: MovieCacheSource = MovieRoomCacheManager()

    override fun execute(request: Unit) {
        movieCacheManager.removeMovies()
    }
}