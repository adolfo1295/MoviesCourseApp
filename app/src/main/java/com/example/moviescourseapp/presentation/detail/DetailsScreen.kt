@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.moviescourseapp.presentation.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage

val COLLAPSED_TOP_BAR_HEIGHT = 56.dp

@Composable
fun DetailsScreen(
  movieId: String?
) {

  val expandedTopBarHeight = (LocalConfiguration.current.screenHeightDp * 0.5).dp

  val listState = rememberLazyListState()

  val overlapHeightPx = with(LocalDensity.current) {
    expandedTopBarHeight.toPx() - COLLAPSED_TOP_BAR_HEIGHT.toPx()
  }
  val isCollapsed: Boolean by remember {
    derivedStateOf {
      val isFirstItemHidden =
        listState.firstVisibleItemScrollOffset > overlapHeightPx
      isFirstItemHidden || listState.firstVisibleItemIndex > 0
    }
  }

  Box {
    CollapsedTopBar(modifier = Modifier, isCollapsed = isCollapsed)
    LazyColumn(state = listState) {
      item {
        ExpandedHeaderContent(
          expandedTopBarHeight = expandedTopBarHeight,
          isCollapsed = isCollapsed
        )
      }
      item {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp)
        ) {
          FilterChip(selected = false, onClick = { /*TODO*/ }, label = {
            Text(text = "PG-18")
          })
        }
      }
      items(10) {
        Card(
          onClick = {

          },
          colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
          ),
          modifier = Modifier
            .fillMaxWidth()
            .size(100.dp)
            .padding(horizontal = 10.dp, vertical = 15.dp)
        ) {}
      }
    }
  }
}

@Composable
private fun ExpandedHeaderContent(expandedTopBarHeight: Dp, isCollapsed: Boolean) {

  val dummyImage =
    "https://static1.moviewebimages.com/wordpress/wp-content/uploads/2022/03/Spider-Man-No-Way-Home-Miles-Morales-(1).jpg"
  val gradientColor = MaterialTheme.colorScheme.surface

  Box(
    modifier = Modifier
      .background(MaterialTheme.colorScheme.primary)
      .fillMaxWidth()
      .height(expandedTopBarHeight),
    contentAlignment = Alignment.TopStart
  ) {
    TopAppBar(
      modifier = Modifier.zIndex(1f),
      colors = TopAppBarDefaults.topAppBarColors(
        containerColor = Color.Transparent,
        navigationIconContentColor = Color.White,
        actionIconContentColor = Color.White
      ),
      navigationIcon = {
        IconButton(
          colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.onSurface,
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
          ),
          onClick = { }) {
          Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
        }
      },
      actions = {
        IconButton(
          colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.onSurface,
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
          ),
          onClick = { }) {
          Icon(imageVector = Icons.Filled.FavoriteBorder, contentDescription = null)
        }
      },
      title = {})
    AsyncImage(
      model = dummyImage,
      contentDescription = null,
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .zIndex(0f)
        .fillMaxSize()
        .drawWithCache {
          onDrawWithContent {
            drawContent()
            val brush = Brush.verticalGradient(
              0.6f to gradientColor.copy(alpha = 0F),
              0.7f to gradientColor.copy(alpha = 0.6F),
              0.8f to gradientColor.copy(alpha = 0.7F),
              0.9f to gradientColor.copy(alpha = 0.8F),
              1f to gradientColor
            )
            drawRect(brush = brush)
          }
        }
    )
    AnimatedVisibility(
      modifier = Modifier
        .zIndex(1f)
        .align(Alignment.BottomCenter)
        .fillMaxWidth()
        .wrapContentHeight(),
      visible = !isCollapsed
    ) {
      Box {
        Row(
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
          StarWithRatingAndName(rating = 4.5, movieName = "Spider Man")
          IconButton(
            colors = IconButtonDefaults.iconButtonColors(
              containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
              contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = null)
          }
        }
      }
    }
  }
}

@Composable
fun StarWithRatingAndName(
  rating: Double,
  movieName: String
) {
  Column(
    modifier = Modifier.fillMaxWidth(0.7f)
  ) {
    Text(
      text = movieName,
      style = MaterialTheme.typography.bodyLarge,
      fontSize = 20.sp,
      fontWeight = FontWeight.Bold,
    )
    Row {
      Text(
        text = rating.toString(),
        modifier = Modifier.padding(8.dp)
      )
      Icon(
        imageVector = Icons.Filled.Star,
        tint = MaterialTheme.colorScheme.primary,
        contentDescription = null
      )
    }
  }
}

@Composable
private fun CollapsedTopBar(
  modifier: Modifier = Modifier,
  isCollapsed: Boolean
) {
  val color by animateColorAsState(
    targetValue = if (isCollapsed) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surface.copy(
      alpha = 0f
    )
  )
  Box(
    modifier = modifier
      .zIndex(1f)
      .background(color)
      .fillMaxWidth()
      .wrapContentHeight()
      .padding(16.dp),
  ) {
    AnimatedVisibility(visible = isCollapsed) {
      TopAppBar(
        navigationIcon = {
          IconButton(onClick = { }) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
          }
        },
        actions = {
          IconButton(
            colors = IconButtonDefaults.iconButtonColors(
              contentColor = MaterialTheme.colorScheme.onSurface,
              containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
            ),
            onClick = { }) {
            Icon(imageVector = Icons.Filled.FavoriteBorder, contentDescription = null)
          }
        },
        title = {
          Text(text = "Movie Detail")
        })
    }
  }
}

