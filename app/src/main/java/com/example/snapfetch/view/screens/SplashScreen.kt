package com.example.snapfetch.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.snapfetch.R
import com.example.snapfetch.navigation.LocalNavController
import com.example.snapfetch.navigation.Screen
import com.example.snapfetch.ui.theme.SfTheme

const val ANIMATION_PROGRESS_FINISHED = 1.0f

@Composable
fun SplashScreen() {
    val navController = LocalNavController.current
    val splashAnimationComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.splash_animation
        )
    )
    val animationProgress by animateLottieCompositionAsState(splashAnimationComposition)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SfTheme.colors.primarySurface),
        contentAlignment = Alignment.Center,
    ) {
        LottieAnimation(
            modifier = Modifier.size(SfTheme.dimensions.splashAnimationSize),
            composition = splashAnimationComposition,
            contentScale = ContentScale.Fit,
        )
        if (animationProgress == ANIMATION_PROGRESS_FINISHED) {
            navController.navigate(Screen.Photos)
        }
    }
}