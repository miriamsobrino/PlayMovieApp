package com.mirdev.playmovieapp.data.network.response.popularMovies

data class MoviePopular (
    val page: Int,
    val results: List<MoviePopularResult>,
    val total_pages: Int,
    val total_results: Int
)