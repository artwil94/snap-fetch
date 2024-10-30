package com.example.snapfetch.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {

    @Serializable
    data object Photos : Screen()

    @Serializable
    data class PhotoDetails(val photoId: String = "") : Screen()

    @Serializable
    data object Splash : Screen()
}
