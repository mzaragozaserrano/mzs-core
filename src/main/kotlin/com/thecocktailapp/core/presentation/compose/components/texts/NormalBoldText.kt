package com.thecocktailapp.core.presentation.compose.components.texts

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.thecocktailapp.core.R
import com.thecocktailapp.core.presentation.compose.utils.FontSize
import com.thecocktailapp.core.presentation.compose.utils.TextType

@Composable
fun NormalBoldText(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    maxLines: Int = 1,
    minLines: Int = 1,
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    textType: TextType = TextType.Adjust(FontSize.Normal.size),
) {
    BaseText(
        modifier = modifier,
        color = color,
        fontSize = FontSize.Normal.size,
        fontWeight = FontWeight.Bold,
        maxLines = maxLines,
        minLines = minLines,
        text = text,
        textAlign = textAlign,
        textType = textType
    )
}

@Composable
fun NormalBoldText(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    maxLines: Int = 1,
    text: AnnotatedString,
    textAlign: TextAlign = TextAlign.Start,
    textType: TextType = TextType.Adjust(FontSize.Normal.size),
) {
    BaseText(
        modifier = modifier,
        color = color,
        fontSize = FontSize.Normal.size,
        fontWeight = FontWeight.Bold,
        maxLines = maxLines,
        text = text,
        textAlign = textAlign,
        textType = textType
    )
}

@Preview
@Composable
private fun NormalBoldTextPrev() {
    NormalBoldText(text = stringResource(id = R.string.core_hello_world))
}