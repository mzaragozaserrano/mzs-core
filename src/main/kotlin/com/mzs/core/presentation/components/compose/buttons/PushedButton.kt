package com.mzs.core.presentation.components.compose.buttons

import android.view.MotionEvent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.mzs.core.presentation.utils.extensions.conditional

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PushedButton(
    modifier: Modifier = Modifier,
    buttonBackgroundColor: Color,
    buttonBackgroundLoadingColor: Color,
    durationMillisBlockingButton: Int? = null,
    text: String,
    textColor: Color,
    textStyle: TextStyle,
    onButtonClicked: () -> Unit
) {

    //Pressed animation
    var buttonSize by remember { mutableStateOf(value = IntSize.Zero) }
    var isPressed by remember { mutableStateOf(value = false) }
    var isLaunchingAction by remember { mutableStateOf(value = false) }
    var isClickAvailable by remember { mutableStateOf(value = true) }
    val scale = animateFloatAsState(
        targetValue = if (isPressed) 0.93f else 1f,
        label = ""
    )

    //Loading animation
    val progress = remember { Animatable(initialValue = 0f) }

    LaunchedEffect(key1 = isLaunchingAction, key2 = progress) {
        if (durationMillisBlockingButton != null) {
            if (isLaunchingAction) {
                progress.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(durationMillis = durationMillisBlockingButton)
                )
            }
            if (progress.value == 1f) {
                progress.snapTo(targetValue = 0f)
                isLaunchingAction = false
            }
        }
    }

    Box(
        modifier = modifier
            .scale(scale = scale.value)
            .conditional(condition = progress.isRunning.not() || durationMillisBlockingButton == null) {
                pointerInteropFilter { motionEvent ->
                    when (motionEvent.action) {
                        MotionEvent.ACTION_DOWN -> {
                            isPressed = true
                        }

                        MotionEvent.ACTION_MOVE -> {
                            if (isPressed) {
                                val x = motionEvent.x.toInt()
                                val y = motionEvent.y.toInt()
                                isPressed =
                                    x in 0 until buttonSize.width && y in 0 until buttonSize.height
                                isClickAvailable = isPressed
                            }
                        }

                        MotionEvent.ACTION_UP -> {
                            if (isClickAvailable) {
                                isPressed = false
                                isLaunchingAction = true
                                onButtonClicked()
                            } else {
                                isClickAvailable = true
                            }
                        }
                    }
                    true
                }
            }
            .onGloballyPositioned { layoutCoordinates ->
                buttonSize = layoutCoordinates.size
            }
            .clip(shape = RoundedCornerShape(size = 8.dp))
    ) {
        if (progress.isRunning) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(buttonBackgroundLoadingColor)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(fraction = progress.value)
                        .height(height = 40.dp)
                        .background(color = buttonBackgroundColor)
                        .padding(vertical = 8.dp)
                )
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(size = 32.dp)
                        .align(alignment = Alignment.Center),
                    color = textColor.copy(alpha = 0.2f)
                )
            }
        } else {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.Center)
                    .background(color = buttonBackgroundColor)
                    .padding(vertical = 8.dp),
                color = textColor,
                style = textStyle,
                text = text.uppercase(),
                textAlign = TextAlign.Center
            )
        }
    }

}

@Preview(apiLevel = 34)
@Composable
private fun PushedButtonPrev() {
    PushedButton(
        buttonBackgroundColor = Color.Red,
        buttonBackgroundLoadingColor = Color.LightGray,
        durationMillisBlockingButton = 3000,
        textColor = Color.Black,
        text = "Accept",
        textStyle = MaterialTheme.typography.titleMedium
    ) {
        //Here will go the action when clicking on the button
    }
}