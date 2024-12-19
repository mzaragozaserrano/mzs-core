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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mzs.core.presentation.utils.generic.ItemOrientation

@Composable
fun <T> Adapter(
    modifier: Modifier = Modifier,
    itemOrientation: ItemOrientation = ItemOrientation.Vertical,
    lineModifier: Modifier = Modifier.padding(
        horizontal = if (itemOrientation is ItemOrientation.Vertical) 16.dp else 0.dp,
        vertical = if (itemOrientation is ItemOrientation.Horizontal) 16.dp else 0.dp,
    ),
    list: List<T>,
    isScrollable: Boolean = true,
    showDivider: Boolean = false,
    contentPadding: Dp = 16.dp,
    arrangement: Arrangement.HorizontalOrVertical? = null,
    item: @Composable (Int, T) -> Unit,
) {

    when (itemOrientation) {
        is ItemOrientation.Horizontal -> {
            HorizontalAdapter(
                modifier = modifier,
                lineModifier = lineModifier,
                list = list,
                contentPadding = PaddingValues(horizontal = contentPadding),
                arrangement = arrangement,
                isScrollable = isScrollable,
                showDivider = showDivider,
                content = item
            )
        }

        is ItemOrientation.Vertical -> {
            VerticalAdapter(
                modifier = modifier,
                lineModifier = lineModifier,
                list = list,
                contentPadding = PaddingValues(vertical = contentPadding),
                arrangement = arrangement,
                isScrollable = isScrollable,
                showDivider = showDivider,
                content = item
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun <T> HorizontalAdapter(
    modifier: Modifier,
    lineModifier: Modifier,
    list: List<T>,
    contentPadding: PaddingValues,
    arrangement: Arrangement.HorizontalOrVertical?,
    isScrollable: Boolean,
    showDivider: Boolean,
    content: @Composable (Int, T) -> Unit,
) {
    if (isScrollable) {
        CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
            LazyRow(
                modifier = modifier,
                contentPadding = contentPadding,
                horizontalArrangement = arrangement ?: Arrangement.Start,
            ) {
                itemsIndexed(list) { index, item ->
                    content(index, item)
                    if (showDivider && index < list.size - 1) {
                        Line(
                            modifier = lineModifier,
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
                content(index, item)
                if (showDivider && index < list.size - 1) {
                    Line(
                        modifier = lineModifier,
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
    lineModifier: Modifier,
    list: List<T>,
    contentPadding: PaddingValues,
    arrangement: Arrangement.HorizontalOrVertical?,
    isScrollable: Boolean,
    showDivider: Boolean,
    content: @Composable (Int, T) -> Unit,
) {
    if (isScrollable) {
        CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
            LazyColumn(
                modifier = modifier,
                contentPadding = contentPadding,
                verticalArrangement = arrangement ?: Arrangement.Top,
            ) {
                itemsIndexed(list) { index, item ->
                    content(index, item)
                    if (showDivider && index < list.size - 1) {
                        Line(modifier = lineModifier)
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
                content(index, item)
                if (showDivider && index < list.size - 1) {
                    Line(modifier = lineModifier)
                }
            }
        }
    }
}