package com.mzaragozaserrano.compose.composables.utils

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mzaragozaserrano.compose.utils.ObjectOrientation

@Composable
fun <T> Recycler(
    modifier: Modifier = Modifier,
    lineModifier: Modifier = Modifier,
    orientation: ObjectOrientation = ObjectOrientation.Vertical,
    list: List<T>,
    hasLine: Boolean = true,
    contentItem: @Composable (T) -> Unit,
) {

    when (orientation) {
        is ObjectOrientation.Horizontal -> {
            HorizontalRecycler(
                modifier = modifier,
                lineModifier = lineModifier,
                list = list,
                hasLine = hasLine,
                contentItem = contentItem
            )
        }

        is ObjectOrientation.Vertical -> {
            VerticalRecycler(
                modifier = modifier,
                lineModifier = lineModifier,
                list = list,
                hasLine = hasLine,
                contentItem = contentItem
            )
        }
    }
}

@Composable
private fun <T> HorizontalRecycler(
    modifier: Modifier,
    lineModifier: Modifier = Modifier,
    list: List<T>,
    hasLine: Boolean = true,
    contentItem: @Composable (T) -> Unit,
) {
    LazyRow(modifier = modifier) {
        items(list) { item ->
            contentItem(item)
            if (hasLine && list.last() != item) {
                Line(modifier = lineModifier)
            }
        }
    }
}

@Composable
private fun <T> VerticalRecycler(
    modifier: Modifier,
    lineModifier: Modifier = Modifier,
    list: List<T>,
    hasLine: Boolean = true,
    contentItem: @Composable (T) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        items(list) { item ->
            contentItem(item)
            if (hasLine && list.last() != item) {
                Line(modifier = lineModifier)
            }
        }
    }
}