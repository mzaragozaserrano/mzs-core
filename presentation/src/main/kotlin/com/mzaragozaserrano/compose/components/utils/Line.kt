package com.mzaragozaserrano.compose.components.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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
            .height(height = height),
        color = color
    )
}

@Preview
@Composable
private fun LinePrev() {
    Line(modifier = Modifier.padding(all = 16.dp))
}