@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.moviescourseapp.presentation.favorites

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
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
  val context = LocalContext.current


  LaunchedEffect(key1 = favoriteMoviesUiState.errorMessage) {
    favoriteMoviesUiState.errorMessage?.let { error ->
      Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
    }
  }

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
            items(movieList, key = { movieModel ->
              movieModel.id
            }) { movieModel ->

              val dismissState = rememberSwipeToDismissBoxState(
              confirmValueChange = { dismissValue ->
                if (dismissValue == SwipeToDismissBoxValue.EndToStart) {
                  viewModel.deleteMovieFromFavorites(movieModel)
                  Toast.makeText(context, "Pelicula eliminada correctamente", Toast.LENGTH_SHORT)
                    .show()
                  true
                } else {
                  false
                }
              }
            )

              SwipeToDismissBox(
                state = dismissState,
                backgroundContent = {},
                content = {
                  MovieCard(
                    movieModel = movieModel,
                    onMovieClick = { movieClicked ->
                      navigateToDetails(movieClicked)
                    }
                  )
                }
              )
            }
          }
        )
      } else {
        Text(
          modifier = Modifier.align(Alignment.Center),
          style = MaterialTheme.typography.titleLarge,
          textAlign = TextAlign.Center,
          color = MaterialTheme.colorScheme.onSurface,
          text = "No tienes ninguna pelicula en favoritos, agrega una!"
        )
      }
    }
  }

}