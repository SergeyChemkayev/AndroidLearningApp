package com.example.androidlearningapp.movies.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieRoomEntity(
        @ColumnInfo(name = "name") var name: String?,
        @ColumnInfo(name = "name_eng") var nameEng: String?,
        @ColumnInfo(name = "premiere") var premiere: String?,
        @ColumnInfo(name = "description") var description: String?,
        @ColumnInfo(name = "cover") var cover: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}