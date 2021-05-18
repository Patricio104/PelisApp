package com.patricio.pelisapp.repository

import com.patricio.pelisapp.data.model.MovieList
import com.patricio.pelisapp.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(private val dataSource: RemoteMovieDataSource) : MovieRepository {
    override suspend fun getUpcomingMovies(): MovieList = dataSource.getUpcomingMovies()

    override suspend fun getTopRatedMovies(): MovieList =dataSource.getTopRatedMovies()

    override suspend fun getPopularMovies(): MovieList = dataSource.getPopularMovies()
}