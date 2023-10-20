package com.mzaragozaserrano.compose.composables.images

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource

@Composable
fun ResourceImage(
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    @DrawableRes iconId: Int,
    iconTint: Color? = null,
) {
    Image(
        modifier = modifier,
        contentDescription = contentDescription,
        colorFilter = iconTint?.let { color ->
            ColorFilter.tint(color = color)
        },
        painter = painterResource(id = iconId)
    )
}