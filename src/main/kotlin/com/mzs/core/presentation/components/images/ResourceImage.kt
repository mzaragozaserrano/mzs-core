package com.mzs.core.presentation.components.images

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp

@Composable
fun ResourceImage(
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    @DrawableRes iconId: Int,
    iconTint: Color? = null,
    size: Dp,
) {
    Image(
        modifier = modifier.size(size = size),
        contentDescription = contentDescription,
        colorFilter = iconTint?.let { color ->
            ColorFilter.tint(color = color)
        },
        painter = painterResource(id = iconId)
    )
}