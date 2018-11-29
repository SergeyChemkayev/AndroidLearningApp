package com.example.androidlearningapp.movies.data.api

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns
import android.util.Log
import com.example.androidlearningapp.movies.data.MovieCacheConstruct.MovieEntry
import com.example.androidlearningapp.movies.data.MovieCacheDbHelper
import com.example.androidlearningapp.movies.entity.Movie

class MovieCacheManager(context: Context) : MovieCacheSource {

    private val dbHelper = MovieCacheDbHelper(context)

    override fun putMovies(movies: List<Movie>) {
        val db = dbHelper.writableDatabase
        movies.forEach {
            val values = ContentValues().apply {
                put(MovieEntry.COLUMN_NAME_NAME, it.name)
                put(MovieEntry.COLUMN_NAME_NAME_ENG, it.nameEng)
                put(MovieEntry.COLUMN_NAME_PREMIERE, it.premiere)
                put(MovieEntry.COLUMN_NAME_DESCRIPTION, it.description)
                put(MovieEntry.COLUMN_NAME_COVER, it.image)
            }
            db.insert(MovieEntry.TABLE_NAME, null, values)
        }
    }

    override fun getMovies(): List<Movie> {
        val db = dbHelper.readableDatabase
        val projection = arrayOf(BaseColumns._ID, MovieEntry.COLUMN_NAME_NAME, MovieEntry.COLUMN_NAME_NAME_ENG, MovieEntry.COLUMN_NAME_PREMIERE, MovieEntry.COLUMN_NAME_DESCRIPTION, MovieEntry.COLUMN_NAME_COVER)
        val cursor = db.query(
                MovieEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        )
        return parseCursorToMovies(cursor)
    }

    override fun removeMovies() {
        val db = dbHelper.writableDatabase
        db.delete(MovieEntry.TABLE_NAME, null, null)
    }

    private fun parseCursorToMovies(cursor: Cursor): MutableList<Movie> {
        val moviesList: MutableList<Movie> = mutableListOf()
        with(cursor) {
            while (moveToNext()) {
                Log.d("SQL", getLong(getColumnIndexOrThrow(BaseColumns._ID)).toString() + " || " + getString(getColumnIndexOrThrow(MovieEntry.COLUMN_NAME_NAME)) + "\n")
                val movie = Movie(
                        getString(getColumnIndexOrThrow(MovieEntry.COLUMN_NAME_NAME)),
                        getString(getColumnIndexOrThrow(MovieEntry.COLUMN_NAME_NAME_ENG)),
                        getString(getColumnIndexOrThrow(MovieEntry.COLUMN_NAME_PREMIERE)),
                        getString(getColumnIndexOrThrow(MovieEntry.COLUMN_NAME_DESCRIPTION)),
                        getString(getColumnIndexOrThrow(MovieEntry.COLUMN_NAME_COVER)))
                moviesList.add(movie)
            }
        }
        return moviesList
    }
}