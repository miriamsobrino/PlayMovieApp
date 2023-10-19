package com.mirdev.playmovieapp.data.repository

import android.util.Log
import com.mirdev.playmovieapp.data.network.MoviesApiService
import com.mirdev.playmovieapp.domain.model.detailMovieModel.DetailMovieModel
import com.mirdev.playmovieapp.domain.model.movieSearchModel.MovieSearchModel
import com.mirdev.playmovieapp.domain.model.homeMoviesModel.HomeMoviesModel
import com.mirdev.playmovieapp.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val apiService: MoviesApiService) :
    MoviesRepository {
    override suspend fun getUpcomingMovies(lang: String): List<HomeMoviesModel> {
        runCatching {
            val response = apiService.getUpcomingMovies(lang)
            val responseDomain = response.body()?.results?.map { it.toDomain() }
            return responseDomain!!
        }
            .onFailure { Log.i("error", "Ha ocurrido un error ${it.message}") }

        return emptyList()
    }

    override suspend fun getPopularMovies(lang: String): List<HomeMoviesModel> {
        runCatching {
            val response = apiService.getPopularMovies(lang)
            val responseDomain = response.body()?.results?.map { it.toDomain() }
            return responseDomain!!
        }
            .onFailure { Log.i("error", "Ha ocurrido un error ${it.message}") }

        return emptyList()
    }

    override suspend fun getRecommendationsMovies(
        originalLanguage: String,
        lang: String
    ): List<HomeMoviesModel> {
        runCatching {
            val response = apiService.getMovieRecommendation(originalLanguage, lang)
            val responseDomain = response.body()?.results?.map { it.toDomain() }
            return responseDomain!!
        }
            .onFailure { Log.i("error", "Ha ocurrido un error ${it.message}") }

        return emptyList()
    }


    override suspend fun getDetailMovie(movieId: Int, lang: String): DetailMovieModel {

        val response = apiService.getMovieDetail(movieId, lang)
        val responseDomain = response.body()?.toDomain()
        return responseDomain!!


    }

    override suspend fun getSearchMovie(movie: String): List<MovieSearchModel> {
        runCatching {
            val response = apiService.getSearchMovie(movie)
            val responseDomain = response.body()?.results?.map { it.toDomain() }
            return responseDomain!!
        }
        return emptyList()
    }
}