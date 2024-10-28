package com.example.snapfetch.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.snapfetch.navigation.LocalNavController
import com.example.snapfetch.viewmodel.PhotosUIState
import com.example.snapfetch.viewmodel.PhotosViewModel

@Composable
fun PhotosScreen(viewModel: PhotosViewModel = hiltViewModel()) {
    val navController = LocalNavController.current
    LaunchedEffect(key1 = Unit) {
        viewModel.getPhotos()
    }
    PhotosScreenContent(uiState = viewModel.uiState)
}

@Composable
private fun PhotosScreenContent(uiState: PhotosUIState) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(uiState.photos) { photo ->
                Text(text = "${photo.id}")
                Text(text = "${photo.url}")
                Text(text = "${photo.author}")
                Text(text = "${photo.width}")
                Text(text = "${photo.height}")
                Text(text = "${photo.downloadUrl}")
            }
        }
    }
}