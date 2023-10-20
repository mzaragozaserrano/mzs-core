package com.mzaragozaserrano.compose.composables.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mzaragozaserrano.compose.utils.ObjectOrientation

@Composable
fun <T> Recycler(
    modifier: Modifier = Modifier,
    hasLine: Boolean = false,
    itemMinSize: Dp? = null,
    lineModifier: Modifier = Modifier,
    list: List<T>,
    numberCells: Int? = null,
    orientation: ObjectOrientation = ObjectOrientation.Vertical,
    spaceBetween: Dp = 8.dp,
    contentItem: @Composable (T) -> Unit,
) {

    when (orientation) {
        is ObjectOrientation.Horizontal -> {
            if (numberCells != null) {
                HorizontalGridRecycler(
                    modifier = modifier,
                    itemMinSize = itemMinSize,
                    list = list,
                    numberCells = numberCells,
                    spaceBetween = spaceBetween,
                    contentItem = contentItem
                )
            } else {
                HorizontalRecycler(
                    modifier = modifier,
                    hasLine = hasLine,
                    lineModifier = lineModifier,
                    list = list,
                    spaceBetween = spaceBetween,
                    contentItem = contentItem
                )
            }
        }

        is ObjectOrientation.Vertical -> {
            if (numberCells != null) {
                VerticalGridRecycler(
                    modifier = modifier,
                    itemMinSize = itemMinSize,
                    list = list,
                    numberCells = numberCells,
                    spaceBetween = spaceBetween,
                    contentItem = contentItem
                )
            } else {
                VerticalRecycler(
                    modifier = modifier,
                    hasLine = hasLine,
                    lineModifier = lineModifier,
                    list = list,
                    spaceBetween = spaceBetween,
                    contentItem = contentItem
                )
            }
        }
    }
}

@Composable
private fun <T> HorizontalGridRecycler(
    modifier: Modifier,
    itemMinSize: Dp?,
    list: List<T>,
    numberCells: Int,
    spaceBetween: Dp,
    contentItem: @Composable (T) -> Unit,
) {
    LazyHorizontalGrid(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spaceBetween),
        rows = if(itemMinSize != null) GridCells.Adaptive(itemMinSize) else GridCells.Fixed(numberCells),
        verticalArrangement = Arrangement.spacedBy(spaceBetween)
    ) {
        items(list) { item ->
            contentItem(item)
        }
    }
}

@Composable
private fun <T> HorizontalRecycler(
    modifier: Modifier,
    hasLine: Boolean = true,
    lineModifier: Modifier = Modifier,
    list: List<T>,
    spaceBetween: Dp,
    contentItem: @Composable (T) -> Unit,
) {
    LazyRow(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(spaceBetween)) {
        items(list) { item ->
            contentItem(item)
            if (hasLine && list.last() != item) {
                Line(modifier = lineModifier)
            }
        }
    }
}

@Composable
private fun <T> VerticalGridRecycler(
    modifier: Modifier,
    itemMinSize: Dp?,
    list: List<T>,
    numberCells: Int,
    spaceBetween: Dp,
    contentItem: @Composable (T) -> Unit,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = if(itemMinSize != null) GridCells.Adaptive(itemMinSize) else GridCells.Fixed(numberCells),
        horizontalArrangement = Arrangement.spacedBy(spaceBetween),
        verticalArrangement = Arrangement.spacedBy(spaceBetween)
    ) {
        items(list) { item ->
            contentItem(item)
        }
    }
}

@Composable
private fun <T> VerticalRecycler(
    modifier: Modifier,
    hasLine: Boolean = true,
    lineModifier: Modifier = Modifier,
    list: List<T>,
    spaceBetween: Dp,
    contentItem: @Composable (T) -> Unit,
) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(spaceBetween)) {
        items(list) { item ->
            contentItem(item)
            if (hasLine && list.last() != item) {
                Line(modifier = lineModifier)
            }
        }
    }
}