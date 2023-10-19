package com.mirdev.playmovieapp.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mirdev.playmovieapp.data.db.entities.FavoritesMoviesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {

    @Query("SELECT * FROM favorites_movies_db")
    fun getAllFavoriteMovies(): Flow<List<FavoritesMoviesEntity>>

    @Query("SELECT COUNT(*) FROM favorites_movies_Db WHERE id = :movieId")
    fun isFavorite(movieId: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavoriteMovie(movie: FavoritesMoviesEntity)

    @Delete
    fun deleteFavoriteMovie(movie: FavoritesMoviesEntity)


}