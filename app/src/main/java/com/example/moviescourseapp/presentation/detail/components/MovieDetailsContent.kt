package com.example.moviescourseapp.presentation.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviescourseapp.models.details.MovieDetailsModel

@Composable
fun MovieDetailsContent(
    movieDetailsModel: MovieDetailsModel
) {
    Scaffold(
        topBar = {
            MovieDetailsTopBar(movieDetailsModel = movieDetailsModel)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            MovieDetailsHeader(
                movieDetailsModel = movieDetailsModel,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            )
            MovieDetailsBody(movieDetailsModel = movieDetailsModel)
        }
    }
}