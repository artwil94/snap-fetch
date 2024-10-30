package com.example.snapfetch.view.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.snapfetch.R
import com.example.snapfetch.ui.theme.SfTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    @DrawableRes navigationIcon: Int? = R.drawable.ic_chevron_left,
    onNavigationIconClick: () -> Unit = {},
    backgroundColor: Color = Color.Transparent,
    showBackIcon: Boolean = true
) {
    CenterAlignedTopAppBar(
        modifier = Modifier.then(modifier),
        title = {
            title?.let {
                Text(
                    text = title,
                    style = SfTheme.typography.photoDetailSubtitle,
                    color = Color.White
                )
            }
        },
        navigationIcon = {
            if (showBackIcon) {
                if (navigationIcon != null) {
                    Icon(
                        modifier = Modifier.clickable { onNavigationIconClick.invoke() },
                        painter = painterResource(id = navigationIcon),
                        contentDescription = stringResource(id = R.string.content_description_back),
                        tint = Color.White
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = backgroundColor,
            scrolledContainerColor = backgroundColor,
            navigationIconContentColor = backgroundColor,
            titleContentColor = backgroundColor,
            actionIconContentColor = backgroundColor
        ),
    )
}