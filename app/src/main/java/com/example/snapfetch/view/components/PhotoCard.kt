package com.example.snapfetch.view.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.snapfetch.R
import com.example.snapfetch.domain.model.Photo
import com.example.snapfetch.ui.theme.SfTheme

@Composable
fun PhotoCard(modifier: Modifier = Modifier, photo: Photo) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .clip(shape = SfTheme.shapes.photoCard)
                .aspectRatio(1.75f)
        ) {
            CoilImage(imageUrl = photo.url)
        }
        Spacer(modifier = Modifier.height(SfTheme.dimensions.padding))
        Text(
            text = stringResource(id = R.string.photo_id, photo.id ?: ""),
            style = SfTheme.typography.photoId,
            color = SfTheme.colors.primaryTextGray
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0x000000)
@Composable
private fun PhotoCardPreview() {
    PhotoCard(photo = Photo(id = "0", url = ""))
}
