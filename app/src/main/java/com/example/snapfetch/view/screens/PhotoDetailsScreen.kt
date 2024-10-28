package com.example.snapfetch.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.snapfetch.navigation.LocalNavController
import com.example.snapfetch.viewmodel.PhotoDetailsActions
import com.example.snapfetch.viewmodel.PhotoDetailsUIState
import com.example.snapfetch.viewmodel.PhotoDetailsViewModel

@Composable
fun PhotoDetailsScreen(photoId: String, viewModel: PhotoDetailsViewModel = hiltViewModel()) {
    PhotoDetailsContent(uiState = viewModel.uiState, actions = viewModel.actions, photoId = photoId)
}

@Composable
private fun PhotoDetailsContent(
    uiState: PhotoDetailsUIState,
    actions: PhotoDetailsActions,
    photoId: String
) {
    LaunchedEffect(key1 = Unit) {
        actions.start(photoId)
    }
    val navController = LocalNavController.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Photo details"
            )
            Text(
                text = "${uiState.photo?.id}"
            )
            Text(
                text = "${uiState.photo?.author}"
            )
            Text(
                text = "${uiState.photo?.width}"
            )
            Text(
                text = "${uiState.photo?.height}"
            )
            Text(
                text = "${uiState.photo?.downloadUrl}"
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { navController.popBackStack() }) {
                Text(text = "Navigate back")
            }
        }

    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PhotoDetailsScreenPreview() {
    PhotoDetailsScreen(photoId = "")
}