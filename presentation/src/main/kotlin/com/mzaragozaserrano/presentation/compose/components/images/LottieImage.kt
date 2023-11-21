package com.mzaragozaserrano.presentation.compose.components.images

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
import com.mzaragozaserrano.presentation.R

@Composable
fun LottieImage(modifier: Modifier = Modifier, @RawRes animation: Int) {

    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(animation))

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
        animation = R.raw.core_image_loading
    )

}