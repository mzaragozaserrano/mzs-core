package com.mzs.core.presentation.vo

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MenuDrawerItemVO<T>(
    val drawerOption: T,
    @DrawableRes val iconId: Int,
    @StringRes val titleId: Int,
)

enum class FakeItem {
    HOME
}