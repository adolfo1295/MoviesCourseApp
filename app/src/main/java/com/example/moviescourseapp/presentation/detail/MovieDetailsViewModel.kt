package com.example.moviescourseapp.presentation.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescourseapp.data.MoviesRepository
import com.example.moviescourseapp.presentation.home.ErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
  private val movieRepository: MoviesRepository
) : ViewModel() {

  private val _movieDetailsUiState = MutableStateFlow(MovieDetailsUiState())
  val movieDetailsUiState = _movieDetailsUiState.asStateFlow()

  fun getMovieDetails(movieId: String) {

    Log.d("adolfo", "getMovieDetails: paso por aqui")

    viewModelScope.launch {

      _movieDetailsUiState.update {
        it.copy(isLoading = true, error = null)
      }

      delay(1.seconds)

      try {
        val movieDetail = movieRepository.getMovieDetails(movieId = movieId)
        _movieDetailsUiState.update {
          it.copy(isLoading = false, movieDetailsModel = movieDetail)
        }
      } catch (e: Exception) {
        val error = when (e) {
          is ConnectException -> ErrorMessage.INTERNET_CONNECTION
          else -> ErrorMessage.DEFAULT
        }

        _movieDetailsUiState.update {
          it.copy(isLoading = false, error = error)
        }

      }
    }
  }
}
