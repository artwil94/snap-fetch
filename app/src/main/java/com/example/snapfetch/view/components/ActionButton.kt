package com.example.snapfetch.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.snapfetch.R
import com.example.snapfetch.ui.theme.SfTheme

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = SfTheme.colors.actionButton,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        colors = ButtonColors(
            containerColor = color,
            contentColor = Color.Black,
            disabledContentColor = SfTheme.colors.actionButton,
            disabledContainerColor = SfTheme.colors.actionButton
        ),
    ) {
        Text(text = text, style = SfTheme.typography.actionButton)
    }
}

@Preview
@Composable
private fun ActionButtonPreview() {
    ActionButton(text = stringResource(id = R.string.load_more), onClick = {})
}