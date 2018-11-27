package com.example.androidlearningapp.movies.data.api

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns
import android.util.Log
import com.example.androidlearningapp.movies.data.MovieCacheConstruct.MovieEntry
import com.example.androidlearningapp.movies.data.MovieCacheDbHelper
import com.example.androidlearningapp.movies.entity.Movie

class MovieCacheManager(private var context: Context) {

    private val dbHelper = MovieCacheDbHelper(context)

    fun putEntries(movies: List<Movie>) {
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

    fun getEntries(): MutableList<Movie> {
        val db = dbHelper.readableDatabase
        val projection = arrayOf(BaseColumns._ID, MovieEntry.COLUMN_NAME_NAME)
        val cursor = db.query(
                MovieEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        )
        var moviesList: MutableList<Movie>
        with(cursor) {
            while (moveToNext()) {
                Log.d("SQL", getLong(getColumnIndexOrThrow(BaseColumns._ID)).toString() + "||" + getString(getColumnIndexOrThrow(MovieEntry.COLUMN_NAME_NAME)) + "\n")
            }
        }
        return moviesList
    }

    fun removeEntries() {
        val db = dbHelper.writableDatabase
        db.delete(MovieEntry.TABLE_NAME, null, null)
    }

}