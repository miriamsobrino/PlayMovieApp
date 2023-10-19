package com.mirdev.playmovieapp.domain.usecase

import com.mirdev.playmovieapp.domain.model.movieSearchModel.MovieSearchModel
import com.mirdev.playmovieapp.domain.repository.MoviesRepository
import javax.inject.Inject

class GetSearchMovieUseCase @Inject constructor(private val repository: MoviesRepository) {

    suspend operator fun invoke(movie: String):List<MovieSearchModel> = repository.getSearchMovie(movie)
}