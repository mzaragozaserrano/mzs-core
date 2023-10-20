package presentationapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


private val LightColorScheme = lightColorScheme(
    primary = DarkGreen,
    onPrimary = White,
    primaryContainer = LightMint,
    onPrimaryContainer = DeepGreenShade,
    secondary = MossGreen,
    onSecondary = White,
    secondaryContainer = LightMintTint,
    onSecondaryContainer = DarkGreenShade,
    tertiary = DeepBlue,
    onTertiary = White,
    tertiaryContainer = White,
    onTertiaryContainer = Black,
    error = FireRed,
    errorContainer = LightCoral,
    onError = White,
    onErrorContainer = FireRedShade,
    background = LightGray,
    onBackground = DarkGray,
    surface = LightGray,
    onSurface = DarkGray,
    surfaceVariant = GrayBlue,
    onSurfaceVariant = DarkSlate,
    outline = SlateGray,
    inverseOnSurface = PaleGray,
    inverseSurface = Charcoal,
    inversePrimary = Green,
    surfaceTint = DarkGreen,
    outlineVariant = SilverGray,
    scrim = Black,
)

private val DarkColorScheme = darkColorScheme(
    primary = Green,
    onPrimary = DarkTeal,
    primaryContainer = DeepGreen,
    onPrimaryContainer = LightMint,
    secondary = SeaFoamGreen,
    onSecondary = DarkMossGreen,
    secondaryContainer = SlateGreen,
    onSecondaryContainer = LightMintTint,
    tertiary = LightSkyBlue,
    onTertiary = DarkCyan,
    tertiaryContainer = Black,
    onTertiaryContainer = White,
    error = LightSalmon,
    errorContainer = DarkCrimson,
    onError = DarkReddish,
    onErrorContainer = LightCoral,
    background = DarkGray,
    onBackground = LightPlain,
    surface = DarkGray,
    onSurface = LightPlain,
    surfaceVariant = DarkSlate,
    onSurfaceVariant = SilverGray,
    outline = CharcoalGray,
    inverseOnSurface = DarkGray,
    inverseSurface = LightPlain,
    inversePrimary = DarkGreen,
    surfaceTint = Green,
    outlineVariant = DarkSlate,
    scrim = Black
)

@Composable
fun PresentationAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}