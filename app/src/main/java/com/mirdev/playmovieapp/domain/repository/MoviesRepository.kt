package com.mirdev.playmovieapp.domain.repository

import com.mirdev.playmovieapp.domain.model.detailMovieModel.DetailMovieModel
import com.mirdev.playmovieapp.domain.model.movieSearchModel.MovieSearchModel
import com.mirdev.playmovieapp.domain.model.homeMoviesModel.HomeMoviesModel

interface MoviesRepository {

    suspend fun getUpcomingMovies(lang:String) : List<HomeMoviesModel>

    suspend fun getPopularMovies(lang:String) :List<HomeMoviesModel>

    suspend fun getRecommendationsMovies(originalLanguage: String, lang:String) : List<HomeMoviesModel>

    suspend fun getDetailMovie(movieId: Int, lang: String) : DetailMovieModel

    suspend fun getSearchMovie(movie: String) : List<MovieSearchModel>
}