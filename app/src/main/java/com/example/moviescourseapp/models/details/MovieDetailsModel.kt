package com.example.moviescourseapp.models.details

import com.example.moviescourseapp.data.remote.detail.Genre

data class MovieDetailsModel(
    var backdropPath: String,
    var genres: List<Genre>,
    var id: Int,
    var overview: String,
    var popularity: Double,
    var posterPath: String,
    var releaseDate: String,
    var tagline: String,
    var title: String,
    var video: Boolean,
    var voteAverage: Double,
    var runtime: Int
)
