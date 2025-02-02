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
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mzs.core.presentation.utils.extensions.conditional
import com.mzs.core.presentation.utils.generic.emptyText

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PushedButton(
    modifier: Modifier = Modifier,
    buttonBackgroundColor: Color,
    durationMillisBlockingButton: Int? = null,
    text: String,
    textColor: Color,
    textStyle: TextStyle,
    onButtonClicked: () -> Unit,
) {

    var buttonSize by remember { mutableStateOf(value = IntSize.Zero) }
    var isClickAvailable by remember { mutableStateOf(value = true) }
    var isLaunchingAction by remember { mutableStateOf(value = false) }
    var isPressed by remember { mutableStateOf(value = false) }
    val scale = animateFloatAsState(
        targetValue = if (isPressed) 0.93f else 1f,
        label = emptyText
    )

    val progress = remember { Animatable(initialValue = 0f) }
    var textSize by remember { mutableStateOf(value = IntSize.Zero) }

    LaunchedEffect(
        key1 = isLaunchingAction,
        block = {
            when {
                durationMillisBlockingButton != null && isLaunchingAction -> {
                    progress.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(durationMillis = durationMillisBlockingButton)
                    )
                    progress.snapTo(0f)
                    isLaunchingAction = false
                    onButtonClicked()
                }

                isLaunchingAction -> {
                    isLaunchingAction = false
                    onButtonClicked()
                }
            }
        }
    )

    Box(
        modifier = modifier
            .scale(scale = scale.value)
            .conditional(condition = progress.isRunning.not() || durationMillisBlockingButton == null) {
                pointerInteropFilter { motionEvent ->
                    when (motionEvent.action) {
                        MotionEvent.ACTION_CANCEL -> {
                            isPressed = false
                        }

                        MotionEvent.ACTION_DOWN -> {
                            isPressed = true
                        }

                        MotionEvent.ACTION_MOVE -> {
                            val x = motionEvent.x.toInt()
                            val y = motionEvent.y.toInt()
                            isPressed =
                                x in 0 until buttonSize.width && y in 0 until buttonSize.height
                            isClickAvailable = isPressed
                        }

                        MotionEvent.ACTION_UP -> {
                            if (isClickAvailable) {
                                isPressed = false
                                isLaunchingAction = true
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
            .clip(shape = RoundedCornerShape(size = 8.dp)),
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = buttonBackgroundColor.copy(alpha = 0.2f)),
                content = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(fraction = if (progress.isRunning) progress.value else 1f)
                            .height(height = (textSize.height.dp / 2) + 16.dp)
                            .background(color = buttonBackgroundColor)
                    )
                    if (progress.isRunning) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(size = 24.dp)
                                .align(alignment = Alignment.Center),
                            color = textColor.copy(alpha = 0.2f)
                        )
                    } else {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(alignment = Alignment.Center)
                                .padding(vertical = 12.dp)
                                .onGloballyPositioned { layoutCoordinates ->
                                    textSize = layoutCoordinates.size
                                },
                            color = textColor,
                            fontSize = 18.sp,
                            style = textStyle,
                            text = text.uppercase(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            )
        }
    )

}

@PreviewLightDark
@Composable
private fun PushedButtonPrev() {
    PushedButton(
        buttonBackgroundColor = Color.Red,
        durationMillisBlockingButton = 3000,
        text = "Accept",
        textColor = Color.Black,
        textStyle = MaterialTheme.typography.titleSmall,
        onButtonClicked = { /*Here will go the action when clicking on the button*/ }
    )
}