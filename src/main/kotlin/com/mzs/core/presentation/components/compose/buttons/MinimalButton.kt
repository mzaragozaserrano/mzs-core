package com.mzs.core.presentation.components.compose.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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
    onButtonClicked: () -> Unit,
) {
    RoundedBackground(
        modifier = modifier
            .aspectRatio(ratio = 1f)
            .clickable(
                indication = ripple(
                    color = MaterialTheme.colorScheme.background.copy(alpha = 0.2f)
                ),
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                    onButtonClicked()
                }
            ),
        backgroundColor = Color.Transparent,
        cornerRadius = 12.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 8.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            ResourceImage(
                iconId = iconId,
                iconTint = iconTint,
                size = 36.dp
            )
            Text(color = textColor, text = text)
        }
    }
}

@Preview
@Composable
private fun MinimalButtonPrev() {
    MinimalButton(iconId = R.drawable.core_ic_cloud, text = "Accept", textColor = Color.Black) {
        //Here will go the action when clicking on the button
    }
}