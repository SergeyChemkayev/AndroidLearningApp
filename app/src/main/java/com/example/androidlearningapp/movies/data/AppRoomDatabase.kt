package com.example.androidlearningapp.movies.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.example.androidlearningapp.movies.entity.RoomMovie

@Database(entities = arrayOf(RoomMovie::class), version = 1, exportSchema = false)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun movieDao(): RoomMoviesDao

    private object Holder {
        val INSTANCE = Room.databaseBuilder(AndroidLearningApplication.applicationContext(),
                AppRoomDatabase::class.java, "MovieRoomCache.db")
                .build()
    }

    companion object {
        val instance: AppRoomDatabase by lazy { Holder.INSTANCE }
    }
}