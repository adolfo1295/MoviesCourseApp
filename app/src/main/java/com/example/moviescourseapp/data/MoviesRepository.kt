package com.example.moviescourseapp.data

import com.example.moviescourseapp.models.MovieModel

interface MoviesRepository {
    suspend fun getMovies(): List<MovieModel>
}