package com.example.moviescourseapp.movieslist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviescourseapp.models.MovieModel


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
        "https://sm.ign.com/t/ign_latam/feature/a/aquaman-an/aquaman-and-the-lost-kingdom-ending-explained_8b5y.1200.jpg",
        false
    ),
    MovieModel(
        3,
        "Tiburon 1",
        "https://es.web.img3.acsta.net/pictures/14/03/17/10/10/562887.jpg",
        false
    ),
)

@Composable
fun MoviesListScreen() {
    LazyColumn {
        items(mockMovieList) { movie ->
            var isFavorite by rememberSaveable {
                mutableStateOf(false)
            }
            MovieCard(
                movieModel = movie,
                isFavorite = isFavorite,
                onFavoriteClick = {
                    isFavorite = !isFavorite
                }
            )
        }
    }
}

@Composable
@Preview
fun MoviesListScreenPreview() {
    MoviesListScreen()
}