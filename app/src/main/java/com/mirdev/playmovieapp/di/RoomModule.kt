package com.mirdev.playmovieapp.di

import android.content.Context
import androidx.room.Room
import com.mirdev.playmovieapp.data.db.FavoritesMoviesFinalDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val FAVORITES_MOVIES_DATABASE_NAME = "favorites_movies_database"

    @Singleton
    @Provides
    fun providerRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            FavoritesMoviesFinalDataBase::class.java,
            FAVORITES_MOVIES_DATABASE_NAME
        ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideFavoritesMoviesDao(db: FavoritesMoviesFinalDataBase) = db.getfavoriteMovieDao()


}