package com.mirdev.playmovieapp.domain.usecase

import com.mirdev.playmovieapp.domain.model.homeMoviesModel.HomeMoviesModel
import com.mirdev.playmovieapp.domain.repository.MoviesRepository
import javax.inject.Inject

class GetRecommendationsMoviesUseCase @Inject constructor(val repository: MoviesRepository) {

    suspend operator fun invoke(originalLanguage: String, lang: String):List<HomeMoviesModel> = repository.getRecommendationsMovies(originalLanguage, lang)
}