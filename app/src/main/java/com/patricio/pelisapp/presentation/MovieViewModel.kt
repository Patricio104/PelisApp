package com.patricio.pelisapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.patricio.pelisapp.core.Resource
import com.patricio.pelisapp.repository.MovieRepository
import kotlinx.coroutines.Dispatchers

class MovieViewModel(private val repo:MovieRepository) : ViewModel() {
    fun fetchMainScreenMovies()=liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(Triple(repo.getUpcomingMovies(),repo.getTopRatedMovies(),repo.getPopularMovies())))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }
    /*fun fetchUpcomingMovies() = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getUpcomingMovies()))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

    fun fetchPopularMovies() = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getPopularMovies()))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

    fun fetchTopRatedMovies() = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getTopRatedMovies()))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }*/
}

class MovieViewModelFactory(private val repo: MovieRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }

}