package com.mirdev.playmovieapp.domain.usecase

import com.mirdev.playmovieapp.domain.model.detailMovieModel.DetailMovieModel
import com.mirdev.playmovieapp.domain.repository.MoviesRepository
import javax.inject.Inject

class GetDetailMovieUseCase @Inject constructor(private val repository: MoviesRepository) {

    suspend operator fun invoke(movieId: Int, lang: String): DetailMovieModel = repository.getDetailMovie(movieId, lang)
}