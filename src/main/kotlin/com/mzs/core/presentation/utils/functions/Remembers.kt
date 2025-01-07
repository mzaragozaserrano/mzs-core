package com.mzs.core.presentation.utils.functions

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.mzs.core.presentation.utils.generic.emptyText

@Composable
fun rememberShimmerBrush(color: Color): Brush {
    val shimmerAnimation = rememberInfiniteTransition(label = emptyText)
    val shimmerTranslateX by shimmerAnimation.animateFloat(
        initialValue = -1000f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = emptyText
    )

    return Brush.linearGradient(
        colors = listOf(
            color,
            color.copy(alpha = 0.3f),
            color
        ),
        start = Offset(x = shimmerTranslateX, y = 0f),
        end = Offset(x = shimmerTranslateX + 300f, y = 0f)
    )
}