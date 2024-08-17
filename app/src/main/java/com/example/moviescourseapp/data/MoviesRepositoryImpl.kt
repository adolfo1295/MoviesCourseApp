package com.example.moviescourseapp.data

import com.example.moviescourseapp.data.local.FavoriteMovieDao
import com.example.moviescourseapp.data.local.FavoriteMovieEntity
import com.example.moviescourseapp.data.remote.MovieDbApi
import com.example.moviescourseapp.models.MovieModel
import com.example.moviescourseapp.models.details.MovieDetailsModel
import com.example.moviescourseapp.models.toMovieDetailsModel
import com.example.moviescourseapp.models.toMoviesModelList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val movieDbApi: MovieDbApi,
    private val movieDao: FavoriteMovieDao
) : MoviesRepository {

    override suspend fun getMovies(): List<MovieModel> {
        return movieDbApi.getMovies().toMoviesModelList()
    }

    override suspend fun getMovieDetails(movieId: String): MovieDetailsModel {
        return movieDbApi.getMovieDetails(movieId = movieId).toMovieDetailsModel()
    }

    override suspend fun insertMovie(movieEntity: FavoriteMovieEntity) {
        return movieDao.insertMovie(movieEntity = movieEntity)
    }

    override suspend fun deleteMovie(movieEntity: FavoriteMovieEntity) {
        return movieDao.deleteMovie(movieEntity = movieEntity)
    }

    override fun getFavoriteMovies(): Flow<List<FavoriteMovieEntity>> {
        return movieDao.getFavoriteMovies()
    }
}
