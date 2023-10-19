package com.mirdev.playmovieapp.data.network

import com.mirdev.playmovieapp.BuildConfig
import com.mirdev.playmovieapp.data.network.response.moviesSearch.MovieSearch
import com.mirdev.playmovieapp.data.network.response.detailMovie.MovieDetailResult
import com.mirdev.playmovieapp.data.network.response.popularMovies.MoviePopular
import com.mirdev.playmovieapp.data.network.response.recommendationsMovies.MovieRecommendations
import com.mirdev.playmovieapp.data.network.response.upcomingMovies.UpcomingMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {

    companion object{
        const val API_KEY = BuildConfig.API_KEY
    }

    @GET("movie/upcoming?api_key=$API_KEY")
    suspend fun getUpcomingMovies(@Query("language") lang: String) : Response<UpcomingMovies>

    @GET("movie/popular?api_key=$API_KEY")
    suspend fun getPopularMovies(@Query("language") lang: String) : Response<MoviePopular>

    @GET("movie/{movie_id}?api_key=$API_KEY")
    suspend fun getMovieDetail(@Path("movie_id") movieId : Int, @Query("language") lang: String) : Response<MovieDetailResult>

    @GET("discover/movie?api_key=$API_KEY")
    suspend fun getMovieRecommendation(@Query("with_original_language") originalLanguage: String, @Query("language") lang: String) : Response<MovieRecommendations>

    @GET("search/movie?api_key=$API_KEY")
    suspend fun getSearchMovie(@Query("query") movie : String) : Response<MovieSearch>
}