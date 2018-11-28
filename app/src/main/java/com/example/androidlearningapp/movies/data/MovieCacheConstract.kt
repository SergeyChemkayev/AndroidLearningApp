package com.example.androidlearningapp.movies.data

import android.provider.BaseColumns

object MovieCacheConstruct {
    object MovieEntry : BaseColumns {
        const val TABLE_NAME = "movies"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_NAME_ENG = "name_eng"
        const val COLUMN_NAME_PREMIERE = "premiere"
        const val COLUMN_NAME_DESCRIPTION = "description"
        const val COLUMN_NAME_COVER = "cover"
    }

    const val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${MovieCacheConstruct.MovieEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${MovieCacheConstruct.MovieEntry.COLUMN_NAME_NAME} TEXT," +
                    "${MovieCacheConstruct.MovieEntry.COLUMN_NAME_NAME_ENG} TEXT," +
                    "${MovieCacheConstruct.MovieEntry.COLUMN_NAME_PREMIERE} TEXT," +
                    "${MovieCacheConstruct.MovieEntry.COLUMN_NAME_DESCRIPTION} TEXT," +
                    "${MovieCacheConstruct.MovieEntry.COLUMN_NAME_COVER} TEXT)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${MovieCacheConstruct.MovieEntry.TABLE_NAME}"
}