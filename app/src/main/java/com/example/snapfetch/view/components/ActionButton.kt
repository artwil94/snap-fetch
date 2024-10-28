package com.example.snapfetch.view.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.snapfetch.R
import com.example.snapfetch.ui.theme.SfTheme

@Composable
fun ActionButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        colors = ButtonColors(
            containerColor = SfTheme.colors.actionButton,
            contentColor = Color.Black,
            disabledContentColor = SfTheme.colors.actionButton,
            disabledContainerColor = SfTheme.colors.actionButton
        ),
    ) {
        Text(text = text, style = SfTheme.typography.actionButton)
        Spacer(modifier = Modifier.width(SfTheme.dimensions.paddingS))
        Icon(
            painter = painterResource(id = R.drawable.ic_load_more),
            contentDescription = null,
            tint = Color.Black
        )
    }
}

@Preview
@Composable
private fun ActionButtonPreview() {
    ActionButton(text = stringResource(id = R.string.load_more_photos), onClick = {})
}