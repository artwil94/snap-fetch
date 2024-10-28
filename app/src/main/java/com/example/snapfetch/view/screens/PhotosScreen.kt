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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.snapfetch.R
import com.example.snapfetch.navigation.LocalNavController
import com.example.snapfetch.navigation.Screen
import com.example.snapfetch.ui.theme.SfTheme
import com.example.snapfetch.view.components.ActionButton
import com.example.snapfetch.view.components.LoadingScreen
import com.example.snapfetch.view.components.PhotoCard
import com.example.snapfetch.viewmodel.PhotosActions
import com.example.snapfetch.viewmodel.PhotosUIState
import com.example.snapfetch.viewmodel.PhotosViewModel
import kotlinx.coroutines.launch

@Composable
fun PhotosScreen(viewModel: PhotosViewModel = hiltViewModel()) {
    PhotosScreenContent(uiState = viewModel.uiState, actions = viewModel.actions)
}

@Composable
private fun PhotosScreenContent(uiState: PhotosUIState, actions: PhotosActions) {
    val navController = LocalNavController.current
    if (uiState.isLoading && uiState.photos.isEmpty()) {
        LoadingScreen()
    } else {
        val listState = rememberLazyListState()
        val coroutineScope = rememberCoroutineScope()
        var scrollAfterLoadMore by remember { mutableStateOf(false) }
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
                    .padding(horizontal = SfTheme.dimensions.padding),
                state = listState
            ) {
                items(uiState.photos) { photo ->
                    PhotoCard(
                        photo = photo,
                        onClick = { navController.navigate(Screen.PhotoDetails(photo.id ?: "")) })
                    Spacer(modifier = Modifier.height(SfTheme.dimensions.paddingM))
                }
                item {
                    if (uiState.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.White
                        )
                    } else {
                        ActionButton(
                            modifier = Modifier.padding(bottom = SfTheme.dimensions.paddingL),
                            text = stringResource(id = R.string.load_more)
                        ) {
                            actions.loadMore()
                            scrollAfterLoadMore = true
                        }
                    }
                }
            }
        }
        LaunchedEffect(uiState.photos.size) {
            if (uiState.photos.isNotEmpty() && scrollAfterLoadMore) {
                coroutineScope.launch {
                    val targetIndex =
                        (listState.firstVisibleItemIndex + 2).coerceAtMost(uiState.photos.size - 1)
                    listState.animateScrollToItem(targetIndex)
                }
            }
        }
    }
}