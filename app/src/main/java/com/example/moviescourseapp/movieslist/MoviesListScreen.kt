package com.example.moviescourseapp.movieslist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviescourseapp.models.MovieModel


val mockMovieList = listOf<MovieModel>(
    MovieModel(0, "Buscando a Dory", "", false),
    MovieModel(1, "Buscando a Nemo", "", false),
    MovieModel(2, "Aquaman", "", false),
    MovieModel(3, "Tiburon 1", "", false),
)

@Composable
fun MoviesListScreen() {
    LazyColumn {
        items(mockMovieList) { movie ->
            Text(text = movie.title)
        }
    }
}

@Composable
@Preview
fun MoviesListScreenPreview() {
    MoviesListScreen()
}