package com.thecocktailapp.core.presentation.compose.components.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.thecocktailapp.core.presentation.compose.utils.ObjectOrientation

@Composable
fun <T> ItemList(
    modifier: Modifier = Modifier,
    items: List<T>,
    lineColor: Color? = null,
    lineHeight: Dp = 1.dp,
    lineModifier: Modifier = Modifier,
    orientation: ObjectOrientation = ObjectOrientation.Vertical,
    contentItem: @Composable (T) -> Unit,
) {

    val content: @Composable () -> Unit = {
        items.forEach { item ->
            contentItem(item)
            if (lineColor != null && items.last() != item) {
                Line(modifier = lineModifier, color = lineColor, height = lineHeight)
            }
        }
    }

    when (orientation) {
        is ObjectOrientation.Horizontal -> {
            HorizontalList(modifier = modifier, content = content)
        }

        is ObjectOrientation.Vertical -> {
            VerticalList(modifier = modifier, content = content)
        }
    }

}

@Composable
private fun HorizontalList(
    modifier: Modifier,
    content: @Composable () -> Unit,
) {

    Row(modifier = modifier) {
        content()
    }

}

@Composable
private fun VerticalList(
    modifier: Modifier,
    content: @Composable () -> Unit,
) {

    Column(modifier = modifier) {
        content()
    }

}