package com.example.androidlearningapp.movies.data

import android.provider.BaseColumns

object MovieCacheConstruct{
    object MovieEntry:BaseColumns{
        const val TABLE_NAME = "movies"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_NAME_ENG = "name_eng"
        const val COLUMN_NAME_PREMIERE = "premiere"
        const val COLUMN_NAME_DESCRIPTION = "description"
        const val COLUMN_NAME_COVER = "cover"
    }

}