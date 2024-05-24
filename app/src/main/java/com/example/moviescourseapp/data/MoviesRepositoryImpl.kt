package com.example.moviescourseapp.data

import com.example.moviescourseapp.data.remote.MovieDbApi
import com.example.moviescourseapp.models.MovieModel
import com.example.moviescourseapp.models.details.MovieDetailsModel
import com.example.moviescourseapp.models.toMovieDetailsModel
import com.example.moviescourseapp.models.toMoviesModelList
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val movieDbApi: MovieDbApi
) : MoviesRepository {

    override suspend fun getMovies(): List<MovieModel> {
        return movieDbApi.getMovies().toMoviesModelList()
    }

    override suspend fun getMovieDetails(movieId: String): MovieDetailsModel {
        return movieDbApi.getMovieDetails(movieId = movieId).toMovieDetailsModel()
    }
}

