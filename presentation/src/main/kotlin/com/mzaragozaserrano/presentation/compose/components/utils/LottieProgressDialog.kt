package com.mzaragozaserrano.presentation.compose.components.utils

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mzaragozaserrano.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LottieProgressDialog(@RawRes animation: Int, height: Dp, width: Dp) {

    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(animation))

    AlertDialog(
        modifier = Modifier
            .height(height = height)
            .width(width = width),
        onDismissRequest = {}
    ) {
        LottieAnimation(
            modifier = Modifier.fillMaxSize(),
            composition = composition,
            iterations = LottieConstants.IterateForever
        )
    }

}

@Preview
@Composable
private fun LottieProgressDialogPrev() {
    LottieProgressDialog(animation = R.raw.core_loading, height = 500.dp, width = 500.dp)
}