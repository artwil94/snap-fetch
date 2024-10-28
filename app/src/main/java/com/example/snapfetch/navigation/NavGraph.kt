package com.example.snapfetch.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.snapfetch.view.screens.PhotoDetailsScreen
import com.example.snapfetch.view.screens.PhotosScreen
import com.example.snapfetch.view.screens.SplashScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = Screen.Splash
    ) {
        composable<Screen.Splash> {
            SplashScreen()
        }
        composable<Screen.Photos> {
            PhotosScreen()
        }

        composable<Screen.PhotoDetails> {
            PhotoDetailsScreen()
        }
    }
}

