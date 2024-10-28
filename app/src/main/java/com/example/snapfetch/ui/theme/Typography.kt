package com.example.snapfetch.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class SfTypography(
    val author: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 22.sp,
        fontFamily = SfTheme.fonts.freigeistMedium,
        fontWeight = FontWeight(600)
    ),
    val photoId: TextStyle = TextStyle(
        fontSize = 14.sp,
        lineHeight = 16.sp,
        fontFamily = SfTheme.fonts.freigeistMedium,
        fontWeight = FontWeight(600)
    ),
    val screenTitle: TextStyle = TextStyle(
        fontSize = 40.sp,
        lineHeight = 40.sp,
        fontFamily = SfTheme.fonts.freigeistMedium,
        fontWeight = FontWeight(550),
        color = Color.White,
    ),
)