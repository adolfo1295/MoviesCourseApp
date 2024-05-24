package com.example.moviescourseapp.presentation.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescourseapp.data.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
  private val movieRepository: MoviesRepository
) : ViewModel() {

  fun getMovieDetails(movieId: String) {
    viewModelScope.launch {
      try {
        val movieDetail = movieRepository.getMovieDetails(movieId = movieId)
        Log.d("adolfo", "getMovieDetails: $movieDetail")
      } catch (e: Exception) {
        Log.e("adolfo", "error: $e")
      }
    }
  }
}
