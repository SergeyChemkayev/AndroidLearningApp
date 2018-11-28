package com.example.androidlearningapp.movies.data.api

import android.content.Context
import com.example.androidlearningapp.movies.data.AppRoomDatabase
import com.example.androidlearningapp.movies.entity.Movie
import com.example.androidlearningapp.movies.entity.MovieRoomEntity

class MovieRoomCacheManager(context: Context) : MovieCacheApi {

    private val appRoomDataBaseInstance = AppRoomDatabase.getInstance(context)

    override fun putEntries(movies: List<Movie>) {
        appRoomDataBaseInstance?.movieDao()?.insertAll(*parseMoviesToMovieRoomEntities(movies))
    }

    override fun getEntries(): MutableList<Movie> {
        return parseMovieRoomEntitiesToMovies(appRoomDataBaseInstance?.movieDao()?.getAll())
    }

    override fun removeEntries() {
        appRoomDataBaseInstance?.movieDao()?.removeAll()
    }

    private fun parseMoviesToMovieRoomEntities(movies: List<Movie>): Array<MovieRoomEntity> {
        val movieRoomEntityList: MutableList<MovieRoomEntity> = mutableListOf()
        movies.forEach {
            movieRoomEntityList.add(MovieRoomEntity(
                    name = it.name,
                    nameEng = it.nameEng,
                    premiere = it.premiere,
                    description = it.description,
                    cover = it.image,
                    id = null))
        }
        return movieRoomEntityList.toTypedArray()
    }

    private fun parseMovieRoomEntitiesToMovies(moviesRoomEntities: List<MovieRoomEntity>?): MutableList<Movie> {
        val moviesList: MutableList<Movie> = mutableListOf()
        moviesRoomEntities?.forEach {
            moviesList.add(Movie(
                    it.name,
                    it.nameEng,
                    it.premiere,
                    it.description,
                    it.cover
            ))
        }
        return moviesList
    }
}