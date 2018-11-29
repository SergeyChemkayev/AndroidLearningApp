package com.example.androidlearningapp.movies.data.api

import com.example.androidlearningapp.movies.data.AppRoomDatabase
import com.example.androidlearningapp.movies.entity.Movie
import com.example.androidlearningapp.movies.entity.RoomMovie
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MovieRoomCacheManager : MovieCacheSource {

    private val appRoomDataBaseDao = AppRoomDatabase.instance.movieDao()

    override fun putMovies(movies: List<Movie>) {
        GlobalScope.launch {
            appRoomDataBaseDao.insertAll(*parseMoviesToRoomMovies(movies))
        }
    }

    override fun getMovies(): List<Movie> {
        return runBlocking { GlobalScope.async { parseRoomMoviesToMovies(appRoomDataBaseDao.getAll()) }.await() }
    }

    override fun removeMovies() {
        runBlocking {
            GlobalScope.launch {
                appRoomDataBaseDao.removeAll()
            }.join()
        }
    }

    private fun parseMoviesToRoomMovies(movies: List<Movie>): Array<RoomMovie> {
        return movies.map {
            it.toRoomMovie()
        }.toTypedArray()
    }

    private fun parseRoomMoviesToMovies(roomMovies: List<RoomMovie>): List<Movie> {
        return roomMovies.map {
            it.toMovie()
        }
    }

    private fun Movie.toRoomMovie(): RoomMovie = RoomMovie(name, nameEng, premiere, description, image)
    private fun RoomMovie.toMovie(): Movie = Movie(name, nameEng, premiere, description, cover)
}
