package com.mzaragozaserrano.compose.composables.buttons

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mzaragozaserrano.compose.composables.images.ResourceImage
import com.mzaragozaserrano.presentation.R

@Composable
fun WavyButton(
    modifier: Modifier = Modifier,
    buttonBackgroundColor: Color = Color.LightGray,
    iconBackgroundColor: Color = Color.White,
    @DrawableRes iconId: Int,
    iconTint: Color = Color.Black,
    isAnimationEnabled: Boolean = false,
    textColor: Color = Color.Black,
    @StringRes textId: Int,
    onButtonClicked: () -> Unit,
) {

    val imageSize: Dp = 24.dp

    var isWave by remember { mutableStateOf(false) }

    val scale = remember { Animatable(1f) }

    LaunchedEffect(isWave && isAnimationEnabled) {
        if (isWave) {
            scale.animateTo(
                1.07f,
                animationSpec = keyframes {
                    durationMillis = 200
                    1.0f at 0 with LinearEasing
                    1.05f atFraction 0.25f with CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f)
                    1.0f atFraction 0.5f with LinearEasing
                    1.07f atFraction 0.75f with CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f)
                    1.0f at 1 with LinearEasing
                }
            )
            scale.animateTo(1f, animationSpec = spring())
            isWave = false
        }
    }

    Card(
        modifier = modifier
            .scale(scale.value)
            .clip(
                RoundedCornerShape(
                    bottomEnd = imageSize,
                    topStart = imageSize
                )
            )
            .clickable {
                isWave = true
                onButtonClicked()
            }
            .indication(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    color = buttonBackgroundColor.copy(alpha = 0.2f),
                    radius = 8.dp
                )
            ),
        colors = CardDefaults.cardColors(containerColor = buttonBackgroundColor),
        shape = RoundedCornerShape(
            bottomEnd = imageSize,
            topStart = imageSize
        )
    ) {
        Row(
            modifier = Modifier.padding(all = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp, CenterHorizontally),
            verticalAlignment = CenterVertically
        ) {
            Card(
                modifier = Modifier
                    .height(imageSize * 2)
                    .width(imageSize * 2),
                colors = CardDefaults.cardColors(containerColor = iconBackgroundColor),
                shape = RoundedCornerShape(
                    bottomEnd = imageSize,
                    topEnd = imageSize,
                    topStart = imageSize
                )
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
                    ResourceImage(
                        modifier = Modifier
                            .padding(all = 4.dp)
                            .width(imageSize)
                            .height(imageSize),
                        iconTint = iconTint,
                        iconId = iconId
                    )
                }
            }
            Text(
                modifier = Modifier.weight(1f),
                color = textColor,
                fontSize = 18.sp,
                text = stringResource(id = textId).uppercase(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun ButtonImageTextPrev() {
    WavyButton(
        textId = R.string.button_text_message,
        iconId = R.drawable.ic_cloud
    ) {
        //Here will go the action when clicking on the button
    }
}