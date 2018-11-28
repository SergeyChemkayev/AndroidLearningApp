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
            "CREATE TABLE ${MovieEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${MovieEntry.COLUMN_NAME_NAME} TEXT," +
                    "${MovieEntry.COLUMN_NAME_NAME_ENG} TEXT," +
                    "${MovieEntry.COLUMN_NAME_PREMIERE} TEXT," +
                    "${MovieEntry.COLUMN_NAME_DESCRIPTION} TEXT," +
                    "${MovieEntry.COLUMN_NAME_COVER} TEXT)"

    const val SQL_SELECT_ALL_ENTRIES = "SELECT * FROM ${MovieEntry.TABLE_NAME}"
    const val SQL_DELETE_ALL_ENTRIES = "DELETE FROM ${MovieEntry.TABLE_NAME}"
}