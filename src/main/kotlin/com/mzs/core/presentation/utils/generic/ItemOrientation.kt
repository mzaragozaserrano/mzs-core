package com.mzs.core.presentation.utils.generic

sealed class ItemOrientation {
    object Horizontal : ItemOrientation()
    object Vertical : ItemOrientation()
}