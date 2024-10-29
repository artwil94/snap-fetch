package com.example.snapfetch.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.snapfetch.R
import com.example.snapfetch.composable.PreviewWrapper
import com.example.snapfetch.domain.model.Photo
import com.example.snapfetch.navigation.LocalNavController
import com.example.snapfetch.ui.theme.SfTheme
import com.example.snapfetch.util.openExternalBrowser
import com.example.snapfetch.view.components.LoadingScreen
import com.example.snapfetch.view.components.PhotoCard
import com.example.snapfetch.view.components.PhotoCardType
import com.example.snapfetch.view.components.PhotoDetailItem
import com.example.snapfetch.view.components.TopBar
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
    val context = LocalContext.current
    if (uiState.isLoading) {
        LoadingScreen()
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(SfTheme.colors.primarySurface)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val photo = uiState.photo
                TopBar(
                    modifier = Modifier.padding(start = SfTheme.dimensions.padding),
                    onNavigationIconClick = { navController.popBackStack() },
                    title = stringResource(id = R.string.photo_id, photo?.id ?: "")
                )
                photo?.let {
                    PhotoCard(
                        modifier = Modifier.padding(start = SfTheme.dimensions.padding),
                        photo = it,
                        photoCardType = PhotoCardType.DetailItem
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(SfTheme.dimensions.padding)
                            .clip(shape = SfTheme.shapes.photoCard)
                            .background(color = Color(0xFF2d2e32), shape = SfTheme.shapes.photoCard)

                    ) {
                        Column(
                            modifier = Modifier.padding(
                                horizontal = SfTheme.dimensions.padding,
                                vertical = SfTheme.dimensions.paddingM
                            )
                        ) {
                            Spacer(modifier = Modifier.height(SfTheme.dimensions.paddingS))
                            PhotoDetailItem(
                                value = photo.author,
                                subtitle = stringResource(id = R.string.author),
                                icon = R.drawable.ic_author,
                                textStyle = SfTheme.typography.author
                            )
                            Spacer(modifier = Modifier.height(SfTheme.dimensions.paddingM))
                            PhotoDetailItem(
                                value = "${photo.width} x ${photo.height}",
                                subtitle = stringResource(id = R.string.format),
                                icon = R.drawable.ic_dimensions
                            )
                            Spacer(modifier = Modifier.height(SfTheme.dimensions.padding))
                            PhotoDetailItem(
                                value = photo.downloadUrl,
                                subtitle = stringResource(id = R.string.download_url),
                                icon = R.drawable.ic_download,
                                textStyle = SfTheme.typography.downloadLink,
                                clickableValue = true,
                                onValueClick = {
                                    openExternalBrowser(
                                        context = context,
                                        url = photo.downloadUrl
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PhotoDetailsScreenPreview() {
    val uiState = PhotoDetailsUIState(
        photo = Photo(
            id = "0",
            author = "Artur Wilczek",
            width = 500,
            height = 1000,
            url = "https://www.miquido.com/",
            downloadUrl = "https://www.miquido.com/"
        )
    )
    PreviewWrapper {
        PhotoDetailsContent(uiState = uiState, actions = PhotoDetailsActions(), photoId = "")
    }
}