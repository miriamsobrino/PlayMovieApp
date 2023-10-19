package com.mirdev.playmovieapp.data.network.response.moviesSearch

import com.mirdev.playmovieapp.domain.model.movieSearchModel.MovieSearchModel

data class MovieSearchResult(
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
    val vote_count: Int
) {
    fun toDomain(): MovieSearchModel {
        return MovieSearchModel(
            id = id,
            poster = poster_path,
            title = title,
            releaseDate = release_date,


            )
    }
}