package com.mzs.core.presentation.components.compose.buttons

import android.view.MotionEvent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.mzs.core.R
import com.mzs.core.presentation.components.compose.images.ResourceImage

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FallButton(
    modifier: Modifier = Modifier,
    iconColor: Color,
    text: String,
    textColor: Color,
    textStyle: TextStyle,
    onButtonClicked: () -> Unit
) {

    var buttonSize by remember { mutableStateOf(value = IntSize.Zero) }
    var isClickAvailable by remember { mutableStateOf(value = true) }
    var isPressed by remember { mutableStateOf(false) }
    var isVisible by remember { mutableStateOf(true) }

    val offset by animateDpAsState(
        animationSpec = tween(durationMillis = 300),
        targetValue = if (isPressed) 24.dp else 0.dp,
        label = ""
    )

    AnimatedVisibility(visible = isVisible, exit = fadeOut()) {
        Card(
            modifier = modifier
                .offset { IntOffset(x = 0, y = offset.value.toInt()) }
                .clip(shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp))
                .pointerInteropFilter { motionEvent ->
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
                                isVisible = false
                                onButtonClicked()
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
            shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp),
            content = {
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
                            iconTint = iconColor,
                            size = 16.dp
                        )
                        Text(
                            modifier = Modifier.padding(vertical = 16.dp),
                            color = textColor,
                            style = textStyle,
                            text = text.uppercase(),
                            textAlign = TextAlign.Center
                        )
                        ResourceImage(
                            iconId = R.drawable.core_ic_arrow_fall,
                            iconTint = iconColor,
                            size = 16.dp
                        )
                    }
                )
            }
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun FallButtonPrev() {
    FallButton(
        modifier = Modifier.fillMaxWidth(),
        iconColor = Color.Black,
        text = "Accept",
        textColor = Color.Black,
        textStyle = MaterialTheme.typography.titleSmall,
        onButtonClicked = { /*Here will go the action when clicking on the button*/ }
    )
}