package com.mirdev.playmovieapp.domain.usecase

import com.mirdev.playmovieapp.domain.model.homeMoviesModel.HomeMoviesModel
import com.mirdev.playmovieapp.domain.repository.MoviesRepository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val repository: MoviesRepository) {

    suspend operator fun invoke(lang: String):List<HomeMoviesModel> = repository.getPopularMovies(lang)
}