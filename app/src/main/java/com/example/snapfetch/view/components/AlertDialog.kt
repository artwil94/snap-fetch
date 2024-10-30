package com.example.snapfetch.view.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.snapfetch.R
import com.example.snapfetch.ui.theme.SfTheme

@Composable
fun AlertDialog(
    title: String? = null,
    body: String? = null,
    firstActionCTA: String,
    secondActionCTA: String? = null,
    onFirstActionClick: () -> Unit = {},
    onSecondActionClick: () -> Unit = {},
    @DrawableRes titleIcon: Int? = null,
    onDismiss: () -> Unit = { if (secondActionCTA == null) onFirstActionClick.invoke() else onSecondActionClick.invoke() }
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = false
        )
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = SfTheme.shapes.photoCard
                )
        ) {
            Column(
                modifier = Modifier.padding(
                    SfTheme.dimensions.alertDialogPadding,
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                title?.let { title ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (titleIcon != null) {
                            Icon(
                                modifier = Modifier.padding(end = SfTheme.dimensions.paddingS),
                                painter = painterResource(id = titleIcon),
                                contentDescription = null,
                                tint = SfTheme.colors.error
                            )
                        }
                        Text(
                            text = title,
                            style = SfTheme.typography.alertDialogTitle,
                            color = Color.Black
                        )
                    }

                }
                body?.let { body ->
                    Spacer(modifier = Modifier.height(SfTheme.dimensions.padding))
                    Text(
                        text = body,
                        style = SfTheme.typography.alertDialogBody,
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(SfTheme.dimensions.paddingL))
                ActionButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = firstActionCTA.uppercase(),
                    onClick = { onFirstActionClick.invoke() },
                )
                Spacer(modifier = Modifier.height(SfTheme.dimensions.paddingS))
                secondActionCTA?.let {
                    ActionButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = secondActionCTA.uppercase(),
                        onClick = { onSecondActionClick.invoke() },
                        color = SfTheme.colors.primaryTextGray,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun AlertDialogPreview() {
    AlertDialog(
        body = stringResource(id = R.string.alert_dialog_body),
        firstActionCTA = stringResource(id = R.string.try_again),
        secondActionCTA = stringResource(id = R.string.close)
    )
}