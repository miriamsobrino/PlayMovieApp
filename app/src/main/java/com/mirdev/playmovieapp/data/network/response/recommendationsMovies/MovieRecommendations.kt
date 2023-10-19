package com.mirdev.playmovieapp.data.network.response.recommendationsMovies

data class MovieRecommendations (
    val page: Int,
    val results: List<MovieRecommendationsResult>,
    val total_pages: Int,
    val total_results: Int
    )