package com.example.moviescourseapp.presentation.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocalMovies
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviescourseapp.models.details.MovieDetailsModel
import com.example.moviescourseapp.presentation.components_general.IconWithText

@Composable
fun MovieInfoContainer(
    movieDetailsModel: MovieDetailsModel
) {

    OutlinedCard(
        shape = CircleShape,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(8.dp)
        ) {
            IconWithText(
                text = movieDetailsModel.releaseDate,
                icon = Icons.Filled.DateRange
            )
            IconWithText(
                text = "${movieDetailsModel.runtime} mins",
                icon = Icons.Filled.Timer
            )
            IconWithText(
                text = movieDetailsModel.genres.first().name,
                icon = Icons.Filled.LocalMovies
            )
        }
    }
}