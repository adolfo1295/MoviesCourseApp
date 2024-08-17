package com.example.moviescourseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviescourseapp.navigation.PrincipalScreenItem
import com.example.moviescourseapp.navigation.Routes
import com.example.moviescourseapp.presentation.detail.DetailsScreen
import com.example.moviescourseapp.presentation.home.MoviesListScreen
import com.example.moviescourseapp.ui.theme.MoviesCourseAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesCourseAppTheme {

                val navController = rememberNavController()
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = backStackEntry?.destination

                Scaffold(
                    bottomBar = {
                        AnimatedVisibility(
                            visible = currentDestination?.route
                                ?.contains(Routes.DetailsScreen) == false
                        ) {
                            PrincipalNavigationBar(
                                navController = navController,
                                currentDestination = currentDestination
                            )
                        }
                    }
                ) { paddingValues ->
                    NavHost(
                        modifier = Modifier.padding(paddingValues),
                        navController = navController,
                        startDestination = Routes.HomeScreen
                    ) {
                        composable(route = Routes.HomeScreen) {
                            MoviesListScreen(
                                onMovieClick = { movieModel ->
                                    navController.navigate(Routes.DetailsScreen + "/${movieModel.id}")
                                }
                            )
                        }
                        composable(route = Routes.FavoritesScreen) {

                        }
                        composable(route = Routes.NowPlayingScreen) {

                        }
                        composable(
                            route = Routes.DetailsScreen + "/{movieId}",
                            arguments = listOf(navArgument(name = "movieId") {
                                type = NavType.StringType
                            })
                        ) {
                            val movieId = it.arguments?.getString("movieId")
                            DetailsScreen(
                                movieId = movieId,
                                onBack = {
                                    navController.navigateUp()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PrincipalNavigationBar(
    navController: NavController,
    currentDestination: NavDestination?
) {

    val principalScreens = listOf(
        PrincipalScreenItem(
            title = "Home",
            route = Routes.HomeScreen,
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        PrincipalScreenItem(
            title = "Favorites",
            route = Routes.FavoritesScreen,
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.FavoriteBorder
        ),
        PrincipalScreenItem(
            title = "Now Playing",
            route = Routes.NowPlayingScreen,
            selectedIcon = Icons.Filled.PlayArrow,
            unselectedIcon = Icons.Outlined.PlayArrow
        ),
    )

    NavigationBar {
        principalScreens.forEach { screen ->
            NavigationBarItem(
                label = {
                    Text(text = screen.title)
                },
                selected = currentDestination?.route == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(Routes.HomeScreen)
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (currentDestination?.route == screen.route) screen.selectedIcon else screen.unselectedIcon,
                        contentDescription = screen.title
                    )
                })
        }
    }
}