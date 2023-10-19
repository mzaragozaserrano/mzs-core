package com.mzaragozaserrano.compose.composables.utils

import androidx.annotation.ColorRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mzaragozaserrano.presentation.R

@Composable
fun Line(
    modifier: Modifier = Modifier,
    height: Dp = 1.dp,
    @ColorRes color: Int = R.color.black,
) {
    Divider(
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        color = colorResource(id = color)
    )
}