package com.example.androidlearningapp.movies.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.androidlearningapp.movies.entity.RoomMovie

@Dao
interface RoomMoviesDao {
    @Query(MovieCacheConstruct.SQL_SELECT_ALL_ENTRIES)
    fun getAll(): List<RoomMovie>

    @Query(MovieCacheConstruct.SQL_DELETE_ALL_ENTRIES)
    fun removeAll()

    @Insert
    fun insertAll(vararg movies: RoomMovie)
}