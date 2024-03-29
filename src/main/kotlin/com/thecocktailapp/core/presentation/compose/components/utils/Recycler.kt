package com.thecocktailapp.core.presentation.compose.components.utils

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.thecocktailapp.core.presentation.compose.utils.ObjectOrientation

@Composable
fun <T> Recycler(
    modifier: Modifier = Modifier,
    itemMinSize: Dp? = null,
    lineColor: Color? = null,
    lineHeight: Dp = 1.dp,
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
                    lineColor = lineColor,
                    lineHeight = lineHeight,
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
                    lineColor = lineColor,
                    lineHeight = lineHeight,
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
        horizontalArrangement = Arrangement.spacedBy(space = spaceBetween),
        rows = if (itemMinSize != null) GridCells.Adaptive(minSize = itemMinSize) else GridCells.Fixed(
            count = numberCells
        ),
        verticalArrangement = Arrangement.spacedBy(space = spaceBetween)
    ) {
        items(list) { item ->
            contentItem(item)
        }
    }

}

@Composable
private fun <T> HorizontalRecycler(
    modifier: Modifier,
    lineColor: Color? = null,
    lineHeight: Dp = 1.dp,
    lineModifier: Modifier = Modifier,
    list: List<T>,
    spaceBetween: Dp,
    contentItem: @Composable (T) -> Unit,
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(space = spaceBetween)
    ) {
        items(list) { item ->
            contentItem(item)
            if (lineColor != null && list.last() != item) {
                Line(modifier = lineModifier, color = lineColor, height = lineHeight)
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
        columns = if (itemMinSize != null) GridCells.Adaptive(minSize = itemMinSize) else GridCells.Fixed(
            count = numberCells
        ),
        horizontalArrangement = Arrangement.spacedBy(space = spaceBetween),
        verticalArrangement = Arrangement.spacedBy(space = spaceBetween)
    ) {
        items(list) { item ->
            contentItem(item)
        }
    }
}

@Composable
private fun <T> VerticalRecycler(
    modifier: Modifier,
    lineColor: Color? = null,
    lineHeight: Dp = 1.dp,
    lineModifier: Modifier = Modifier,
    list: List<T>,
    spaceBetween: Dp,
    contentItem: @Composable (T) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(space = spaceBetween)
    ) {
        items(list) { item ->
            contentItem(item)
            if (lineColor != null && list.last() != item) {
                Line(modifier = lineModifier, color = lineColor, height = lineHeight)
            }
        }
    }
}