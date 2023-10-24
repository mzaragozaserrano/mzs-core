package com.mzaragozaserrano.presentation.compose.utils

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

sealed class FontSize(val size: TextUnit) {
    object Min : FontSize(size = 12.sp)
    object ExtraSmall : FontSize(size = 14.sp), Text
    object Small : FontSize(size = 18.sp), Text
    object Normal : FontSize(size = 20.sp), Text
    object Large : FontSize(size = 22.sp), Text
    object ExtraLarge : FontSize(size = 26.sp), Text
    object Max : FontSize(size = 122.sp)
}

sealed interface Text {
    val size: TextUnit
}