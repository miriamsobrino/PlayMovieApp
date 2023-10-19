package com.mirdev.playmovieapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mirdev.playmovieapp.data.db.dao.FavoriteMovieDao
import com.mirdev.playmovieapp.data.db.entities.FavoritesMoviesEntity

@Database(entities = [FavoritesMoviesEntity::class], version = 1,exportSchema = false)
abstract class FavoritesMoviesFinalDataBase: RoomDatabase() {

    abstract fun getfavoriteMovieDao(): FavoriteMovieDao


}