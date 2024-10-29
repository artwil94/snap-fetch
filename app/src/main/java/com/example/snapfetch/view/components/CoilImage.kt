package com.example.snapfetch.view.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.snapfetch.R

@Composable
fun CoilImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop
) {
    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(imageUrl)
        .crossfade(true)
        .build()

    AsyncImage(
        model = imageRequest,
        modifier = modifier.fillMaxSize(),
        placeholder = painterResource(id = R.drawable.placeholder_image),
        error = painterResource(id = R.drawable.placeholder_image),
        contentScale = contentScale,
        contentDescription = contentDescription
    )
}