package com.mzs.core.presentation.components.utils

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mzs.core.presentation.utils.generic.ItemOrientation

@Composable
fun Line(
    modifier: Modifier = Modifier,
    color: Color,
    itemOrientation: ItemOrientation = ItemOrientation.Vertical,
    thickness: Dp = 1.dp,
) {
    when (itemOrientation) {
        is ItemOrientation.Horizontal -> {
            VerticalDivider(
                modifier = modifier.fillMaxHeight(),
                color = color,
                thickness = thickness
            )
        }

        is ItemOrientation.Vertical -> {
            HorizontalDivider(
                modifier = modifier.fillMaxWidth(),
                color = color,
                thickness = thickness
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LinePrev() {
    Line(color = Color.Black)
}