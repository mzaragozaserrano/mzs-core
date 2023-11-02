package com.mzaragozaserrano.presentation.compose.components.texts

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mzaragozaserrano.presentation.R
import com.mzaragozaserrano.presentation.compose.utils.FontSize
import com.mzaragozaserrano.presentation.compose.utils.TextType

@Composable
fun SmallText(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    maxLines: Int = 1,
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    textType: TextType = TextType.Adjust(FontSize.Small.size),
) {
    BaseText(
        modifier = modifier,
        color = color,
        fontSize = FontSize.Small.size,
        maxLines = maxLines,
        text = text,
        textAlign = textAlign,
        textType = textType
    )
}

@Composable
fun SmallText(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    maxLines: Int = 1,
    @StringRes textId: Int,
    textAlign: TextAlign = TextAlign.Start,
    textType: TextType = TextType.Adjust(FontSize.Small.size),
) {
    BaseText(
        modifier = modifier,
        color = color,
        fontSize = FontSize.Small.size,
        maxLines = maxLines,
        text = stringResource(id = textId),
        textAlign = textAlign,
        textType = textType
    )
}

@Composable
fun SmallText(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    maxLines: Int = 1,
    text: AnnotatedString,
    textAlign: TextAlign = TextAlign.Start,
    textType: TextType = TextType.Adjust(FontSize.Small.size),
) {
    BaseText(
        modifier = modifier,
        color = color,
        fontSize = FontSize.Small.size,
        maxLines = maxLines,
        text = text,
        textAlign = textAlign,
        textType = textType
    )
}

@Preview
@Composable
private fun SmallTextPrev() {
    SmallText(text = stringResource(id = R.string.core_hello_world))
}