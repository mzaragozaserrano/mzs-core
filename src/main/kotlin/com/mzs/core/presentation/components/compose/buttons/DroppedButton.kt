package com.mzs.core.presentation.components.compose.buttons

import android.view.MotionEvent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mzs.core.R
import com.mzs.core.presentation.components.compose.images.ResourceImage
import com.mzs.core.presentation.utils.generic.emptyText

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DroppedButton(
    modifier: Modifier = Modifier,
    buttonContentColor: Color,
    text: String,
    textStyle: TextStyle,
    onButtonClicked: (() -> Unit)? = null,
    invisibleContent: @Composable () -> Unit,
) {

    var buttonSize by remember { mutableStateOf(value = IntSize.Zero) }
    var isClickAvailable by remember { mutableStateOf(value = true) }
    var isLaunchingAction by remember { mutableStateOf(value = false) }
    var isPressed by remember { mutableStateOf(value = false) }
    var isVisible by remember { mutableStateOf(value = true) }

    val offset by animateDpAsState(
        animationSpec = tween(durationMillis = 300),
        targetValue = if (isPressed) 24.dp else 0.dp,
        label = ""
    )

    Card(
        modifier = modifier
            .offset { IntOffset(x = 0, y = offset.value.toInt()) }
            .pointerInteropFilter { motionEvent ->
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
                            isVisible = false
                            isLaunchingAction = true
                            onButtonClicked?.invoke()
                        } else {
                            isClickAvailable = true
                        }
                    }
                }
                true
            }
            .onGloballyPositioned { layoutCoordinates ->
                buttonSize = layoutCoordinates.size
            },
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        content = {
            AnimatedContent(
                targetState = isLaunchingAction,
                transitionSpec = {
                    if (targetState) {
                        fadeIn(animationSpec = tween(durationMillis = 300)) togetherWith
                                fadeOut(animationSpec = tween(300))
                    } else {
                        slideInVertically(initialOffsetY = { fullHeight -> fullHeight }) togetherWith
                                fadeOut(animationSpec = tween(300))
                    }
                },
                label = emptyText
            ) { isPressed ->
                if (isPressed) {
                    invisibleContent()
                } else {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                            .align(alignment = Alignment.CenterHorizontally),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        content = {
                            ResourceImage(
                                iconId = R.drawable.core_ic_arrow_fall,
                                iconTint = buttonContentColor,
                                size = 16.dp
                            )
                            Text(
                                modifier = Modifier.padding(vertical = 16.dp),
                                color = buttonContentColor,
                                fontSize = 18.sp,
                                style = textStyle,
                                text = text.uppercase(),
                                textAlign = TextAlign.Center
                            )
                            ResourceImage(
                                iconId = R.drawable.core_ic_arrow_fall,
                                iconTint = buttonContentColor,
                                size = 16.dp
                            )
                        }
                    )
                }
            }
        }
    )

}

@PreviewLightDark
@Composable
private fun DroppedButtonPrev() {
    DroppedButton(
        modifier = Modifier.fillMaxWidth(),
        buttonContentColor = Color.Black,
        text = "Accept",
        textStyle = MaterialTheme.typography.titleSmall,
        onButtonClicked = { /*Here will go the action when clicking on the button*/ },
        invisibleContent = { /*Here will go the content when the button is pressed*/ }
    )
}