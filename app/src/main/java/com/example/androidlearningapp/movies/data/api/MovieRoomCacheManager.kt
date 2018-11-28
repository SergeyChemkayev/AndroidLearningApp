package com.example.androidlearningapp.movies.data.api

import com.example.androidlearningapp.movies.data.AppRoomDatabase
import com.example.androidlearningapp.movies.entity.Movie
import com.example.androidlearningapp.movies.entity.MovieRoomEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MovieRoomCacheManager : MovieCacheApi {

    private val appRoomDataBaseDao = AppRoomDatabase.instance.movieDao()

    override fun putMovies(movies: List<Movie>) {
        GlobalScope.launch {
            appRoomDataBaseDao.insertAll(*parseMoviesToMovieRoomEntities(movies))
        }
    }

    override fun getMovies(): List<Movie>? {
        return runBlocking { GlobalScope.async { parseMovieRoomEntitiesToMovies(appRoomDataBaseDao.getAll()) }.await() }
    }

    override fun removeMovies() {
        GlobalScope.launch {
            appRoomDataBaseDao.removeAll()
        }
    }

    private fun parseMoviesToMovieRoomEntities(movies: List<Movie>): Array<MovieRoomEntity> {
        return movies.map {
            MovieRoomEntity(
                    name = it.name,
                    nameEng = it.nameEng,
                    premiere = it.premiere,
                    description = it.description,
                    cover = it.image
            )
        }.toTypedArray()
    }

    private fun parseMovieRoomEntitiesToMovies(moviesRoomEntities: List<MovieRoomEntity>?): List<Movie>? {
        return moviesRoomEntities?.map {
            Movie(
                    it.name,
                    it.nameEng,
                    it.premiere,
                    it.description,
                    it.cover
            )
        }
    }
}