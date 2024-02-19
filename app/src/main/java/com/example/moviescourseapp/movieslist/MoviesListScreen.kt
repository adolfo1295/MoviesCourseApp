package com.example.moviescourseapp.movieslist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

@Composable
fun MoviesListScreen() {

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        content = {
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
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 2.dp,
                vertical = 4.dp
            )
    )

}

@Composable
@Preview
fun MoviesListScreenPreview() {
    MoviesListScreen()
}