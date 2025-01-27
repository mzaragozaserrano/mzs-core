package com.mzs.core.presentation.components.compose.utils

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mzs.core.presentation.utils.generic.ItemOrientation

@Composable
fun Line(
    modifier: Modifier = Modifier,
    color: Color,
    itemOrientation: ItemOrientation,
) {
    when (itemOrientation) {
        is ItemOrientation.Horizontal -> {
            VerticalDivider(
                modifier = modifier.fillMaxHeight(),
                color = color,
                thickness = 1.dp
            )
        }

        is ItemOrientation.Vertical -> {
            HorizontalDivider(
                modifier = modifier.fillMaxWidth(),
                color = color,
                thickness = 1.dp
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun LinePrev() {
    Line(color = Color.Black, itemOrientation = ItemOrientation.Vertical)
}