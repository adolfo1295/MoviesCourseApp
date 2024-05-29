package com.example.moviescourseapp.presentation.detail

import com.example.moviescourseapp.models.MovieModel
import com.example.moviescourseapp.models.details.MovieDetailsModel
import com.example.moviescourseapp.presentation.home.ErrorMessage

data class MovieDetailsUiState(
  val movieDetailsModel: MovieDetailsModel? = null,
  val isLoading: Boolean = false,
  val error: ErrorMessage? = null,
)
