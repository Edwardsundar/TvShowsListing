package com.demo.tvserieslisting.ui.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)    // light wigets
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71) // dark for wigets
val Pink40 = Color(0xFF7D5260)

val DarkGray = Color(0xFF181D2D)


sealed class ThemeColors(
    val background : Color,
    val surface : Color,
    val primary : Color,
    val primaryText : Color,
    val secondaryText : Color
){
    object Night : ThemeColors(
        background = DarkGray,
        surface = Color(0xFF181D00),
        primary = Color(0xFF6650a4),
        primaryText = Color.White,
        secondaryText = Color.DarkGray
    )
    object Day : ThemeColors(
        background = Color.White,
        surface = Color(0xFF181D00),
        primary = Color(0xFF6650a4),
        primaryText = Color.Black,
        secondaryText = Color.DarkGray
    )

}