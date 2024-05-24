package com.example.moviescourseapp.data

import com.example.moviescourseapp.models.MovieModel
import com.example.moviescourseapp.models.details.MovieDetailsModel

interface MoviesRepository {
    suspend fun getMovies(): List<MovieModel>
    suspend fun getMovieDetails(movieId: String): MovieDetailsModel
}