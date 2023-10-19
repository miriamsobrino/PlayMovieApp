package com.mirdev.playmovieapp.data.network.response.popularMovies

import com.mirdev.playmovieapp.domain.model.homeMoviesModel.HomeMoviesModel

data class MoviePopularResult(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    val isFavorite: Boolean
) {
    fun toDomain(): HomeMoviesModel {
        return HomeMoviesModel(
            id = id,
            poster = poster_path,
            title = title,
            isFavorite = isFavorite
        )
    }

}