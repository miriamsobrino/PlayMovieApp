package com.mirdev.playmovieapp.ui.detail

import androidx.lifecycle.ViewModel
import com.mirdev.playmovieapp.data.db.dao.FavoriteMovieDao
import com.mirdev.playmovieapp.data.db.entities.FavoritesMoviesEntity
import com.mirdev.playmovieapp.domain.model.detailMovieModel.DetailMovieModel
import com.mirdev.playmovieapp.domain.usecase.GetDetailMovieUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val getDetailMovieUseCase: GetDetailMovieUseCase,
    private val favoriteMovieDao: FavoriteMovieDao
) :
    ViewModel() {


    suspend fun fetchDetailMovie(movieId: Int, lang: String): DetailMovieModel {
        return getDetailMovieUseCase(movieId, lang)
    }

    suspend fun addToFavorites(movie: FavoritesMoviesEntity) {
        withContext(Dispatchers.IO) {
            val isFavorite = favoriteMovieDao.isFavorite(movie.id)
            if (!isFavorite)
                favoriteMovieDao.insertFavoriteMovie(movie)
        }
    }

    suspend fun removeFromFavorites(movie: FavoritesMoviesEntity) {
        withContext(Dispatchers.IO) {
            val isFavorite = favoriteMovieDao.isFavorite(movie.id)
            if (isFavorite)
                favoriteMovieDao.deleteFavoriteMovie(movie)
        }
    }

}