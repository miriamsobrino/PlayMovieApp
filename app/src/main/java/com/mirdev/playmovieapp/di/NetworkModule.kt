package com.mirdev.playmovieapp.di

import com.mirdev.playmovieapp.data.network.MoviesApiService
import com.mirdev.playmovieapp.data.repository.MoviesRepositoryImpl
import com.mirdev.playmovieapp.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideQuoteApiClient(retrofit: Retrofit): MoviesApiService {

        return retrofit.create(MoviesApiService::class.java)
    }

    @Provides
    fun provideRepository(apiService: MoviesApiService): MoviesRepository {
        return MoviesRepositoryImpl(apiService)
    }
}