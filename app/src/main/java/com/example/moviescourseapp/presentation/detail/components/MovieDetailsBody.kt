package com.example.moviescourseapp.presentation.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.moviescourseapp.models.details.MovieDetailsModel

@Composable
fun MovieDetailsBody(
    movieDetailsModel: MovieDetailsModel
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        MovieInfoContainer(movieDetailsModel = movieDetailsModel)
        MovieDescriptionContainer(description = movieDetailsModel.overview)
    }
    
}

