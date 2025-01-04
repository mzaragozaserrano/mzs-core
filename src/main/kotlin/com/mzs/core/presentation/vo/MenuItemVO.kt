package com.mzs.core.presentation.vo

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface MenuItemVO {
    @get:DrawableRes
    val iconId: Int
    
    @get:StringRes
    val titleId: Int
}