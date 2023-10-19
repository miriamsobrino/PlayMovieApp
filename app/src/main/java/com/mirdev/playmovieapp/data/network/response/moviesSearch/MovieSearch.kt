package com.mirdev.playmovieapp.data.network.response.moviesSearch

data class MovieSearch(
    val page: Int,
    val results: List<MovieSearchResult>,
    val total_pages: Int,
    val total_results: Int
)