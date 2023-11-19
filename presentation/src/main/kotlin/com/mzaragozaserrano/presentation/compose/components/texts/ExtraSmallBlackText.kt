package com.mzaragozaserrano.presentation.compose.components.texts

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mzaragozaserrano.presentation.R
import com.mzaragozaserrano.presentation.compose.utils.FontSize
import com.mzaragozaserrano.presentation.compose.utils.TextType

@Composable
fun ExtraSmallBlackText(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    maxLines: Int = 1,
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    textType: TextType = TextType.Adjust(FontSize.ExtraSmall.size),
) {

    BaseText(
        modifier = modifier,
        color = color,
        fontSize = FontSize.ExtraSmall.size,
        fontWeight = FontWeight.Black,
        maxLines = maxLines,
        text = text,
        textAlign = textAlign,
        textType = textType
    )

}

@Composable
fun ExtraSmallBlackText(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    maxLines: Int = 1,
    text: AnnotatedString,
    textAlign: TextAlign = TextAlign.Start,
    textType: TextType = TextType.Adjust(FontSize.ExtraSmall.size),
) {

    BaseText(
        modifier = modifier,
        color = color,
        fontSize = FontSize.ExtraSmall.size,
        fontWeight = FontWeight.Black,
        maxLines = maxLines,
        text = text,
        textAlign = textAlign,
        textType = textType
    )

}

@Preview
@Composable
private fun ExtraSmallBlackTextPrev() {
    ExtraSmallBlackText(text = stringResource(id = R.string.core_hello_world))
}