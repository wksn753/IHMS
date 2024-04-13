package ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable


private val LightColors = lightColors(
    primary = IHMSColors.Day.primary,
    surface = IHMSColors.Day.surface,
    background = IHMSColors.Day.background,
    onPrimary = IHMSColors.Day.text
)


private val DarkColors = darkColors(
    primary = IHMSColors.Night.primary,
    surface = IHMSColors.Night.surface,
    background = IHMSColors.Night.background,
    onPrimary = IHMSColors.Night.text
)

@Composable
fun IHMSTheme( content: @Composable () -> Unit){
    MaterialTheme(
        colors = if (isSystemInDarkTheme()) DarkColors else LightColors,
    ) {
        content()
    }
}
