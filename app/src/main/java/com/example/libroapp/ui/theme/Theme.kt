package com.example.libroapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LibrosColorScheme = lightColorScheme(
    primary = Color(0xFF1565C0),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFD1E4FF),
    onPrimaryContainer = Color(0xFF001D36),
    secondary = Color(0xFF535F70),
    onSecondary = Color(0xFFFFFFFF),
    surface = Color(0xFFF8F9FA),
    onSurface = Color(0xFF1A1C1E),
    surfaceVariant = Color(0xFFDFE2EB),
    onSurfaceVariant = Color(0xFF43474E),
    background = Color(0xFFF8F9FA),
    onBackground = Color(0xFF1A1C1E)
)

@Composable
fun LibrosAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LibrosColorScheme,
        typography = MaterialTheme.typography,
        content = content
    )
}
