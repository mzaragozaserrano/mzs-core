package com.mzs.core.presentation.compose.components.texts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.mzs.core.presentation.compose.utils.CustomTextModule
import com.mzs.core.presentation.compose.utils.TextType

@Composable
fun BaseText(
    modifier: Modifier = Modifier,
    color: Color,
    fontSize: TextUnit,
    fontWeight: FontWeight = FontWeight.Normal,
    maxLines: Int = 1,
    text: String,
    textAlign: TextAlign,
    textType: TextType = TextType.Adjust(),
) {

    val fontFamily = remember { CustomTextModule.fontConfiguration.font.value }
    var fontSizeScaled by remember { mutableStateOf(value = fontSize) }
    var readyToDraw by remember { mutableStateOf(value = false) }
    val screenWidth = LocalConfiguration.current.screenWidthDp

    Text(
        modifier = modifier.drawWithContent {
            if (readyToDraw) {
                drawContent()
            }
        },
        color = color,
        fontFamily = fontFamily,
        fontSize = fontSizeScaled,
        fontWeight = fontWeight,
        maxLines = maxLines,
        text = text,
        textAlign = textAlign,
        onTextLayout = {
            when (textType) {
                is TextType.Adjust -> {
                    val proportion = (screenWidth / 360).toFloat()
                    fontSizeScaled *= proportion
                    fontSizeScaled = when {
                        fontSizeScaled > textType.maxFontSize -> {
                            textType.maxFontSize
                        }

                        fontSizeScaled < textType.minFontSize -> {
                            textType.minFontSize
                        }

                        else -> {
                            fontSizeScaled
                        }
                    }
                    readyToDraw = true
                }

                is TextType.Default -> {
                    readyToDraw = true
                }
            }
        },
        overflow = TextOverflow.Ellipsis
    )

}

@Composable
fun BaseText(
    modifier: Modifier = Modifier,
    color: Color,
    fontSize: TextUnit,
    fontWeight: FontWeight = FontWeight.Normal,
    maxLines: Int = 1,
    text: AnnotatedString,
    textAlign: TextAlign,
    textType: TextType = TextType.Adjust(),
) {

    val fontFamily = remember { CustomTextModule.fontConfiguration.font.value }
    var fontSizeScaled by remember { mutableStateOf(value = fontSize) }
    var readyToDraw by remember { mutableStateOf(value = false) }
    val screenWidth = LocalConfiguration.current.screenWidthDp

    Text(
        modifier = modifier.drawWithContent {
            if (readyToDraw) {
                drawContent()
            }
        },
        color = color,
        fontFamily = fontFamily,
        fontSize = fontSizeScaled,
        fontWeight = fontWeight,
        maxLines = maxLines,
        text = text,
        textAlign = textAlign,
        onTextLayout = {
            when (textType) {
                is TextType.Adjust -> {
                    val proportion = (screenWidth / 360).toFloat()
                    fontSizeScaled *= proportion
                    fontSizeScaled = when {
                        fontSizeScaled > textType.maxFontSize -> {
                            textType.maxFontSize
                        }

                        fontSizeScaled < textType.minFontSize -> {
                            textType.minFontSize
                        }

                        else -> {
                            fontSizeScaled
                        }
                    }
                    readyToDraw = true
                }

                is TextType.Default -> {
                    readyToDraw = true
                }
            }
        },
        overflow = TextOverflow.Ellipsis
    )

}