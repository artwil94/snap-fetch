@file:OptIn(ExperimentalLayoutApi::class)

package com.example.snapfetch.view.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.snapfetch.R
import com.example.snapfetch.ui.theme.SfTheme

@Composable
fun PhotoDetailItem(
    modifier: Modifier = Modifier,
    value: String,
    subtitle: String,
    @DrawableRes icon: Int,
    textStyle: TextStyle = SfTheme.typography.photoDetailSubtitle,
    subtitleTextStyle: TextStyle = SfTheme.typography.photoDetailSubtitle,
    clickableValue: Boolean = false,
    onValueClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = modifier.size(SfTheme.dimensions.photoDetailIconSize),
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = SfTheme.colors.actionButton
        )
        Box(modifier = Modifier.weight(1f)) {
            Text(
                modifier = Modifier
//                    .weight(1f)
                    .padding(start = SfTheme.dimensions.paddingS),
                text = subtitle,
                style = subtitleTextStyle,
                textAlign = TextAlign.Center,
                color = SfTheme.colors.primaryTextGray,
                maxLines = 2,
            )
        }
        Text(
            modifier = Modifier
                .clickable(onClick = { onValueClick.invoke() }, enabled = clickableValue)
                .padding(start = SfTheme.dimensions.paddingS),
            text = value,
            style = textStyle,
            color = Color.White,
            minLines = 1
        )
    }
}

@Preview
@Composable
private fun PhotoDetailItemPreview() {
    PhotoDetailItem(
        value = "Artur Wilczek",
        icon = R.drawable.ic_author,
        textStyle = SfTheme.typography.author,
        subtitle = stringResource(id = R.string.author)
    )
}