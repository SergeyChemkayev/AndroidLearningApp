package com.example.androidlearningapp.movies.data.useCase

import com.example.androidlearningapp.movies.data.api.MovieCacheSource
import com.example.androidlearningapp.movies.data.api.MovieRoomCacheManager
import com.example.androidlearningapp.movies.entity.Movie

class SaveMoviesCacheUseCase(
        private val movieCacheManager: MovieCacheSource = MovieRoomCacheManager()
) : UseCase<List<Movie>, Unit> {

    override fun execute(request: List<Movie>) {
        movieCacheManager.putMovies(request)
    }

}