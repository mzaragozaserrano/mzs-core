package com.mzaragozaserrano.presentation.compose.components.buttons

import android.view.MotionEvent
import androidx.annotation.StringRes
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mzaragozaserrano.presentation.R
import com.mzaragozaserrano.presentation.compose.components.images.ResourceImage
import com.mzaragozaserrano.presentation.compose.components.texts.NormalBoldText
import com.mzaragozaserrano.presentation.view.utils.emptyLambda

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FallButton(
    modifier: Modifier = Modifier,
    iconColor: Color,
    textColor: Color,
    @StringRes textId: Int,
    onButtonClicked: () -> Unit,
    onButtonPressed: () -> Unit = emptyLambda,
) {

    var isPressed by remember { mutableStateOf(false) }
    var isVisible by remember { mutableStateOf(true) }

    val offset by animateDpAsState(
        animationSpec = tween(durationMillis = 300),
        targetValue = if (isPressed) 25.dp else 0.dp,
        label = ""
    )

    AnimatedVisibility(visible = isVisible, exit = fadeOut()) {
        Card(
            modifier = modifier
                .offset(y = offset)
                .clip(shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp))
                .pointerInteropFilter {
                    when (it.action) {
                        MotionEvent.ACTION_DOWN -> {
                            isPressed = true
                            onButtonPressed()
                        }

                        MotionEvent.ACTION_UP -> {
                            isVisible = false
                            onButtonClicked()
                        }
                    }
                    true
                },
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp).align(alignment = Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.spacedBy(
                    space = 12.dp,
                    alignment = Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ResourceImage(
                    iconId = R.drawable.core_ic_arrow_fall,
                    iconTint = iconColor,
                    size = 18.dp
                )
                NormalBoldText(
                    modifier = Modifier.padding(vertical = 16.dp),
                    color = textColor,
                    text = stringResource(id = textId).uppercase(),
                    textAlign = TextAlign.Center
                )
                ResourceImage(
                    iconId = R.drawable.core_ic_arrow_fall,
                    iconTint = iconColor,
                    size = 18.dp
                )
            }
        }
    }

}

@Preview(backgroundColor = 0xFFFFFFFF)
@Composable
private fun FallButtonPrev() {
    FallButton(
        modifier = Modifier.fillMaxWidth(),
        iconColor = Color.Black,
        textColor = Color.Black,
        textId = R.string.core_button_text_message,
        onButtonClicked = {
            //Here will go the action when clicking on the button
        }
    )
}