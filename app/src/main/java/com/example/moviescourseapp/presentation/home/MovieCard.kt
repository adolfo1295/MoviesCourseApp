@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.moviescourseapp.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviescourseapp.models.MovieModel

@Composable
fun MovieCard(
    movieModel: MovieModel,
    onMovieClick: (MovieModel) -> Unit
) {
  Card(
    onClick = {
      onMovieClick(movieModel)
    },
    elevation = CardDefaults.cardElevation(2.dp)
  ) {

    Box(modifier = Modifier.fillMaxSize()) {
      AsyncImage(
        model = movieModel.imageUrl, contentDescription = "Movie Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
          .fillMaxWidth()
          .wrapContentHeight()
      )
    }
  }
}

@Composable
@Preview(showBackground = true)
fun MovieCardPreview() {
  MovieCard(
      movieModel = MovieModel(
        0,
        "Buscando a Dory",
        "https://play-lh.googleusercontent.com/eEL51k4AEWjVJ-0y-n6YmGvBj786KmGiRNraIhyUa7Zt8wAauACZ46uknVH6r_CpFJnd",
        false
      ),
      onMovieClick = {}
  )
}