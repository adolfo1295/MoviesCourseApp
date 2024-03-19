package com.example.moviescourseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviescourseapp.presentation.detail.DetailsScreen
import com.example.moviescourseapp.presentation.home.MoviesListScreen
import com.example.moviescourseapp.ui.theme.MoviesCourseAppTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MoviesCourseAppTheme {

        val navController = rememberNavController()

        Scaffold { paddingValues ->
          NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = "movieList"
          ) {
            composable(route = "movieList") {
              MoviesListScreen(
                onMovieClick = {movieModel ->
                  navController.navigate("details")
                }
              )
            }
            composable(route = "details") {
              DetailsScreen()
            }
          }
        }
      }
    }
  }
}
