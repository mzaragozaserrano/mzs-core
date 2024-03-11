package presentation.utils

import androidx.compose.ui.unit.TextUnit

sealed class TextType {
    data class Adjust(
        val fontSize: TextUnit? = null,
        val minFontSize: TextUnit = if (fontSize != null) fontSize * 0.75f else FontSize.Min.size,
        val maxFontSize: TextUnit = if (fontSize != null) fontSize * 2.25f else FontSize.Max.size
    ) : TextType()

    object Default : TextType()
}