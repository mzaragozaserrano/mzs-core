package com.mzs.core.presentation.components.compose.texts

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.mzs.core.presentation.utils.generic.emptyText

@Composable
fun BlinkingText(
    modifier: Modifier = Modifier,
    color: Color,
    fontWeight: FontWeight? = null,
    text: String,
    textStyle: TextStyle
) {

    val isBlinking by remember { mutableStateOf(value = true) }

    val infiniteTransition = rememberInfiniteTransition(label = emptyText)
    val alpha by infiniteTransition.animateFloat(
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500),
            repeatMode = RepeatMode.Reverse
        ),
        initialValue = 1f,
        targetValue = if (isBlinking) 0.3f else 1f,
        label = emptyText,
    )

    val scale by infiniteTransition.animateFloat(
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500),
            repeatMode = RepeatMode.Reverse
        ),
        initialValue = 1f,
        targetValue = if (isBlinking) 0.95f else 1f,
        label = emptyText,
    )

    Text(
        modifier = modifier
            .alpha(alpha = alpha)
            .scale(scale = scale),
        color = color,
        fontWeight = fontWeight,
        text = text,
        style = textStyle
    )

}

@PreviewLightDark
@Composable
private fun PreviewBlinkingText() {
    BlinkingText(
        color = Color.Black,
        text = "This is a trial text",
        textStyle = MaterialTheme.typography.titleLarge
    )
}