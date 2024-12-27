package com.mzs.core.presentation.components.compose.images

import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieImage(
    modifier: Modifier = Modifier,
    @RawRes animationId: Int,
    iterations: Int = LottieConstants.IterateForever
) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(resId = animationId))
    LottieAnimation(
        modifier = modifier,
        composition = composition,
        iterations = iterations
    )
}