package com.example.moviescourseapp.movieslist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun MovieCard(){
    Card(
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()){
            AsyncImage(model = ""
                , contentDescription = "Movie Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )

            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Favorite icon",
                modifier = Modifier
                    .size(50.dp)
                    .padding(8.dp)
                    .align(Alignment.TopEnd)
                    .clickable {

                    }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MovieCardPreview(){
    MovieCard()
}