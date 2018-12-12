package com.example.androidlearningapp.movies.data.useCase

import com.example.androidlearningapp.movies.data.api.MovieCacheSource
import com.example.androidlearningapp.movies.data.api.MovieRoomCacheManager
import com.example.androidlearningapp.movies.entity.Movie

class SaveMoviesCacheUseCase : UseCase<List<Movie>, Unit> {

    private val movieCacheManager: MovieCacheSource = MovieRoomCacheManager()

    override fun execute(request: List<Movie>) {
        movieCacheManager.putMovies(request)
    }

}