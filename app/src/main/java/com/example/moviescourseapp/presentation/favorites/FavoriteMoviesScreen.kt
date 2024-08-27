package com.example.moviescourseapp.presentation.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.moviescourseapp.models.MovieModel
import com.example.moviescourseapp.presentation.home.MovieCard

@Composable
fun FavoriteMoviesScreen(
    viewModel: FavoriteMoviesViewModel = hiltViewModel(),
    navigateToDetails: (MovieModel) -> Unit
) {

    val favoriteMoviesUiState by viewModel.favoriteMoviesUiState.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        if (favoriteMoviesUiState.isLoading) {
            //mostrar loading
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            val movieList = favoriteMoviesUiState.movieList
            if (movieList.isNotEmpty()) {
                //mostrar peliculas
                LazyVerticalStaggeredGrid(
                    verticalItemSpacing = 8.dp,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                    columns = StaggeredGridCells.Fixed(2),
                    content = {
                        items(movieList) { movieModel ->
                            MovieCard(
                                movieModel = movieModel,
                                onMovieClick = { movieClicked ->
                                    navigateToDetails(movieClicked)
                                }
                            )
                        }
                    }
                )
            } else {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    text = "No tienes ninguna pelicula, agrega una!"
                )
            }
        }
    }

}