package com.example.moviescourseapp.data.remote.detail


import com.squareup.moshi.Json

data class MovieDetailsResponse(
    @Json(name = "backdrop_path")
    var backdropPath: String,
    @Json(name = "genres")
    var genres: List<Genre>,
    @Json(name = "id")
    var id: Int,
    @Json(name = "overview")
    var overview: String,
    @Json(name = "popularity")
    var popularity: Double,
    @Json(name = "poster_path")
    var posterPath: String,
    @Json(name = "release_date")
    var releaseDate: String,
    @Json(name = "tagline")
    var tagline: String,
    @Json(name = "title")
    var title: String,
    @Json(name = "video")
    var video: Boolean,
    @Json(name = "vote_average")
    var voteAverage: Double,
    @Json(name = "runtime")
    var runtime: Int
)