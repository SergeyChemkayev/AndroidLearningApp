package com.example.androidlearningapp.movies.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.androidlearningapp.movies.entity.MovieRoomEntity

@Database(entities = arrayOf(MovieRoomEntity::class), version = 1)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun movieDao(): MoviesRoomDao

    companion object {
        private var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(AppRoomDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            AppRoomDatabase::class.java, "MovieRoomCache.db")
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}