package com.mirdev.playmovieapp.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_movies_db")
data class FavoritesMoviesEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "poster") val poster_path: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean
)

