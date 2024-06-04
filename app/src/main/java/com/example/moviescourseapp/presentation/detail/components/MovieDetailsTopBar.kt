package com.example.moviescourseapp.presentation.detail.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.moviescourseapp.models.details.MovieDetailsModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsTopBar(
  movieDetailsModel: MovieDetailsModel
) {
  CenterAlignedTopAppBar(
    navigationIcon = {
      IconButton(onClick = { }) {
        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
      }
    },
    actions = {
      IconButton(onClick = { }) {
        Icon(imageVector = Icons.Filled.FavoriteBorder, contentDescription = null)
      }
    },
    title = {
      Text(text = movieDetailsModel.title, style = MaterialTheme.typography.titleMedium)
    }
  )
}