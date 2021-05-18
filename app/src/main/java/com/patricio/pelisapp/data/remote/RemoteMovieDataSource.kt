package com.patricio.pelisapp.data.remote

import com.patricio.pelisapp.aplication.AppConstants
import com.patricio.pelisapp.data.model.MovieList
import com.patricio.pelisapp.repository.WebService

class RemoteMovieDataSource(private val webService: WebService) {
    suspend fun getUpcomingMovies(): MovieList = webService.getUpcomingMovies(AppConstants.API_KEY)

    suspend fun getTopRatedMovies(): MovieList =webService.getTopRatedMovies(AppConstants.API_KEY)

    suspend fun getPopularMovies(): MovieList = webService.getPopularMovies(AppConstants.API_KEY)
}