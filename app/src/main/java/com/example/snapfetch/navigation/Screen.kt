package com.example.snapfetch.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {

    @Serializable
    data object Photos : Screen()

    @Serializable
    data object PhotoDetails : Screen()
}
