package com.mirdev.playmovieapp.data.network.response.detailMovie

import com.mirdev.playmovieapp.domain.model.detailMovieModel.DetailMovieModel


data class MovieDetailResult(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: Any,
    val budget: Int,
    val homepage: String,
    val id: Int,
    val genres: List<Genre>,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    val isFavorite: Boolean
) {
    fun toDomain(): DetailMovieModel {
        return DetailMovieModel(
            id = id,
            poster = poster_path,
            title = title,
            language = original_language,
            releaseDate = release_date,
            rate = vote_average,
            genres = genres,
            overview = overview,
            isFavorite = isFavorite


        )
    }

}