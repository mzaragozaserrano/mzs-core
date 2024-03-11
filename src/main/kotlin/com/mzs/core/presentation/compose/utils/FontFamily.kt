package com.mzs.core.presentation.compose.utils

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.thecocktailapp.domain.R

data class FontConfiguration(val font: MutableState<FontFamily> = mutableStateOf(DefaultFontProvider.fontFamily))

interface FontProvider {
    val fontFamily: FontFamily
}

object CustomTextModule {
    var fontConfiguration: FontConfiguration = FontConfiguration()
}

object DefaultFontProvider : FontProvider {
    override val fontFamily: FontFamily = FontFamily(
        Font(R.font.nunito_black, FontWeight.Black),
        Font(R.font.nunito_bold, FontWeight.Bold),
        Font(R.font.nunito_extra_bold, FontWeight.ExtraBold),
        Font(R.font.nunito_extra_light, FontWeight.ExtraLight),
        Font(R.font.nunito_light, FontWeight.Light),
        Font(R.font.nunito_medium, FontWeight.Medium),
        Font(R.font.nunito_regular, FontWeight.Normal),
        Font(R.font.nunito_semi_bold, FontWeight.SemiBold)
    )
}