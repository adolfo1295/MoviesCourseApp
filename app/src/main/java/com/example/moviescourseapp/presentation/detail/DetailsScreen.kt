package com.example.moviescourseapp.presentation.detail

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DetailsScreen(
  movieId: String?,
  viewModel: MovieDetailsViewModel = hiltViewModel()
) {

  LaunchedEffect(movieId) {
    viewModel.getMovieDetails(movieId.orEmpty())
  }

  val movieDetailsUiState by viewModel.movieDetailsUiState.collectAsState()

  Box(modifier = Modifier.fillMaxSize()) {

    LaunchedEffect(movieDetailsUiState.error) {
      if (movieDetailsUiState.error != null) {
        Log.d("adolfo", "DetailsScreen: ${movieDetailsUiState.error}")
      }
    }

    if (movieDetailsUiState.isLoading) {
      CircularProgressIndicator(
        modifier = Modifier.align(Alignment.Center)
      )
    } else {
      Text(text = "Movie Id: $movieId")
    }
  }
}
