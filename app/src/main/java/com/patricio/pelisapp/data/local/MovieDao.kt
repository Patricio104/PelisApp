package com.patricio.pelisapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.patricio.pelisapp.data.model.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movieentity")
    suspend fun getAllMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: MovieEntity)

}