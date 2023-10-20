package com.mzaragozaserrano.compose.composables.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Line(
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    height: Dp = 1.dp,
) {
    Divider(
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        color = color
    )
}