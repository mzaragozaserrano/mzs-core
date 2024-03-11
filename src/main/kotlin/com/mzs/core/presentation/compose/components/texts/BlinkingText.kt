package com.mzs.core.presentation.compose.components.texts

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mzs.core.presentation.R
import com.mzs.core.presentation.compose.utils.FontSize
import com.mzs.core.presentation.compose.utils.Text

@Composable
fun BlinkingText(modifier: Modifier = Modifier, color: Color = Color.Black, fontSize: Text = FontSize.ExtraLarge, text: String) {

    val isBlinking by remember { mutableStateOf(true) }

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val alpha by infiniteTransition.animateFloat(
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500),
            repeatMode = RepeatMode.Reverse
        ),
        initialValue = 1f,
        targetValue = if (isBlinking) 0.3f else 1f,
        label = "",
    )

    val scale by infiniteTransition.animateFloat(
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500),
            repeatMode = RepeatMode.Reverse
        ),
        initialValue = 1f,
        targetValue = if (isBlinking) 0.95f else 1f,
        label = "",
    )

    when(fontSize) {
        is FontSize.ExtraLarge -> {
            ExtraLargeBlackText(
                modifier = modifier
                    .alpha(alpha = alpha)
                    .scale(scale = scale),
                color = color,
                text = text
            )
        }
        is FontSize.ExtraSmall -> {
            ExtraSmallBlackText(
                modifier = modifier
                    .alpha(alpha = alpha)
                    .scale(scale = scale),
                color = color,
                text = text
            )
        }
        is FontSize.Large -> {
            LargeBlackText(
                modifier = modifier
                    .alpha(alpha = alpha)
                    .scale(scale = scale),
                color = color,
                text = text
            )
        }
        is FontSize.Normal -> {
            ExtraLargeBlackText(
                modifier = modifier
                    .alpha(alpha = alpha)
                    .scale(scale = scale),
                color = color,
                text = text
            )
        }
        is FontSize.Small -> {
            SmallBlackText(
                modifier = modifier
                    .alpha(alpha = alpha)
                    .scale(scale = scale),
                color = color,
                text = text
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewBlinkingText() {

    BlinkingText(text = stringResource(id = R.string.core_hello_world))

}