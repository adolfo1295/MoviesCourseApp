package com.example.moviescourseapp.data

import com.example.moviescourseapp.data.remote.MovieDbApi
import com.example.moviescourseapp.models.MovieModel
import com.example.moviescourseapp.models.toMoviesModelList

class MoviesRepositoryImpl(
    private val movieDbApi: MovieDbApi
) : MoviesRepository {

    override suspend fun getMovies(): List<MovieModel> {
        return movieDbApi.getMovies().toMoviesModelList()
    }
}

