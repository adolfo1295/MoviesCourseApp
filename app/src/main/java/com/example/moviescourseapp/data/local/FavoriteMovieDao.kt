package com.example.moviescourseapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {

    @Insert
    suspend fun insertMovie(movieEntity: FavoriteMovieEntity)

    @Delete
    suspend fun deleteMovie(movieEntity: FavoriteMovieEntity)

    @Query("select * from favoritemovieentity")
    fun getFavoriteMovies(): Flow<List<FavoriteMovieEntity>>

}
