package com.mzs.core.presentation.components.utils

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mzs.core.presentation.utils.generic.ItemOrientation

@Composable
fun <T> Adapter(
    modifier: Modifier = Modifier,
    arrangement: Arrangement.HorizontalOrVertical? = null,
    colorDivider: Color? = null,
    contentPadding: Dp = 16.dp,
    isScrollable: Boolean = true,
    itemOrientation: ItemOrientation = ItemOrientation.Vertical,
    lineModifier: Modifier = Modifier.padding(
        horizontal = if (itemOrientation is ItemOrientation.Vertical) 16.dp else 0.dp,
        vertical = if (itemOrientation is ItemOrientation.Horizontal) 16.dp else 0.dp,
    ),
    list: List<T>,
    item: @Composable (Int, T) -> Unit,
) {

    when (itemOrientation) {
        is ItemOrientation.Horizontal -> {
            HorizontalAdapter(
                modifier = modifier,
                arrangement = arrangement,
                colorDivider = colorDivider,
                contentPadding = PaddingValues(horizontal = contentPadding),
                isScrollable = isScrollable,
                lineModifier = lineModifier,
                list = list,
                item = item
            )
        }

        is ItemOrientation.Vertical -> {
            VerticalAdapter(
                modifier = modifier,
                arrangement = arrangement,
                colorDivider = colorDivider,
                contentPadding = PaddingValues(vertical = contentPadding),
                isScrollable = isScrollable,
                lineModifier = lineModifier,
                list = list,
                item = item
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun <T> HorizontalAdapter(
    modifier: Modifier,
    arrangement: Arrangement.HorizontalOrVertical?,
    colorDivider: Color?,
    contentPadding: PaddingValues,
    isScrollable: Boolean,
    lineModifier: Modifier,
    list: List<T>,
    item: @Composable (Int, T) -> Unit,
) {
    if (isScrollable) {
        CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
            LazyRow(
                modifier = modifier,
                contentPadding = contentPadding,
                horizontalArrangement = arrangement ?: Arrangement.Start,
            ) {
                itemsIndexed(list) { index, item ->
                    item(index, item)
                    if (colorDivider != null && index < list.size - 1) {
                        Line(
                            modifier = lineModifier,
                            color = colorDivider,
                            itemOrientation = ItemOrientation.Horizontal
                        )
                    }
                }
            }
        }
    } else {
        Row(
            modifier = modifier,
            horizontalArrangement = arrangement ?: Arrangement.Start,
        ) {
            list.forEachIndexed { index, item ->
                item(index, item)
                if (colorDivider != null && index < list.size - 1) {
                    Line(
                        modifier = lineModifier,
                        color = colorDivider,
                        itemOrientation = ItemOrientation.Horizontal
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun <T> VerticalAdapter(
    modifier: Modifier,
    arrangement: Arrangement.HorizontalOrVertical?,
    colorDivider: Color?,
    contentPadding: PaddingValues,
    isScrollable: Boolean,
    lineModifier: Modifier,
    list: List<T>,
    item: @Composable (Int, T) -> Unit,
) {
    if (isScrollable) {
        CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
            LazyColumn(
                modifier = modifier,
                contentPadding = contentPadding,
                verticalArrangement = arrangement ?: Arrangement.Top,
            ) {
                itemsIndexed(list) { index, item ->
                    item(index, item)
                    if (colorDivider != null && index < list.size - 1) {
                        Line(modifier = lineModifier, color = colorDivider)
                    }
                }
            }
        }
    } else {
        Column(
            modifier = modifier,
            verticalArrangement = arrangement ?: Arrangement.Top,
        ) {
            list.forEachIndexed { index, item ->
                item(index, item)
                if (colorDivider != null && index < list.size - 1) {
                    Line(modifier = lineModifier, color = colorDivider)
                }
            }
        }
    }
}