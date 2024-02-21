package com.example.moviescourseapp.movieslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescourseapp.models.MovieModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class MoviesListViewModel : ViewModel() {

    private val _moviesListUiState = MutableStateFlow(MoviesUiState())
    val moviesListUiState: StateFlow<MoviesUiState> = _moviesListUiState.asStateFlow()

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {

            _moviesListUiState.update {
                it.copy(
                    isLoading = true
                )
            }

            delay(2000L)

            val movies: List<MovieModel> = mockMovieList
            _moviesListUiState.update { moviesUiState ->
                moviesUiState.copy(
                    moviesList = movies,
                    isLoading = false
                )
            }
        }
    }
}

data class MoviesUiState(
    val moviesList: List<MovieModel> = emptyList(),
    val isLoading: Boolean = false,
    val showErrorMessage: Boolean = false
)

val mockMovieList = listOf<MovieModel>(
    MovieModel(
        0,
        "Buscando a Dory",
        "https://play-lh.googleusercontent.com/eEL51k4AEWjVJ-0y-n6YmGvBj786KmGiRNraIhyUa7Zt8wAauACZ46uknVH6r_CpFJnd",
        false
    ),
    MovieModel(
        1,
        "Buscando a Nemo",
        "https://es.web.img3.acsta.net/pictures/14/02/13/11/08/054573.jpg",
        false
    ),
    MovieModel(
        2,
        "Aquaman",
        "https://static.cinepolis.com/resources/mx/movies/posters/414x603/44086-316627-20231219074437.jpg",
        false
    ),
    MovieModel(
        3,
        "Tiburon 1",
        "https://es.web.img3.acsta.net/pictures/14/03/17/10/10/562887.jpg",
        false
    ),
)