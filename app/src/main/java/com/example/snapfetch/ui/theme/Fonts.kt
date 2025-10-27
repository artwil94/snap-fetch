package com.example.snapfetch.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.snapfetch.R

data class Fonts(
    val freigeistMedium: FontFamily = FontFamily(Font(R.font.freigeist_xconmedium)),
    val robotoMedium: FontFamily = FontFamily(Font(R.font.roboto_medium)),
    val robotoBold: FontFamily = FontFamily(Font(R.font.roboto_bold)),
    val robotoLight: FontFamily = FontFamily(Font(R.font.roboto_light)),
    val robotoRegular: FontFamily = FontFamily(Font(R.font.roboto_regular))
)