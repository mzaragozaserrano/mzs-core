package com.mzs.core.presentation.utils.generic

sealed class ItemOrientation {
    data object Horizontal : ItemOrientation()
    data object Vertical : ItemOrientation()
}