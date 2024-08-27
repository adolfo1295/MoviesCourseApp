package com.example.moviescourseapp.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescourseapp.data.MoviesRepository
import com.example.moviescourseapp.models.transformToMovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteMoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _favoriteUiState = MutableStateFlow(FavoriteMoviesUiState())
    val favoriteMoviesUiState = _favoriteUiState.asStateFlow()

    init {
        getFavoriteMovies()
    }

    private fun getFavoriteMovies() {
        viewModelScope.launch {
            _favoriteUiState.update {
                it.copy(isLoading = true)
            }
            moviesRepository.getFavoriteMovies().collect { favoriteMovieList ->
                _favoriteUiState.update {
                    it.copy(
                        isLoading = false,
                        movieList = favoriteMovieList.map { movieEntity ->
                            movieEntity.transformToMovieModel()
                        }
                    )
                }
            }

        }
    }
}
