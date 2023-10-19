package com.mirdev.playmovieapp.ui.search

import androidx.lifecycle.ViewModel
import com.mirdev.playmovieapp.domain.model.movieSearchModel.MovieSearchModel
import com.mirdev.playmovieapp.domain.usecase.GetSearchMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SearchFragmentViewModel @Inject constructor(private val getSearchMovieUseCase: GetSearchMovieUseCase) : ViewModel(){

    suspend fun fetchListMovies(movie: String): List<MovieSearchModel> {
        return getSearchMovieUseCase(movie)
    }
}