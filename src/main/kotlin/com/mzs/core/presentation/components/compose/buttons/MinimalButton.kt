package com.mzs.core.presentation.components.compose.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mzs.core.R
import com.mzs.core.presentation.components.compose.backgrounds.RoundedBackground
import com.mzs.core.presentation.components.compose.images.ResourceImage

@Composable
fun MinimalButton(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int,
    iconTint: Color? = null,
    text: String,
    textColor: Color,
    textStyle: TextStyle,
    onButtonClicked: () -> Unit,
) {
    RoundedBackground(
        modifier = modifier
            .clickable(
                indication = ripple(
                    color = MaterialTheme.colorScheme.background.copy(alpha = 0.2f)
                ),
                interactionSource = remember { MutableInteractionSource() },
                onClick = onButtonClicked
            ),
        backgroundColor = Color.Transparent,
        cornerRadius = 12.dp,
        content = {
            Column(
                modifier = Modifier
                    .padding(all = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    space = 8.dp,
                    alignment = Alignment.CenterVertically
                ),
                content = {
                    ResourceImage(iconId = iconId, iconTint = iconTint, size = 36.dp)
                    Text(
                        modifier = Modifier.widthIn(max = 72.dp),
                        color = textColor,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        text = text,
                        textAlign = TextAlign.Center,
                        style = textStyle
                    )
                }
            )
        }
    )
}

@PreviewLightDark
@Composable
private fun MinimalButtonPrev() {
    MinimalButton(
        iconId = R.drawable.core_ic_cloud,
        text = "Accept",
        textColor = Color.Black,
        textStyle = MaterialTheme.typography.labelLarge,
        onButtonClicked = { /*Here will go the action when clicking on the button*/ }
    )
}