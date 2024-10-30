package com.example.snapfetch.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import com.example.snapfetch.navigation.LocalNavController


@Composable
fun PreviewWrapper(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalNavController provides rememberNavController(),
        content = content
    )
}