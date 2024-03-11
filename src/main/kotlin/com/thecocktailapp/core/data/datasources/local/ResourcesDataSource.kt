package com.thecocktailapp.core.data.datasources.local

import androidx.annotation.StringRes

interface ResourcesDataSource {
    fun getStringFromResource(@StringRes resId: Int): String
    fun getStringOrResource(str: String?, @StringRes resId: Int): String
}