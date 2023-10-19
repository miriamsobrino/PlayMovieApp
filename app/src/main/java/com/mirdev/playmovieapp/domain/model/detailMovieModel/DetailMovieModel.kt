package com.mirdev.playmovieapp.domain.model.detailMovieModel

import com.mirdev.playmovieapp.data.network.response.detailMovie.Genre


data class DetailMovieModel(
    val id: Int,
    val title: String,
    val poster: String,
    val language: String,
    val rate: Double,
    val releaseDate: String,
    val genres: List<Genre>,
    val overview: String,
    val isFavorite: Boolean

)
