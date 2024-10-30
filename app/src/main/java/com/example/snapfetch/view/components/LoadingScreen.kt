package com.example.snapfetch.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.snapfetch.ui.theme.SfTheme

@Composable
fun LoadingScreen(modifier: Modifier = Modifier, loaderColor: Color = Color.White) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SfTheme.colors.primarySurface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(color = loaderColor)
    }
}