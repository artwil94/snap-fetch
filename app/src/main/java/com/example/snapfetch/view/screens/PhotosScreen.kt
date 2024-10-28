package com.example.snapfetch.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.snapfetch.R
import com.example.snapfetch.navigation.LocalNavController
import com.example.snapfetch.ui.theme.SfTheme
import com.example.snapfetch.view.components.LoadingScreen
import com.example.snapfetch.view.components.PhotoCard
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
    if (uiState.isLoading) {
        LoadingScreen()
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(SfTheme.colors.primarySurface)
                .padding(top = SfTheme.dimensions.padding)
                .systemBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.lorem_picsum).uppercase(),
                style = SfTheme.typography.screenTitle
            )
            Spacer(modifier = Modifier.height(SfTheme.dimensions.paddingL))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = SfTheme.dimensions.padding)
            ) {
                items(uiState.photos) { photo ->
                    PhotoCard(photo = photo)
                    Spacer(modifier = Modifier.height(SfTheme.dimensions.paddingM))
                }
            }
        }
    }
}