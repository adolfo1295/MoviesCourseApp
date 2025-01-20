package com.example.moviescourseapp.presentation.detail

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.moviescourseapp.R
import com.example.moviescourseapp.presentation.detail.components.MovieDetailsContent

@Composable
fun DetailsScreen(
  movieId: String?,
  viewModel: MovieDetailsViewModel = hiltViewModel(),
  onBack: () -> Unit
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
      val animationSpec by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.popcorn)
      )
      LottieAnimation(
        modifier = Modifier
          .fillMaxSize()
          .align(Alignment.Center),
        composition = animationSpec,
        iterations = LottieConstants.IterateForever
      )
    } else {
      movieDetailsUiState.movieDetailsModel?.let {
        MovieDetailsContent(
          movieDetailsModel = it,
          onUpdateFavorites = { movieDetailsModel ->
            viewModel.updateFavorites(movieDetailsModel = movieDetailsModel)
          },
          onBack = onBack
        )
      }
    }
  }
}
