package com.example.moviescourseapp.models

import com.example.moviescourseapp.data.remote.MoviesResultResponse
import com.example.moviescourseapp.data.remote.detail.MovieDetailsResponse
import com.example.moviescourseapp.models.details.MovieDetailsModel


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

fun MovieDetailsResponse.toMovieDetailsModel(): MovieDetailsModel {
    return MovieDetailsModel(
        id = this.id,
        title = this.title,
        posterPath = "$BASE_IMAGE_URL${this.posterPath}",
        overview = this.overview,
        releaseDate = this.releaseDate,
        backdropPath = this.backdropPath,
        genres = this.genres,
        popularity = this.popularity,
        tagline = this.tagline,
        voteAverage = this.voteAverage,
        video = this.video
    )
}