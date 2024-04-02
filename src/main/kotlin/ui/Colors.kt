package ui

import androidx.compose.ui.graphics.Color

sealed class IHMSColors(
    val background: Color,
    val surface: Color,
    val primary: Color,
    val text: Color,
){
    object Night:IHMSColors(
        primary = Color(0xFF39AFEA),
        surface = Color(0xFF666362),
        background = Color(0xFF000000),
        text = Color(0xFFFFFFFF),
    )
    object Day:IHMSColors(
        primary = Color(0xFF5E69EE),
        surface = Color(0xFF39AFEA),
        background = Color(0xFFF4F4FB),
        text = Color(0xFF000000),
    )
}