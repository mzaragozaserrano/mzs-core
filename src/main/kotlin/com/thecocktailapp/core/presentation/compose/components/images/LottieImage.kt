package com.thecocktailapp.core.presentation.compose.components.images

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.thecocktailapp.core.R

@Composable
fun LottieImage(modifier: Modifier = Modifier, @RawRes animationId: Int) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(animationId))
    LottieAnimation(
        modifier = modifier,
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
}

@Preview
@Composable
private fun LottieImagePrev() {
    LottieImage(
        modifier = Modifier.fillMaxSize(),
        animationId = R.raw.core_image_loading
    )
}