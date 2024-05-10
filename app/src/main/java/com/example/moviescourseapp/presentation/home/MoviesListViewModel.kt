package com.example.moviescourseapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescourseapp.data.MoviesRepository
import com.example.moviescourseapp.models.MovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject


@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    private val _moviesListUiState = MutableStateFlow(MoviesUiState())
    val moviesListUiState: StateFlow<MoviesUiState> = _moviesListUiState.asStateFlow()

    init {
        getMovies()
    }

    fun getMovies() {
        viewModelScope.launch {
            try {
                _moviesListUiState.update {
                    it.copy(
                        isLoading = true,
                        errorEnum = null
                    )
                }

                delay(2000L)

                val movies: List<MovieModel> = repository.getMovies()
                _moviesListUiState.update { moviesUiState ->
                    moviesUiState.copy(
                        moviesList = movies,
                        isLoading = false,
                        errorEnum = null
                    )
                }
            } catch (e: Exception) {
                val errorEnum = when {
                    e is ConnectException -> ErrorMessage.INTERNET_CONNECTION
                    else -> ErrorMessage.DEFAULT
                }

                _moviesListUiState.update {
                    it.copy(
                        isLoading = false,
                        errorEnum = errorEnum
                    )
                }
            }
        }
    }
}

data class MoviesUiState(
    val moviesList: List<MovieModel> = emptyList(),
    val isLoading: Boolean = false,
    val errorEnum: ErrorMessage? = null
)