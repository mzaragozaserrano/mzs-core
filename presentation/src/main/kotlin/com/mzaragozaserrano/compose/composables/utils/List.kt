package com.mzaragozaserrano.compose.composables.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mzaragozaserrano.compose.utils.ObjectOrientation

@Composable
fun <T> List(
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
            HorizontalAdapter(modifier = modifier, content = content)
        }

        is ObjectOrientation.Vertical -> {
            VerticalAdapter(modifier = modifier, content = content)
        }
    }
}

@Composable
private fun HorizontalAdapter(
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Row(modifier = modifier) {
        content()
    }
}

@Composable
private fun VerticalAdapter(
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        content()
    }
}