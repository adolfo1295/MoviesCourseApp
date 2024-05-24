package com.example.moviescourseapp.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun DetailsScreen(
  movieId: String?,
  viewModel: MovieDetailsViewModel = hiltViewModel()
) {
  Box(modifier = Modifier.fillMaxSize()) {
    Text(text = "Details $movieId")
    viewModel.getMovieDetails(movieId.orEmpty())
  }
}