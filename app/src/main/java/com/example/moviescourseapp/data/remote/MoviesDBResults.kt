package com.example.moviescourseapp.data.remote

import com.squareup.moshi.Json

data class MoviesResultResponse(
    @Json(name = "page") val page : Int,
    @Json(name ="results") val result: List<MovieDetailResponse>,
)

data class MovieDetailResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "vote_average") val voteAverage: Float,
    @Json(name = "overview") val overview: String,
    @Json(name = "poster_path") val posterPath: String,
)