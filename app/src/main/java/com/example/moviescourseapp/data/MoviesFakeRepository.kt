package com.example.moviescourseapp.data

import com.example.moviescourseapp.models.MovieModel

class MoviesFakeRepository : MoviesRepository {
    override suspend fun getMovies(): List<MovieModel> {
        return listOf(
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
    }
}