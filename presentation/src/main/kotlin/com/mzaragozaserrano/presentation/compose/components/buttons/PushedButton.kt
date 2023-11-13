package com.mzaragozaserrano.presentation.compose.components.buttons

import androidx.annotation.StringRes
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mzaragozaserrano.presentation.R
import com.mzaragozaserrano.presentation.compose.components.texts.SmallText

@Composable
fun PushedButton(
    modifier: Modifier = Modifier,
    buttonBackgroundColor: Color = Color.LightGray,
    isAnimationEnabled: Boolean = false,
    textColor: Color = Color.Black,
    @StringRes textId: Int,
    onButtonClicked: () -> Unit,
) {

    var isPressed by remember { mutableStateOf(false) }
    val scale = remember { Animatable(1f) }

    LaunchedEffect(isPressed && isAnimationEnabled) {
        if (isPressed) {
            scale.animateTo(
                targetValue = 0.93f,
                animationSpec = keyframes {
                    durationMillis = 25
                    1.0f at 0 with LinearEasing
                    0.93f atFraction 0.5f with LinearEasing
                    1.0f at 1 with LinearEasing
                }
            )
            scale.animateTo(targetValue = 1f, animationSpec = spring())
            isPressed = false
        }
    }

    Card(
        modifier = modifier
            .scale(scale = scale.value)
            .clip(shape = CircleShape)
            .clickable(
                indication = if (isAnimationEnabled) null else rememberRipple(
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f)
                ),
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                    isPressed = true
                    onButtonClicked()
                }
            ),
        colors = CardDefaults.cardColors(containerColor = buttonBackgroundColor),
        shape = CircleShape
    ) {
        SmallText(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .align(Alignment.CenterHorizontally),
            color = textColor,
            text = stringResource(id = textId).uppercase(),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun PushedButtonPrev() {
    PushedButton(textId = R.string.core_button_text_message, isAnimationEnabled = true) {
        //Here will go the action when clicking on the button
    }
}

@Preview
@Composable
private fun PushedButtonNoAnimationPrev() {
    PushedButton(textId = R.string.core_button_text_message) {
        //Here will go the action when clicking on the button
    }
}