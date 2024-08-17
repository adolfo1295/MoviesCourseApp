package com.example.moviescourseapp.presentation.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescourseapp.data.MoviesRepository
import com.example.moviescourseapp.data.local.FavoriteMovieEntity
import com.example.moviescourseapp.models.details.MovieDetailsModel
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
        viewModelScope.launch {

            _movieDetailsUiState.update {
                it.copy(isLoading = true, error = null)
            }

            try {
                val movieDetail = movieRepository.getMovieDetails(movieId = movieId)
                _movieDetailsUiState.update {
                    it.copy(isLoading = false, movieDetailsModel = movieDetail)
                }
                getFavoriteMovies()
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

    fun updateFavorites(movieDetailsModel: MovieDetailsModel) {
      viewModelScope.launch {
          if (movieDetailsModel.isMovieInFavorites) {
              movieRepository.deleteMovie(
                  FavoriteMovieEntity(movieId = movieDetailsModel.id.toString(), posterPath = movieDetailsModel.posterPath)
              )
          } else {
              movieRepository.insertMovie(
                  FavoriteMovieEntity(movieId = movieDetailsModel.id.toString(), posterPath = movieDetailsModel.posterPath)
              )
          }
      }
    }

    private fun getFavoriteMovies() {
        viewModelScope.launch {
            movieRepository.getFavoriteMovies().collect { favoriteMovieList ->
                val currentMovieDetail = _movieDetailsUiState.value.movieDetailsModel
                currentMovieDetail?.let {
                    val isMovieInFavorites = favoriteMovieList.any { movieData ->
                        currentMovieDetail.id.toString() == movieData.movieId
                    }
                    _movieDetailsUiState.update {
                        it.copy(
                            movieDetailsModel = currentMovieDetail.copy(isMovieInFavorites = isMovieInFavorites)
                        )
                    }
                }
            }
        }
    }
}
