package com.mzaragozaserrano.compose.composables.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mzaragozaserrano.compose.utils.ObjectOrientation

@Composable
fun <T> Adapter(
    modifier: Modifier = Modifier,
    lineModifier: Modifier = Modifier,
    orientation: ObjectOrientation = ObjectOrientation.Vertical,
    list: List<T>,
    hasLine: Boolean = true,
    contentItem: @Composable (T) -> Unit
) {

    val content: @Composable () -> Unit = {
        list.forEach { item ->
            contentItem(item)
            if (hasLine && list.last() != item) {
                Line(modifier = lineModifier)
            }
        }
    }
    when (orientation) {
        is ObjectOrientation.Horizontal -> {
            HorizontalAdapter(modifier, content)
        }

        is ObjectOrientation.Vertical -> {
            VerticalAdapter(modifier, content)
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