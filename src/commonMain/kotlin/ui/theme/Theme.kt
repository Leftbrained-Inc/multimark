package ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import ui.utils.Scale

private val DarkColorScheme = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    errorContainer = md_theme_dark_errorContainer,
    onError = md_theme_dark_onError,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    outline = md_theme_dark_outline,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inverseSurface = md_theme_dark_inverseSurface,
    inversePrimary = md_theme_dark_inversePrimary,
    surfaceTint = md_theme_dark_surfaceTint,
    outlineVariant = md_theme_dark_outlineVariant,
    scrim = md_theme_dark_scrim,
)

private val LightColorScheme = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    errorContainer = md_theme_light_errorContainer,
    onError = md_theme_light_onError,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    outline = md_theme_light_outline,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inverseSurface = md_theme_light_inverseSurface,
    inversePrimary = md_theme_light_inversePrimary,
    surfaceTint = md_theme_light_surfaceTint,
    outlineVariant = md_theme_light_outlineVariant,
    scrim = md_theme_light_scrim,
)

@Composable
fun MultimarkAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true, content: @Composable () -> Unit
) {
    var type by remember { mutableStateOf(Typography) }
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // Жёстко нахордкодил fontScaling
    LaunchedEffect(Scale.scale) {
        val fontScaleWithScale = Scale.scale * Scale.fontScale
        val newType = type.copy(
            displayLarge = Typography.displayLarge.copy(fontSize = Typography.displayLarge.fontSize * fontScaleWithScale),
            displayMedium = Typography.displayMedium.copy(fontSize = Typography.displayMedium.fontSize * fontScaleWithScale),
            displaySmall = Typography.displaySmall.copy(fontSize = Typography.displaySmall.fontSize * fontScaleWithScale),
            headlineLarge = Typography.headlineLarge.copy(fontSize = Typography.headlineLarge.fontSize * fontScaleWithScale),
            headlineMedium = Typography.headlineMedium.copy(fontSize = Typography.headlineMedium.fontSize * fontScaleWithScale),
            headlineSmall = Typography.headlineSmall.copy(fontSize = Typography.headlineSmall.fontSize * fontScaleWithScale),
            titleLarge = Typography.titleLarge.copy(fontSize = Typography.titleLarge.fontSize * fontScaleWithScale),
            titleMedium = Typography.titleMedium.copy(fontSize = Typography.titleMedium.fontSize * fontScaleWithScale),
            titleSmall = Typography.titleSmall.copy(fontSize = Typography.titleSmall.fontSize * fontScaleWithScale),
            bodyLarge = Typography.bodyLarge.copy(fontSize = Typography.bodyLarge.fontSize * fontScaleWithScale),
            bodyMedium = Typography.bodyMedium.copy(fontSize = Typography.bodyMedium.fontSize * fontScaleWithScale),
            bodySmall = Typography.bodySmall.copy(fontSize = Typography.bodySmall.fontSize * fontScaleWithScale),
            labelLarge = Typography.labelLarge.copy(fontSize = Typography.labelLarge.fontSize * fontScaleWithScale),
            labelMedium = Typography.labelMedium.copy(fontSize = Typography.labelMedium.fontSize * fontScaleWithScale),
            labelSmall = Typography.labelSmall.copy(fontSize = Typography.labelSmall.fontSize * fontScaleWithScale),
        )
        type = newType
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = type,
        content = content
    )
}