package com.example.moviescourseapp.presentation.favorites

import com.example.moviescourseapp.models.MovieModel
import com.example.moviescourseapp.presentation.home.ErrorMessage

data class FavoriteMoviesUiState(
    val isLoading: Boolean = false,
    val movieList : List<MovieModel> = emptyList(),
    val errorMessage: ErrorMessage? = null
)
