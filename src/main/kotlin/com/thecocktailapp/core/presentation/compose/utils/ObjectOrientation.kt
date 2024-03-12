package com.thecocktailapp.core.presentation.compose.utils

sealed class ObjectOrientation {
    object Horizontal : ObjectOrientation()
    object Vertical : ObjectOrientation()
}