package com.example.snapfetch.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.snapfetch.R
import com.example.snapfetch.navigation.LocalNavController
import com.example.snapfetch.navigation.Screen
import com.example.snapfetch.network.ConnectivityObserver
import com.example.snapfetch.network.NetworkConnectivityObserver
import com.example.snapfetch.ui.theme.SfTheme
import com.example.snapfetch.view.components.ActionButton
import com.example.snapfetch.view.components.AlertDialog
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
    val context = LocalContext.current
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var scrollAfterLoadMore by remember { mutableStateOf(false) }
    val connectivityObserver = remember { NetworkConnectivityObserver(context) }
    val networkStatus by connectivityObserver.observe().collectAsState(
        initial = connectivityObserver.getCurrentStatus()
    )
    val isNoNetwork = networkStatus == ConnectivityObserver.Status.Lost ||
            networkStatus == ConnectivityObserver.Status.Unavailable
    var showNoNetworkAlert by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        if (uiState.photos.isEmpty()) {
            actions.start()
        }
    }

    LaunchedEffect(key1 = isNoNetwork) {
        showNoNetworkAlert = isNoNetwork
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
    if (uiState.isLoading && uiState.photos.isEmpty()) {
        LoadingScreen()
    } else {
        val photoCount = uiState.photos.size
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
            Spacer(modifier = Modifier.height(SfTheme.dimensions.paddingS))
            PhotosCounter(photoCount = photoCount)
            Spacer(modifier = Modifier.height(SfTheme.dimensions.paddingL))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = SfTheme.dimensions.padding),
                state = listState,
                horizontalAlignment = Alignment.CenterHorizontally
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
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            color = Color.White
                        )
                    } else {
                        ActionButton(
                            modifier = Modifier.padding(bottom = SfTheme.dimensions.paddingL),
                            text = if (uiState.photos.isEmpty()) stringResource(id = R.string.load_photos) else
                                stringResource(id = R.string.load_more)
                        ) {
                            actions.loadMore()
                            scrollAfterLoadMore = true
                        }
                    }
                }
            }
        }
    }
    if (uiState.error) {
        AlertDialog(
            body = stringResource(id = R.string.alert_dialog_body),
            firstActionCTA = stringResource(id = R.string.try_again),
            onFirstActionClick = actions.onTryAgain,
            onSecondActionClick = actions.onClose,
            secondActionCTA = stringResource(
                id = R.string.close
            )
        )
    }
    if (showNoNetworkAlert) {
        AlertDialog(
            title = stringResource(id = R.string.no_network),
            body = stringResource(id = R.string.no_network_message),
            firstActionCTA = stringResource(id = R.string.close),
            onFirstActionClick = { showNoNetworkAlert = false },
            titleIcon = R.drawable.ic_no_network
        )
    }
}

@Composable
private fun ColumnScope.PhotosCounter(photoCount: Int) {
    Text(
        modifier = Modifier
            .align(Alignment.End)
            .padding(end = SfTheme.dimensions.padding),
        text = buildAnnotatedString {
            append(stringResource(id = R.string.loaded_photos))
            append("  ")
            withStyle(
                style = SpanStyle(
                    color = SfTheme.colors.highlightedColor,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append(photoCount.toString())
            }
        },
        style = SfTheme.typography.photoDetailSubtitle,
        color = SfTheme.colors.primaryTextGray
    )
}