package com.mzaragozaserrano.compose.components.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mzaragozaserrano.compose.utils.ObjectOrientation

@Composable
fun <T> ItemList(
    modifier: Modifier = Modifier,
    hasLine: Boolean = true,
    items: List<T>,
    lineModifier: Modifier = Modifier,
    orientation: ObjectOrientation = ObjectOrientation.Vertical,
    contentItem: @Composable (T) -> Unit
) {

    val content: @Composable () -> Unit = {
        items.forEach { item ->
            contentItem(item)
            if (hasLine && items.last() != item) {
                Line(modifier = lineModifier)
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
    content: @Composable () -> Unit
) {
    Row(modifier = modifier) {
        content()
    }
}

@Composable
private fun VerticalList(
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        content()
    }
}