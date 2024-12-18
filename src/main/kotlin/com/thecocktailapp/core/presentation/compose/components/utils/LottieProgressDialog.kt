package com.thecocktailapp.core.presentation.compose.components.utils

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thecocktailapp.core.R
import com.thecocktailapp.core.presentation.compose.components.images.LottieImage

@Composable
fun LottieProgressDialog(modifier: Modifier = Modifier, @RawRes animationId: Int) {
    LottieImage(modifier = modifier, animationId = animationId)
}

@Preview
@Composable
private fun LottieProgressDialogPrev() {
    LottieProgressDialog(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 60.dp)
            .aspectRatio(ratio = 1f),
        animationId = R.raw.core_image_loading
    )
}