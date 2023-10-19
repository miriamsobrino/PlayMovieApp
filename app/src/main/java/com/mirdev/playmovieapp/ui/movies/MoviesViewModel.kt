package com.mirdev.playmovieapp.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirdev.playmovieapp.data.db.dao.FavoriteMovieDao
import com.mirdev.playmovieapp.data.db.entities.FavoritesMoviesEntity
import com.mirdev.playmovieapp.domain.model.homeMoviesModel.HomeMoviesModel
import com.mirdev.playmovieapp.domain.usecase.GetPopularMoviesUseCase
import com.mirdev.playmovieapp.domain.usecase.GetRecommendationsMoviesUseCase
import com.mirdev.playmovieapp.domain.usecase.GetUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getRecommendationsMoviesUseCase: GetRecommendationsMoviesUseCase,
    private val favoriteMovieDao: FavoriteMovieDao
) :
    ViewModel() {
    private var _upcomingMovies = MutableStateFlow<List<HomeMoviesModel>>(emptyList())
    val upcomingMovies: StateFlow<List<HomeMoviesModel>> = _upcomingMovies

    private var _popularMovies = MutableStateFlow<List<HomeMoviesModel>>(emptyList())
    val popularMovies: StateFlow<List<HomeMoviesModel>> = _popularMovies

    private var _recommendationsMovies = MutableStateFlow<List<HomeMoviesModel>>(emptyList())
    val recommendationsMovies: StateFlow<List<HomeMoviesModel>> = _recommendationsMovies

    private var _loading = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean> = _loading
    val favoritesMovies: Flow<List<FavoritesMoviesEntity>> = favoriteMovieDao.getAllFavoriteMovies()


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

    suspend fun getRecommendationsMoviesES(): List<HomeMoviesModel> {

        return getRecommendationsMoviesUseCase(originalLanguage = "es", lang = "es")

    }

    suspend fun getRecommendationsMoviesEN(): List<HomeMoviesModel> {

        return getRecommendationsMoviesUseCase(originalLanguage = "en", lang = "es")


    }


    init {

        viewModelScope.launch {
            _loading.value = true
            val result = withContext(Dispatchers.IO) { getUpcomingMoviesUseCase(lang = "es") }
            if (!result.isNullOrEmpty()) {
                _upcomingMovies.value = result
                _loading.value = false

            }
        }
        viewModelScope.launch {
            _loading.value = true
            val result = withContext(Dispatchers.IO) { getPopularMoviesUseCase(lang = "es") }
            if (!result.isNullOrEmpty()) {
                _popularMovies.value = result
                _loading.value = false

            }

            viewModelScope.launch {
                _loading.value = true
                val result = withContext(Dispatchers.IO) {
                    getRecommendationsMoviesUseCase(
                        originalLanguage = "es",
                        lang = "es"
                    )
                }
                if (!result.isNullOrEmpty()) {
                    _recommendationsMovies.value = result
                    _loading.value = false

                }
            }
        }
    }
}