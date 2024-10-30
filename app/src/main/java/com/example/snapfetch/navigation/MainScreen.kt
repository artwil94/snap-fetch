package com.example.snapfetch.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

val LocalNavController = compositionLocalOf<NavHostController> {
    error("No LocalNavController provided")
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalNavController provides navController) {
        SetupNavGraph(navController = navController)
    }
}
