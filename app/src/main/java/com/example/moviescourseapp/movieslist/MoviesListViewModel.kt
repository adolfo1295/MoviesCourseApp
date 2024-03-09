package com.example.moviescourseapp.movieslist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.moviescourseapp.data.MoviesRepository
import com.example.moviescourseapp.data.remote.RetrofitClient
import com.example.moviescourseapp.models.MovieModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.ConnectException


class MoviesListViewModel(
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

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MoviesListViewModel(
                    MoviesRepository(
                        RetrofitClient.service
                    )
                )
            }
        }
    }
}

data class MoviesUiState(
    val moviesList: List<MovieModel> = emptyList(),
    val isLoading: Boolean = false,
    val errorEnum: ErrorMessage? = null
)