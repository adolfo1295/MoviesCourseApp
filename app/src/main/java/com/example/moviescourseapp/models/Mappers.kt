package com.example.moviescourseapp.models

import com.example.moviescourseapp.data.remote.MoviesResultResponse


const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
fun MoviesResultResponse.toMoviesModelList() : List<MovieModel> {
    return this.result.map { movieDetailResponse ->
        MovieModel(
            id = movieDetailResponse.id,
            title = movieDetailResponse.title,
            imageUrl = "$BASE_IMAGE_URL${movieDetailResponse.posterPath}",
            isFavorite = false
        )
    }
}