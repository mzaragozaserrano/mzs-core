package com.mzaragozaserrano.presentation.view.vo

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color

data class MinimalButtonVO(
    @DrawableRes val iconId: Int,
    val iconTint: Color = Color.Black,
    val textColor: Color = Color.Black,
    @StringRes val textId: Int,
    val onButtonClicked: () -> Unit,
)