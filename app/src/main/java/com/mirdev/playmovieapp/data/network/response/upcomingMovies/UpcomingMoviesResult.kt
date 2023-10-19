package com.mirdev.playmovieapp.data.network.response.upcomingMovies

import com.mirdev.playmovieapp.domain.model.homeMoviesModel.HomeMoviesModel

data class UpcomingMoviesResult(
    val id: Int,
    val poster_path: String,
    val title: String,
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